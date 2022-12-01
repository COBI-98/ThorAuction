package com.goodee.finalproject.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.finalproject.board.notice.NoticeFileVO;
import com.goodee.finalproject.board.notice.NoticeVO;
import com.goodee.finalproject.util.FileManager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private FileManager fileManager;
	
	public int setProductAdd(ProductVO productVO,MultipartFile [] files,ServletContext servletContext) throws Exception {
		
		int result = productMapper.setProductAdd(productVO);
		
		String path = "resources/upload/product" ;
		
		log.info("------------------------service----------------");
		log.info("noticeVO ->{}", files.length);
		if(files.length != 0) {
			for(MultipartFile file : files) {
				log.info("test1 => {}", file);
				log.info("test1 => {}", file.isEmpty());
				if(!file.isEmpty()) {
					
					String fileName = fileManager.saveFile(path,servletContext, file);
					ProductFileVO productFileVO = new ProductFileVO();
					productFileVO.setFileName(fileName);
					productFileVO.setOriName(file.getOriginalFilename());
					productFileVO.setProductNum(productVO.getProductNum());
					productMapper.setProductFileAdd(productFileVO);
				
					}
				}
			}
		return result;
	}
	
	public void saveTempFile(ProductVO productVO,ServletContext servletContext) throws Exception{
		
		String path = "resources/upload/temp/";
		String realpath = servletContext.getRealPath(path);
		String [] contents = productVO.getProductInformation().split("src=\"/");
		String [] split = null;
		ArrayList<String> src = new ArrayList<String>();
		
		for(int i = 1; i < contents.length ; i++) {
			split = contents[i].split("\"");
			src.add(split[0]); ///resources/upload/QNA/filename
		}
		ProductFileVO productFileVO = new ProductFileVO();
		for(String ar : src){
				String copyFilePath = servletContext.getRealPath(ar); // upload/QNA
			    String oriFilePath = copyFilePath.replace("notice","temp\\"); // upload\temp\id
			    File copyfile = new File(copyFilePath);
			    System.out.println("======파일 복사 실행 전");
			    if(!copyfile.exists()) {
			    	System.out.println("======파일 복사");
			    	FileInputStream fis = new FileInputStream(oriFilePath); //읽을파일
			        FileOutputStream fos = new FileOutputStream(copyFilePath); //복사할파일
			        log.info("oriFilePath -> {}", oriFilePath);
			        log.info("copyFilePath -> {}", copyFilePath);
			        int data = 0;
			        while((data=fis.read())!=-1) {
			         fos.write(data);
			        }
			        fis.close();
			        fos.close();
			    }
			    String [] fileNames = ar.split("/");
			    String fileName = fileNames[fileNames.length-1];
			    String [] fileOriNames = fileName.split("_",2);
			    String fileOriName = fileOriNames[1];
			    System.out.println("fileOriName = " + fileOriName);
			    
			    
				
				productFileVO.setFileName(fileName);
				productFileVO.setOriName(fileOriName);
				productFileVO.setProductNum(productVO.getProductNum());
				productFileVO.setSort(true);
				productMapper.setProductFileAdd(productFileVO);
			
//			serviceDAO.addBoardFile(boardFileDTO);
		        
		}
		File folder = new File(realpath);
        if(folder.exists()) {
        	productMapper.setSummerFileDelete(productFileVO);
        	File[] files = folder.listFiles();
        	
        	for(File file : files) {
        		System.out.println("list File ! =" + file.getName());
        		file.delete();
        	}
        	
            folder.delete();
                
        }
        
		
		
	}
	
	
}
