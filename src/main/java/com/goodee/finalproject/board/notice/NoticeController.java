package com.goodee.finalproject.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/board/notice/*")
@Slf4j
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("list")
	public ModelAndView getNoticeList(NoticeVO noticeVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<NoticeVO> test  = noticeService.getNoticeList(noticeVO);
		
		mv.addObject("testdto", test);
		mv.setViewName("/board/notice/list");
		return mv;
	}
	
	@GetMapping("add")
	public void setNoticeAdd(NoticeVO notcieVO) throws Exception{
		log.info("---------------------");
	}
	
	@PostMapping("add")
	public ModelAndView setNoticeAdd(NoticeVO notcieVO, MultipartFile [] files) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		log.info("NoticeVO -> {}", notcieVO);
		int result = noticeService.setNoticeAdd(notcieVO);
		
		mv.setViewName("redirect:./list");
		
		return mv;
	}
}
