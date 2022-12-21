package com.goodee.finalproject.product;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.mypage.MypageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/product/*")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private MypageService mypageService;
	
	@GetMapping("approval")
	public ModelAndView getApprovalProduct(ProductVO productVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		productVO = productService.getProductApproval(productVO);
		
		mv.addObject("productVO",productVO);
		mv.setViewName("product/approval");
		return mv;
	}
	
	@PostMapping("approval")
	public ModelAndView setSaleProduct(SaleProductVO saleProductVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = productService.setSaleProduct(saleProductVO);
		
		
		mv.setViewName("redirect:./list");
		return mv;
	}
	
	@GetMapping("list")
	public ModelAndView getSaleProductList(SaleProductVO saleproductVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		CategoryVO category = new CategoryVO();
		
		List<CategoryVO> categoryVO = productService.getCategoryList(category);
		List<SaleProductVO> saleVO = new ArrayList<>();
		if(saleproductVO.getCategoryId() == null) {
			saleVO = productService.getSaleProductList(saleproductVO);
		}else {
			saleVO = productService.getSaleProductCategoryList(saleproductVO);
		}
		
		if(saleproductVO.getSc().equals("2") && saleproductVO.getCategoryId() == null){
			saleVO = productService.getSaleProductHitList(saleproductVO);
		}
		
		//카테고리 조회수 추가
		
		List<Long> orderTime = new ArrayList<>();
		
		BidAmountVO bidAmountVO = new BidAmountVO();
		
		// 마감시간 확인
		LocalDateTime now = LocalDateTime.now(); 
		Timestamp timestamp = Timestamp.valueOf(now);
		
		for(int productNum=0; productNum<saleVO.size(); productNum++) {
			ProductVO productVO = new ProductVO();
			
			//상품 리스트 디테일
			productVO.setProductNum(saleVO.get(productNum).getProductNum());
			productVO = productService.getProductApproval(productVO);
			
			// 시간 
			Long test1 = saleVO.get(productNum).getProductDate().getTime();
			Long test2 = productVO.getAuctionPeriod()*24*3600*1000;
			
			Timestamp timestamp2 = new Timestamp(test1+test2);
			// 현재최고가 
			bidAmountVO.setProductId(saleVO.get(productNum).getProductId());
			Long check = productService.getMaxAmountCheck(bidAmountVO);
			
			orderTime.add(test1+test2);
			// deadline 업데이트 
			if(timestamp2.before(timestamp)) {
				saleproductVO.setProductId(saleVO.get(productNum).getProductId());
				int result = productService.setDeadLineUpdate(saleproductVO);
				
				// 최고가가 null 이라면 입찰한 자가 없으니 status 유지
				if(check != null) {
					//최고가가 있다면 경매종료 후 상품 결제진행
					productService.setStatus(productVO);
				}
			}
			
			
			
			//최고가가 없다면 초기값 지정
			if(check == null) {
				check = productVO.getProductPrice();
			}
			
			saleVO.get(productNum).setMaxBidAmount(check);
			System.out.println(saleVO.get(productNum).getMaxBidAmount());
			mv.addObject("testVO"+productNum, productVO);
			mv.addObject("bidAmountCheck"+productNum, check);

		}
		// 정렬
		if(saleproductVO.getSc().equals("3")) {
			Collections.sort(orderTime);
		}else if(saleproductVO.getSc().equals("4")) {
			saleVO = saleVO.stream().sorted(Comparator.comparing(SaleProductVO::getMaxBidAmount)).collect(Collectors.toList());
			
		}else if(saleproductVO.getSc().equals("5")) {
			saleVO = saleVO.stream().sorted(Comparator.comparing(SaleProductVO::getMaxBidAmount).reversed()).collect(Collectors.toList());
			
		}else {
			
		}

		mv.addObject("orderTime", orderTime);
		mv.addObject("classCheck", saleproductVO.getSc());
		mv.addObject("categoryVO", categoryVO);
		mv.addObject("saleVO", saleVO);
		mv.setViewName("product/list");
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getSaleProductListDetail(SaleProductVO saleProductVO,Authentication authentication, HttpSession session) throws Exception{
		ModelAndView mv= new ModelAndView();
		
		// 댓글관리과 입찰내역이 하나의 테이블에 left join이 안되어서 VO관리를 두개로 나눔
		SaleProductVO saleProductVO2 = saleProductVO;
		saleProductVO = productService.getSaleProductListDetail(saleProductVO);
		
		//댓글관리
		saleProductVO2 = productService.getSaleProductListDetail2(saleProductVO2);
		
		productService.setSaleProductHit(saleProductVO);
		
		if(authentication != null) {
			MemberVO memberVO= (MemberVO) authentication.getPrincipal();
			mv.addObject("memberVO", memberVO);
		}
		BidAmountVO bidAmountVO = new BidAmountVO();
		
		LocalDateTime now = LocalDateTime.now(); 
		Timestamp timestamp = Timestamp.valueOf(now);
		
		
		bidAmountVO.setProductId(saleProductVO.getProductId());
		Long check = productService.getMaxAmountCheck(bidAmountVO);
		
		if(check == null) {
			check = saleProductVO.getProductVOs().get(0).getProductPrice();
		}
		
		// 최근 본 상품
		Long productId = saleProductVO.getProductId();
		
		List<Long> ar = (List<Long>)session.getAttribute("recent");
		
		if(ar == null) {
			ar = new ArrayList<Long>();
			session.setAttribute("recent", ar);
		}
		
		ar.add(productId);
		
		// 중복상품 제거
		Set<Long> set = new HashSet<Long>(ar);
		
		// 중복 제거된 상품을 다시 newAr에 넣기
		List<Long> newAr = new ArrayList<Long>(set);
		
		List<SaleProductVO> recentList = new ArrayList<SaleProductVO>();
		
		// 최근 본 상품 form에 넣기
		for(Long l : newAr) {
			List<SaleProductVO> recentListDetail = productService.recentProduct(l);
			
			if(recentListDetail.size() != 0) {
				recentList.add(recentListDetail.get(0));
			}
		}
		
		session.setAttribute("recentList", recentList);
		
		mv.addObject("recentList", recentList);
		
		log.info("최근 : {}", recentList);
		// 최근 본 상품 End
		
		mv.addObject("maxAmount", check);
		
		mv.addObject("saleProductVO", saleProductVO);
		mv.addObject("saleProductQna", saleProductVO2);
		mv.setViewName("product/detail");
		return mv;
	}
	
	// 입찰 관리 bid post
	@PostMapping("bid")
	@ResponseBody
	public String setBidAmountAdd(BidAmountVO bidAmountVO) throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setId(bidAmountVO.getId());
		log.info("test2 -> {}",bidAmountVO.getBidAmount());
		memberVO = mypageService.getList(memberVO);
		Long check = productService.getMaxAmountCheck(bidAmountVO);
		
		List<BidAmountVO> bidAmountUserList = productService.getBidAmountUser(bidAmountVO);
		String test = "";
		String userCheck = "";
		Long userBidAmount = 0L;
		// 마감된경우
		if(bidAmountVO.getDeadCheck().equals("1")) {
			test = "1";
			return test;
		}
		
		// 시작가 관리 최대값이 없다면
		if(check == null) {
			check = bidAmountVO.getStartAmount();
			if(check > bidAmountVO.getBidAmount()) {
				test ="3";
				return test;
			}else {	
				if(memberVO.getPoint() < bidAmountVO.getBidAmount()) {
					test = "5";
				}else {
					test="6";
					productService.setBidAmountAdd(bidAmountVO);
					return test;
				}
			}
		}
			
		// 최대가격과 넘어오는 가격이 같음
		if(check.equals(bidAmountVO.getBidAmount())) {
			test = "4";
		} else if(check > bidAmountVO.getBidAmount()) {
			test = "2";
		}else if(check < bidAmountVO.getBidAmount()){
			if(memberVO.getPoint() < bidAmountVO.getBidAmount()) {
				test = "5";
			}else {
				// 같은 유저가 동일한 상품을 입찰하면 db가 업데이트
				for(int i=0;i<bidAmountUserList.size();i++) {
					if(bidAmountUserList.get(i).getId().equals(bidAmountVO.getId())) {
						userCheck="1";
						userBidAmount = bidAmountUserList.get(i).getBidAmount();
					}
				}				
				test="6";
				if(userCheck.equals("1")) {
					productService.setBidAmountUpdate(bidAmountVO);
					bidAmountVO.setBidAmount(bidAmountVO.getBidAmount()-userBidAmount);
					productService.setBidAmountSuccess(bidAmountVO);
				}else {
					productService.setBidAmountAdd(bidAmountVO);
					productService.setBidAmountSuccess(bidAmountVO);
				}
			}
		} 

		return test;
	}
	
	@PostMapping("likeProduct")
	@ResponseBody
	public String setLikeSaleProduct(LikeSaleProductVO likeSaleProductVO) throws Exception{
		
		String likeCheck = productService.getLikeProductCheck(likeSaleProductVO);
		
		if(likeCheck == null) {
			productService.setLikeSaleProduct(likeSaleProductVO);
			likeCheck = "N";
		}else {
			likeCheck = "Y";
		}
		System.out.println(likeCheck);
		return likeCheck;
	}
	
	@PostMapping("questionAdd")
	public ModelAndView setQuestionAdd(ProductQuestionVO productQuestionVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = productService.setQuestionAdd(productQuestionVO);
		
		String message ="";
		String url = "";
		if(result==1) {
			message="success";
			url= "/product/detail?productId="+productQuestionVO.getProductId();
		}else {
			message ="fail";
			url= "/product/detail?productId="+productQuestionVO.getProductId();
		}
		mv.addObject("message", message);
		mv.addObject("url", url);
		mv.setViewName("common/result");
		return mv;
	}
	
	@PostMapping("adminQuestionAdd")
	public ModelAndView setAdminQuestionAdd(AdminQuestionVO adminQuestionVO, String prNum) throws Exception{
		ModelAndView mv = new ModelAndView();

		String message ="";
		String url = "";
		
		int result = productService.setAdminQuestionAdd(adminQuestionVO);
		
		
		if(result==1) {
			message="success";
			url = "/product/detail?productId="+prNum;
		}else {
			message ="fail";
			url = "/product/detail?productId="+prNum;
		}
		mv.addObject("message", message);
		mv.addObject("url", url);
		mv.setViewName("common/result");
		return mv;
	}
	
	@PostMapping("questionDelete")
	@ResponseBody
	public int setQuestionDelete(ProductQuestionVO productQuestionVO) throws Exception{
		
		AdminQuestionVO adminQuestionVO = productService.getAdminQuestionCheck(productQuestionVO);
		int result = 0;
		if(adminQuestionVO == null) {
			productService.setQuestionDelete(productQuestionVO);
			result = 1;
		}else {
			productService.setAdminQuestionDelete(adminQuestionVO);
			productService.setQuestionDelete(productQuestionVO);
			result = 1;
		}
		return result;
	}
	
	@PostMapping("adminQuestionDelete")
	@ResponseBody
	public int setAdminQuestionDelete(AdminQuestionVO adminQuestionVO) throws Exception{
		
		int result = productService.setAdminQuestionDelete(adminQuestionVO);
		
		return result;
	}
	
	@PostMapping("deadLineProduct")
	@ResponseBody
	public int setDeadLineProductCheck(SaleProductVO saleproductVO) throws Exception{

		int result = productService.setDeadLineUpdate(saleproductVO);
		System.out.println(saleproductVO);
		return result;
	}
}
