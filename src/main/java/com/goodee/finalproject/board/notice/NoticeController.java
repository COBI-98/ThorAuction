package com.goodee.finalproject.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/board/notice/*")
public class NoticeController {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@GetMapping("list")
	public ModelAndView getNoticeList(NoticeVO noticeVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<NoticeVO> test  = noticeMapper.getNoticeList(noticeVO);
		
		mv.addObject("testdto", test);
		mv.setViewName("/board/notice/list");
		return mv;
	}
}
