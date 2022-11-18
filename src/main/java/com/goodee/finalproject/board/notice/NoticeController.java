package com.goodee.finalproject.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.finalproject.util.BoardPageMaker;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/board/notice/*")
@Slf4j
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("list")
	public ModelAndView getNoticeList(BoardPageMaker boardPageMaker) throws Exception{
		ModelAndView mv = new ModelAndView();
		boardPageMaker.setPerPage(5L);
		List<NoticeVO> test  = noticeService.getNoticeList(boardPageMaker);
		mv.addObject("testdto", test);
		mv.setViewName("/board/notice/list");
		return mv;
	}
	
	@GetMapping("add")
	public void setNoticeAdd(NoticeVO noticeVO) throws Exception{
		log.info("---------------------");
	}
	
	@PostMapping("add")
	public ModelAndView setNoticeAdd(NoticeVO noticeVO, MultipartFile [] files,RedirectAttributes redirectAttributes) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		log.info("NoticeVO -> {}", noticeVO);
		int result = noticeService.setNoticeAdd(noticeVO);
		
		mv.setViewName("redirect:./list");
		
		redirectAttributes.addAttribute("result", result);
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getNoticeListDetail(NoticeVO noticeVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		NoticeVO test = noticeService.getNoticeListDetail(noticeVO);
	
		mv.addObject("noticeVO",test);
		mv.setViewName("/board/notice/detail");
		return mv;
	}
	
	@GetMapping("update")
	public void setNoticeUpdate(NoticeVO noticeVO,Model model) throws Exception{
		model.addAttribute("noticeNum", noticeVO.getNoticeNum());
	}
	
	@PostMapping("update")
	public ModelAndView setNoticeUpdate(NoticeVO noticeVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = noticeService.setNoticeUpdate(noticeVO);
		
		mv.setViewName("redirect:./list");
		
		return mv;
	}
	
	@GetMapping("delete")
	public ModelAndView setNoticeDelete(NoticeVO noticeVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		noticeService.setNoticeDelete(noticeVO);
		mv.setViewName("redirect:./list");
		return mv;
	}
}
