package com.goodee.finalproject.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.AbstractView;

import com.goodee.finalproject.board.notice.NoticeFileVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileManager extends AbstractView{
	
	@Value("${app.download.base}")
	private String base;
	
	// 상속 받아주는 메소드
	// 얘가 view 가 됨
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		NoticeFileVO noticeFileVO = (NoticeFileVO) model.get("fileVO");
		String path = (String) model.get("path");
		
		
		log.info("------------");
		log.info("base -> {}", base);
		log.info("path -> {}", path);
		log.info("FILEVO {}", noticeFileVO.getNoticeFileName());
		
		File file = new File(base+path+"/",noticeFileVO.getNoticeFileName());
		
		// 한글 처리
		response.setCharacterEncoding("UTF-8");
		
		// 총 파일의 크기
		response.setContentLengthLong(file.length());
		
		// client가 다운로드 시 파일의 이름을 encoding 
		String oriName = URLEncoder.encode(noticeFileVO.getNoticeOriName(), "UTF-8");
		
		// header
		response.setHeader("Content-Disposition", "attachment;filename=\""+oriName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//HDD에서 파일을 읽을 준비
		FileInputStream fi = new FileInputStream(file);
		//Client 로 전송준비
		OutputStream os = response.getOutputStream();
		
		//전송
		FileCopyUtils.copy(fi, os);
		
		// 자원해제
		os.close();
		fi.close();
		
	}
	
	
	
	public String saveFile(String path,ServletContext servletContext,MultipartFile multipartFile) throws Exception{
		
		String realPath = servletContext.getRealPath(path);
		System.out.println("realPath:" + realPath);
		
		
		File file = new File(realPath);
		if(!file.exists()) {
			file.mkdirs();	
		}
		
		String fileName = UUID.randomUUID().toString() + "_" + multipartFile.getOriginalFilename();
		
		file = new File(file,fileName);
		multipartFile.transferTo(file);
		
		
		return fileName;
			
		
		
	}
	
	//delete
    public boolean deleteFile(String fileName,ServletContext servletContext,String path) throws Exception{
       String realPath = servletContext.getRealPath(path);
       System.out.println(realPath);
       
       File file = new File(realPath, fileName);
       
       return file.delete();
    }
	

}
