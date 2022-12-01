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

	@PostMapping("add")
	public ModelAndView setProductAdd(ProductVO productVO,RedirectAttributes redirectAttributes,MultipartFile [] files,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		log.info("<---------------------productAdd-------->");
		String contents = productVO.getProductInformation();
		
		log.info("APPLICATIONNUM -> {}",productVO.getApplicationNum());
		
		
		productVO.setProductInformation(contents.replace("temp/","notice"));
		int result = productService.setProductAdd(productVO,files,session.getServletContext());
		productService.saveTempFile(productVO,session.getServletContext());
//		int result = noticeService.setNoticeAdd(noticeVO);
		mv.setViewName("redirect:./list");
		redirectAttributes.addAttribute("result", result);
		return mv;
	}
	
	@GetMapping("approval")
	public void getApprovalProduct(ProductVO productVO) throws Exception{
		
	}
	
}
