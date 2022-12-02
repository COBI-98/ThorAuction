package com.goodee.finalproject.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.finalproject.board.application.ApplicationService;
import com.goodee.finalproject.board.notice.NoticeFileVO;
import com.goodee.finalproject.board.notice.NoticeService;
import com.goodee.finalproject.product.ProductFileVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileManageController {

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@GetMapping("/fileDown/{path}") // RestFul, RestAPI
	public ModelAndView fileDown(@PathVariable String path,NoticeFileVO noticeFileVO,ProductFileVO productFileVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		//DB에서 정보 조회
		log.info("Path {}",path);
		
		// 경로에 맞는 service 사용 (db)
		if(path.equals("notice")) {
			
			noticeFileVO = noticeService.getFileCheck(noticeFileVO);
			mv.addObject("fileVO", noticeFileVO);
			mv.addObject("path", path);
			mv.setViewName("fileManager");
		} else if (path.equals("application")) {
			
		}
		
//		log.info("FileVO {}", qnaFileVO);
		
		
		
		
		return mv;
	}
	}