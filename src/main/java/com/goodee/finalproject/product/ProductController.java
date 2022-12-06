package com.goodee.finalproject.product;

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
		log.info("category -> {}", categoryVO);
		log.info("sale -> {}", saleVO);
		
		List<ProductVO> productVOs = new ArrayList<>();
//		for(int productNum=0; productNum<saleVO.size(); productNum++) {
			ProductVO productVO = new ProductVO();
			productVO.setProductNum(saleVO.get(0).getProductNum());
			productVO = productService.getProductApproval(productVO);
			
			
//		}
		mv.addObject("testVO", productVO);
		mv.addObject("categoryVO", categoryVO);
		mv.addObject("saleVO", saleVO);
		mv.setViewName("product/list");
		return mv;
	}
}
