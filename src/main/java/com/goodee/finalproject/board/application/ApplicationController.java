package com.goodee.finalproject.board.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.finalproject.board.application.ApplicationVO;
import com.goodee.finalproject.util.BoardPageMaker;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board/application/*")
@Slf4j
public class ApplicationController {

	@Autowired
	private ApplicationService ApplicationService;
	

	@GetMapping("list")
	public ModelAndView getApplicationList(BoardPageMaker boardPageMaker) throws Exception{
		ModelAndView mv = new ModelAndView();
		boardPageMaker.setPerPage(5L);
		List<ApplicationVO> test  = ApplicationService.getApplicationList(boardPageMaker);
		mv.addObject("applicationVO", test);
		log.info("--------------------");
		mv.setViewName("/board/application/list");
		return mv;
	}
	
	@GetMapping("add")
	public void setApplicationAdd(ApplicationVO applicationVO) throws Exception{
		log.info("---------------------");
	}
	
	@PostMapping("add")
	public ModelAndView setApplicationAdd(ApplicationVO applicationVO, MultipartFile [] files,RedirectAttributes redirectAttributes) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		int result = ApplicationService.setApplicationAdd(applicationVO);
		
		mv.setViewName("redirect:./list");
		
		redirectAttributes.addAttribute("result", result);
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getApplicationListDetail(ApplicationVO ApplicationVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		ApplicationVO VO = ApplicationService.getApplicationListDetail(ApplicationVO);
	
		mv.addObject("applicationVO",VO);
		mv.setViewName("/board/application/detail");
		return mv;
	}
	
	@GetMapping("update")
	public void setApplicationUpdate(ApplicationVO applicationVO,Model model) throws Exception{
		model.addAttribute("applicationNum", applicationVO.getApplicationNum());
	}
	
	@PostMapping("update")
	public ModelAndView setApplicationUpdate(ApplicationVO applicationVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = ApplicationService.setApplicationUpdate(applicationVO);
		
		mv.setViewName("redirect:./list");
		
		return mv;
	}
	
	@GetMapping("delete")
	public ModelAndView setApplicationDelete(ApplicationVO applicationVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		ApplicationService.setApplicationDelete(applicationVO);
		mv.setViewName("redirect:./list");
		return mv;
	}
}
