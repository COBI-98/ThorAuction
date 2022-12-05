package com.goodee.finalproject.board.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.finalproject.board.application.ApplicationMapper;
import com.goodee.finalproject.board.application.ApplicationVO;
import com.goodee.finalproject.product.ProductFileVO;
import com.goodee.finalproject.product.ProductMapper;
import com.goodee.finalproject.product.ProductVO;
import com.goodee.finalproject.util.BoardPageMaker;
import com.goodee.finalproject.util.FileManager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationService {

	@Autowired
	private ApplicationMapper applicationMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private FileManager fileManager;
	
	public List<ApplicationVO> getApplicationList(BoardPageMaker boardPageMaker) throws Exception{ 
		
		Long totalCount = applicationMapper.getApplicationCount(boardPageMaker);
		
		if(totalCount == 0L) {
			totalCount = 1L;
		}
		boardPageMaker.getNum(totalCount);
		boardPageMaker.getRowNum();
		log.info("totalCount :  {}", totalCount);

		log.info("boardPageMake -> {} ", boardPageMaker);
		return applicationMapper.getApplicationList(boardPageMaker);
	}

	
	public int setApplicationAdd(ApplicationVO applicationVO,MultipartFile [] files,ServletContext servletContext) throws Exception {
		int result = applicationMapper.setApplicationAdd(applicationVO);
		
		String path = "resources/upload/product" ;
		
		ProductVO productVO = new ProductVO();
		String contents = applicationVO.getProductInformation();
		productVO.setProductName(applicationVO.getProductName());
		productVO.setProductPrice(applicationVO.getProductPrice());
		productVO.setAuctionPeriod(applicationVO.getAuctionPeriod());
		productVO.setProductInformation(contents.replace("temp/","product"));
		productVO.setApplicationNum(applicationVO.getApplicationNum());
		productMapper.setProductAdd(productVO);
		applicationVO.setProductNum(productVO.getProductNum());
		log.info("------------------------service----------------");
		log.info("productVO ->{}", files.length);
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

	
	public ApplicationVO getApplicationListDetail(ApplicationVO applicationVO) throws Exception {
		// TODO Auto-generated method stub
		return applicationMapper.getApplicationListDetail(applicationVO);
	}

	
	public int setApplicationUpdate(ApplicationVO applicationVO,MultipartFile [] files,ServletContext servletContext,String [] fileUpdateNumber) throws Exception {
		ProductVO productVO = new ProductVO();
		ProductFileVO productFileVO = new ProductFileVO();
		String contents = applicationVO.getProductInformation();
		productVO.setProductName(applicationVO.getProductName());
		productVO.setProductPrice(applicationVO.getProductPrice());
		productVO.setAuctionPeriod(applicationVO.getAuctionPeriod());
		productVO.setProductInformation(contents.replace("temp/","product"));
		productVO.setProductNum(applicationVO.getProductNum());
//		productMapper.setProductAdd(productVO);
//		applicationVO.setProductNum(productVO.getProductNum());
		
		log.info("------------------------Update----------------");
		

		productFileVO.setProductNum(applicationVO.getProductNum());
		productMapper.setFileDeleteSort(productFileVO);
		String path = "resources/upload/product" ;
		int result = applicationMapper.setApplicationUpdate(applicationVO);
		productMapper.setProductUpdate(productVO);
		
		log.info("applicationVO -> {}",applicationVO.getProductNum());
		int count = 0;
		log.info("productVO ->{}", files.length);
		if(files.length != 0) {
			for(MultipartFile file : files) {
				
				log.info("count -> {}", count);
				
				if(!file.isEmpty()) {
					
					String fileName = fileManager.saveFile(path,servletContext, file);
					
					
					
						
					
					if(fileUpdateNumber != null) {
						if(fileUpdateNumber.length-1 < count) {
							System.out.println("!!");
							
							productFileVO.setFileName(fileName);
							productFileVO.setOriName(file.getOriginalFilename());
							productFileVO.setProductNum(productVO.getProductNum());
							log.info("fileTest1 -> {}",file.getOriginalFilename());
							log.info("fileTest2 -> {}",fileName);
							
							productMapper.setProductFileAdd(productFileVO);
						}else {
						System.out.println(Long.parseLong(fileUpdateNumber[count]));
						productFileVO.setFileNum(Long.parseLong(fileUpdateNumber[count]));
						productFileVO.setFileName(fileName);
						productFileVO.setOriName(file.getOriginalFilename());
//						productFileVO.setproductNum(productVO.getproductNum());
						log.info("fileTest1 -> {}",file.getOriginalFilename());
						log.info("fileTest2 -> {}",fileName);
						log.info("fileTest3 -> {}", productFileVO.getFileNum());
						
						productMapper.setProductFileUpdate(productFileVO);
						count++;
						}
					}else if(fileUpdateNumber == null)  {
						log.info("--------------없음=---------");
						productFileVO.setFileName(fileName);
						productFileVO.setOriName(file.getOriginalFilename());
						productFileVO.setProductNum(productVO.getProductNum());
						log.info("fileTest1 -> {}",file.getOriginalFilename());
						log.info("fileTest2 -> {}",fileName);
						
						productMapper.setProductFileAdd(productFileVO);
					}
					
					
					}
					
				}
			}
		return result;
	}

	
	public int setApplicationDelete(ApplicationVO applicationVO) throws Exception {
		// TODO Auto-generated method stub
		return applicationMapper.setApplicationDelete(applicationVO);
	}
	
	public void saveTempFile(ApplicationVO applicationVO,ServletContext servletContext) throws Exception{
		
		String path = "resources/upload/temp/";
		String realpath = servletContext.getRealPath(path);
		String [] contents = applicationVO.getProductInformation().split("src=\"/");
		String [] split = null;
		ArrayList<String> src = new ArrayList<String>();
		
		for(int i = 1; i < contents.length ; i++) {
			split = contents[i].split("\"");
			src.add(split[0]); ///resources/upload/QNA/filename
		}
		ProductFileVO productFileVO = new ProductFileVO();
		for(String ar : src){
				String copyFilePath = servletContext.getRealPath(ar); // upload/QNA
			    String oriFilePath = copyFilePath.replace("product","temp\\"); // upload\temp\id
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
				productFileVO.setProductNum(applicationVO.getProductNum());
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
	public ProductFileVO getFileNumCheck(ProductFileVO productFileVO) throws Exception{
		
		return productMapper.getFileNumCheck(productFileVO);
	}
	
	public int setFileNumCheckDelete(ProductFileVO productFileVO) throws Exception{
		
		return productMapper.setFileNumCheckDelete(productFileVO);
	}
	
	public int setProductDelete(ProductVO productVO) throws Exception{
		
		return productMapper.setProductDelete(productVO);
	}
	
	public int setFileDelete(ProductFileVO productFileVO) throws Exception{
		
		return productMapper.setFileDelete(productFileVO);
	}
	
	public ProductVO getProductNumCheck(ProductVO productVO) throws Exception{
		
		return productMapper.getProductNumCheck(productVO);
	}
	
	public List<ApplicationVO> getApprovalCheckList() throws Exception{
		return applicationMapper.getApprovalCheckList();
	}
}
