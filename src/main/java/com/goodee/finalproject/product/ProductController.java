package com.goodee.finalproject.product;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.finalproject.board.application.ApplicationController;
import com.goodee.finalproject.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/product/*")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService productService;

	
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
		List<SaleProductVO> saleVO = productService.getSaleProductList(saleproductVO);
		
		if(saleproductVO.getSc().equals("2")){
			saleVO = productService.getSaleProductHitList(saleproductVO);
		}
		
		List<Long> orderTime = new ArrayList<>();
		List<Long> orderBidAmount = new ArrayList<>();
		
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
			orderTime.add(test1+test2);
			if(timestamp2.before(timestamp)) {
				saleproductVO.setProductId(saleVO.get(productNum).getProductId());
				int result = productService.setDeadLineUpdate(saleproductVO);
			}
			
			// 현재최고가 
			bidAmountVO.setProductId(saleVO.get(productNum).getProductId());
			Long check = productService.getMaxAmountCheck(bidAmountVO);
			
			if(check == null) {
				check = productVO.getProductPrice();
			}
			
			orderBidAmount.add(check);
			mv.addObject("testVO"+productNum, productVO);
			mv.addObject("bidAmountCheck"+productNum, check);

		}
		
		// 정렬
		if(saleproductVO.getSc().equals("3")) {
			Collections.sort(orderTime);
		}else if(saleproductVO.getSc().equals("4")) {
			Collections.sort(orderBidAmount);
		}else if(saleproductVO.getSc().equals("5")) {
			Collections.sort(orderBidAmount, Collections.reverseOrder());
		}else {
			
		}

		mv.addObject("orderTime", orderTime);
		mv.addObject("orderBidAmount", orderBidAmount);
		mv.addObject("classCheck", saleproductVO.getSc());
		mv.addObject("categoryVO", categoryVO);
		mv.addObject("saleVO", saleVO);
		mv.setViewName("product/list");
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getSaleProductListDetail(SaleProductVO saleProductVO,Authentication authentication) throws Exception{
		ModelAndView mv= new ModelAndView();
		saleProductVO = productService.getSaleProductListDetail(saleProductVO);
		productService.setSaleProductHit(saleProductVO);
		if(authentication != null) {
			MemberVO memberVO= (MemberVO) authentication.getPrincipal();
			mv.addObject("memberVO", memberVO);
		}
		BidAmountVO bidAmountVO = new BidAmountVO();
		
//		log.info("Test 1 -> {}", saleProductVO.getBidAmountVOs());
		
		bidAmountVO.setProductId(saleProductVO.getProductId());
		Long check = productService.getMaxAmountCheck(bidAmountVO);
		
		if(check == null) {
			check = saleProductVO.getProductVOs().get(0).getProductPrice();
		}
		
		mv.addObject("maxAmount", check);
		
		mv.addObject("saleProductVO", saleProductVO);
		mv.setViewName("product/detail");
		return mv;
	}
	
	@PostMapping("bid")
	@ResponseBody
	public String setBidAmountAdd(BidAmountVO bidAmountVO) throws Exception{
		
		log.info("test2 -> {}",bidAmountVO);
		int result = productService.setBidAmountAdd(bidAmountVO);
		String test = "";
		log.info("test -> {}",result);
		if(result == 1) {
			test = "7";
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
	
}
