package com.goodee.finalproject.product;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.finalproject.board.application.ApplicationController;

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
		
		
		mv.setViewName("product/list");
		return mv;
	}
	
	@GetMapping("list")
	public ModelAndView getSaleProductList(SaleProductVO saleproductVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		CategoryVO category = new CategoryVO();
		List<CategoryVO> categoryVO = productService.getCategoryList(category);
		List<SaleProductVO> saleVO = productService.getSaleProductList(saleproductVO);

		LocalDateTime now = LocalDateTime.now();
		 
		Timestamp timestamp = Timestamp.valueOf(now);
		
		
		List<ProductVO> productVOs = new ArrayList<>();
		for(int productNum=0; productNum<saleVO.size(); productNum++) {
			ProductVO productVO = new ProductVO();
			
			productVO.setProductNum(saleVO.get(productNum).getProductNum());
			productVO = productService.getProductApproval(productVO);
			Long test1 = saleVO.get(productNum).getProductDate().getTime();
			Long test2 = productVO.getAuctionPeriod()*24*3600*1000;
			Timestamp timestamp2 = new Timestamp(test1+test2);
			
			if(timestamp2.before(timestamp)) {
				saleproductVO.setProductId(saleVO.get(productNum).getProductId());
				int result = productService.setDeadLineUpdate(saleproductVO);
				System.out.println(result);
			}
				
			mv.addObject("testVO"+productNum, productVO);
			
			
		}
		
		
		mv.addObject("categoryVO", categoryVO);
		mv.addObject("saleVO", saleVO);
		mv.setViewName("product/list");
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getSaleProductListDetail(SaleProductVO saleProductVO) throws Exception{
		ModelAndView mv= new ModelAndView();
		saleProductVO = productService.getSaleProductListDetail(saleProductVO);
		
		mv.addObject("saleProductVO", saleProductVO);
		mv.setViewName("product/detail");
		return mv;
	}
}
