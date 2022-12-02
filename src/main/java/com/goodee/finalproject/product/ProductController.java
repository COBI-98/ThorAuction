package com.goodee.finalproject.product;

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
	
}
