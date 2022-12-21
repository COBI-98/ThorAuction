package com.goodee.finalproject.board.notice;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.util.BoardPageMaker;
import com.goodee.finalproject.util.FileManager;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/board/notice/*")
@Slf4j
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private FileManager fileManager;
	
//	@Value("${app.upload.temp}")
//	private String path;

	   
	
	@GetMapping("list")
	public ModelAndView getNoticeList(BoardPageMaker boardPageMaker,Authentication authentication) throws Exception{
		ModelAndView mv = new ModelAndView();
		NoticeVO noticeVO = new NoticeVO();
		List<NoticeVO> test2 = noticeService.getNoticeTotalList(noticeVO);
		boardPageMaker.setPerPage(5L);
		List<NoticeVO> test  = noticeService.getNoticeList(boardPageMaker);
		
		if(authentication != null) {
			MemberVO memberVO= (MemberVO) authentication.getPrincipal();
			mv.addObject("memberVO", memberVO);
		}
		
		mv.addObject("importNotice", test2);
		mv.addObject("testdto", test);
		mv.setViewName("/board/notice/list");
		return mv;
	}
	
	@GetMapping("add")
	public ModelAndView setNoticeAdd(NoticeVO noticeVO,Authentication authentication) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		if(authentication != null) {
			MemberVO memberVO= (MemberVO) authentication.getPrincipal();
			mv.addObject("memberVO", memberVO);
		}
		
		return mv;
	}
	
	@RequestMapping(value="uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request,HttpSession session) throws Exception{

		JsonObject jsonObject = new JsonObject();
		
//		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
//		String path = "C:\\/result\\/upload\\/temp\\/";
		
			String path = "resources/upload/temp/";
			String fileName=null;
			try {
				log.info("----------------파일저장------------");
				log.info("Path -> {}", path);
				String FileName = fileManager.saveFile(path, session.getServletContext(), multipartFile);
				fileName = FileName;
				jsonObject.addProperty("url", "/"+path+"/"+FileName); // contextroot + resources + 저장할 내부 폴더명
				jsonObject.addProperty("responseCode", "success");
					
			} catch (IOException e) {
				//fileManger.deleteFile(fileName, session.getServletContext(), path);
				jsonObject.addProperty("responseCode", "error");
				e.printStackTrace();
				
			}
		
		
		String a = jsonObject.toString();
		return a;
	}
	
	@PostMapping("fileUpdateNumber")
	@ResponseBody
	public int setFileUpdateNumber(NoticeFileVO noticeFileVO) throws Exception{
		
		
		long ROWNUM = noticeFileVO.getRowNum()+1L;
		noticeFileVO.setRowNum(ROWNUM);
		noticeFileVO = noticeService.getFileNumCheck(noticeFileVO);
		
//		int result = noticeService.setFileNumCheckDelete(noticeFileVO);
		int result = noticeFileVO.getNoticeFileNum().intValue();
		
		return result;
	}
	
	@PostMapping("fileDelete")
	@ResponseBody
	public int setAttachFileDelete(NoticeFileVO noticeFileVO) throws Exception{
		
		log.info("NoticeFileVO -> {}", noticeFileVO);
		log.info("NoticeFileVO123 -> {}", noticeFileVO.getRowNum());
		log.info("NoticeFileVO1234 -> {}", noticeFileVO.getNoticeNum());
		long ROWNUM = noticeFileVO.getRowNum()+1L;
		noticeFileVO.setRowNum(ROWNUM);
		noticeFileVO = noticeService.getFileNumCheck(noticeFileVO);
		log.info("------------------------------------------------");
		log.info("NoticeFileVOOOOOO -> {}",noticeFileVO);
		log.info("NoticeFileVOCheck -> {}", noticeFileVO.getNoticeFileNum());
		int result = noticeService.setFileNumCheckDelete(noticeFileVO);
//		int result = noticeFileVO.getNoticeFileNum().intValue();
		
		return result;
	}
	
	
	
	@PostMapping("add")
	public ModelAndView setNoticeAdd(NoticeVO noticeVO, RedirectAttributes redirectAttributes,MultipartFile [] files,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		String contents = noticeVO.getContents();
		
		noticeVO.setContents(contents.replace("temp/","notice"));
		int result = noticeService.setNoticeAdd(noticeVO,files,session.getServletContext());
		noticeService.saveTempFile(noticeVO,session.getServletContext());
//		int result = noticeService.setNoticeAdd(noticeVO);
		mv.setViewName("redirect:./list");
		redirectAttributes.addAttribute("result", result);
		return mv;
	}

	@GetMapping("detail")
	public ModelAndView getNoticeListDetail(NoticeVO noticeVO,Authentication authentication) throws Exception{
		ModelAndView mv = new ModelAndView();
		noticeVO = noticeService.getNoticeListDetail(noticeVO);
		if(authentication != null) {
			MemberVO memberVO= (MemberVO) authentication.getPrincipal();
			mv.addObject("memberVO", memberVO);
		}
		mv.addObject("noticeVO",noticeVO);
		mv.setViewName("/board/notice/detail");
		return mv;
	}
	
	@GetMapping("update")
	public ModelAndView setNoticeUpdate(NoticeVO noticeVO,Model model) throws Exception{
		System.out.println(noticeVO.getNoticeNum());
		
		log.info("noticeNum -> {}", noticeVO.getNoticeNum());
		model.addAttribute("noticeNum", noticeVO.getNoticeNum());
		
		ModelAndView mv = new ModelAndView();
		
		noticeVO = noticeService.getNoticeListDetail(noticeVO);
		
		mv.addObject("noticeUpdate", noticeVO);
		mv.setViewName("/board/notice/update");
		
		return mv;
	}
	
	@PostMapping("update")
	public ModelAndView setNoticeUpdate(NoticeVO noticeVO,MultipartFile [] files,HttpSession session,String [] fileUpdateNumber) throws Exception{
		ModelAndView mv = new ModelAndView();
		if(fileUpdateNumber != null) {
			for(String str : fileUpdateNumber) {
				System.out.println(str);
				log.info("updateNumberCheck -> {}",str);
			}
		}
		
		String contents = noticeVO.getContents();
		
		noticeVO.setContents(contents.replace("temp/","notice"));
		
		
		int result = noticeService.setNoticeUpdate(noticeVO,files,session.getServletContext(),fileUpdateNumber);
		noticeService.saveTempFile(noticeVO,session.getServletContext());
		mv.setViewName("redirect:./list");
		
		return mv;
	}
	
	@GetMapping("delete")
	public ModelAndView setNoticeDelete(NoticeVO noticeVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		NoticeFileVO noticeFileVO = new NoticeFileVO();
		noticeFileVO.setNoticeNum(noticeVO.getNoticeNum());
		noticeService.setFileDelete(noticeFileVO);
		noticeService.setNoticeDelete(noticeVO);
		noticeVO.getNoticeNum();
		
		mv.setViewName("redirect:./list");
		return mv;
	}
}
