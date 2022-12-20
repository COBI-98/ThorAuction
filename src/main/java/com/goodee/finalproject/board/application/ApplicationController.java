package com.goodee.finalproject.board.application;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.goodee.finalproject.board.application.ApplicationVO;
import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.product.ProductFileVO;
import com.goodee.finalproject.product.ProductService;
import com.goodee.finalproject.product.ProductVO;
import com.goodee.finalproject.util.BoardPageMaker;
import com.goodee.finalproject.util.FileManager;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board/application/*")
@Slf4j
public class ApplicationController {

	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ProductService productService; 
	
	@Autowired
	private FileManager fileManager;
	

	@GetMapping("list")
	public ModelAndView getApplicationList(BoardPageMaker boardPageMaker,Authentication authentication) throws Exception{
		ModelAndView mv = new ModelAndView();
		if(authentication != null) {
			MemberVO memberVO= (MemberVO) authentication.getPrincipal();
			mv.addObject("memberVO", memberVO);
		}
		
		boardPageMaker.setPerPage(5L);
		List<ApplicationVO> test  = applicationService.getApplicationList(boardPageMaker);
		mv.addObject("applicationVO", test);

		
		mv.setViewName("/board/application/list");
		return mv;
	}
	
	@GetMapping("add")
	public void setApplicationAdd(ApplicationVO applicationVO) throws Exception{
		
	}
	
	@PostMapping("add")
	public ModelAndView setApplicationAdd(ApplicationVO applicationVO, MultipartFile [] files,
			RedirectAttributes redirectAttributes,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = applicationService.setApplicationAdd(applicationVO,files,session.getServletContext());
		String contents = applicationVO.getProductInformation();
		applicationVO.setProductInformation(contents.replace("temp/","product"));
		applicationService.saveTempFile(applicationVO,session.getServletContext());
		mv.setViewName("redirect:./list");
		
		redirectAttributes.addAttribute("result", result);
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getApplicationListDetail(ApplicationVO applicationVO,Authentication authentication) throws Exception{
		ModelAndView mv = new ModelAndView();
		ApplicationVO VO = applicationService.getApplicationListDetail(applicationVO);
		if(authentication != null) {
			MemberVO memberVO= (MemberVO) authentication.getPrincipal();
			mv.addObject("memberVO", memberVO);
		}
		mv.addObject("applicationVO",VO);
		mv.setViewName("/board/application/detail");
		return mv;
	}
	
	@GetMapping("update")
	public ModelAndView setApplicationUpdate(ApplicationVO applicationVO,Model model) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		model.addAttribute("applicationNum", applicationVO.getApplicationNum());
		
		ApplicationVO VO = applicationService.getApplicationListDetail(applicationVO);
		
		mv.addObject("applicationUpdate", VO);
		mv.setViewName("/board/application/update");
		
		return mv;
	}
	
	@PostMapping("update")
	public ModelAndView setApplicationUpdate(ApplicationVO applicationVO,MultipartFile [] files,HttpSession session,String [] fileUpdateNumber) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = applicationService.setApplicationUpdate(applicationVO,files,session.getServletContext(),fileUpdateNumber);
		
		String contents = applicationVO.getProductInformation();
		applicationVO.setProductInformation(contents.replace("temp/","product"));
		
		applicationService.saveTempFile(applicationVO,session.getServletContext());
		
		mv.setViewName("redirect:./list");
		
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
	public int setFileUpdateNumber(ProductFileVO productFileVO) throws Exception{
		
		long ROWNUM = productFileVO.getRowNum()+1L;
		productFileVO.setRowNum(ROWNUM);
		productFileVO = applicationService.getFileNumCheck(productFileVO);
		log.info("------------------------------------------------");
		log.info("productFileVOOOOOO -> {}",productFileVO);
		log.info("productFileVOCheck -> {}", productFileVO.getFileNum());
//		int result = noticeService.setFileNumCheckDelete(productFileVO);
		int result = productFileVO.getFileNum().intValue();
		
		return result;
	}
	
	@PostMapping("fileDelete")
	@ResponseBody
	public int setAttachFileDelete(ProductFileVO productFileVO) throws Exception{
		
		
		long ROWNUM = productFileVO.getRowNum()+1L;
		productFileVO.setRowNum(ROWNUM);
		productFileVO = applicationService.getFileNumCheck(productFileVO);
		log.info("------------------------------------------------");
		log.info("productFileVOOOOOO -> {}",productFileVO);
		log.info("productFileVOCheck -> {}", productFileVO.getFileNum());
		int result = applicationService.setFileNumCheckDelete(productFileVO);
//		int result = productFileVO.getNoticeFileNum().intValue();
		
		return result;
	}
	
	
	
	@GetMapping("delete")
	public ModelAndView setApplicationDelete(ApplicationVO applicationVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		ProductVO productVO = new ProductVO();
		ProductFileVO productFileVO = new ProductFileVO();
		
		productVO.setApplicationNum(applicationVO.getApplicationNum());
		productVO = applicationService.getProductNumCheck(productVO);
		productFileVO.setProductNum(productVO.getProductNum());
				
		/*파일삭제
		  상품삭제*/
		applicationService.setFileDelete(productFileVO);
		System.out.println(productVO.getApplicationNum());
		applicationService.setProductDelete(productVO);
		
		applicationService.setApplicationDelete(applicationVO);
		mv.setViewName("redirect:./list");
		return mv;
	}
	
	@PostMapping("approvalCheckUpdate")
	@ResponseBody
	public int setApprovalCheckUpdate(ApplicationVO applicationVO) throws Exception{
		int result = applicationService.setApprovalCheckUpdate(applicationVO);
		
		
		return result;
	}
}
