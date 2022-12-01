package com.goodee.finalproject.board.notice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.finalproject.util.BoardPageMaker;
import com.goodee.finalproject.util.FileManager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NoticeService  {

	@Autowired
	private NoticeMapper noticeMapper;
	
//	@Value("${app.upload.qna}")
//	private String path;
	
	@Autowired
	private FileManager fileManager;
	
	
	public List<NoticeVO> getNoticeList(BoardPageMaker boardPageMaker) throws Exception{ 
		
		Long totalCount = noticeMapper.getCount(boardPageMaker);
		
		if(totalCount == 0L) {
			totalCount = 1L;
		}
		boardPageMaker.getNum(totalCount);
		boardPageMaker.getRowNum();
		log.info("totalCount :  {}", totalCount);

		log.info("boardPageMake -> {} ", boardPageMaker);
		return noticeMapper.getNoticeList(boardPageMaker);
	}
	
	public List<NoticeVO> getNoticeTotalList(NoticeVO noticeVO) throws Exception{
		
		return noticeMapper.getNoticeTotalList(noticeVO);
	}


	
	public int setNoticeAdd(NoticeVO noticeVO,MultipartFile [] files,ServletContext servletContext) throws Exception {
		
		int result = noticeMapper.setNoticeAdd(noticeVO);
		
		String path = "resources/upload/notice" ;
		
		log.info("------------------------service----------------");
		log.info("noticeVO ->{}", files.length);
		if(files.length != 0) {
			for(MultipartFile file : files) {
				log.info("test1 => {}", file);
				log.info("test1 => {}", file.isEmpty());
				if(!file.isEmpty()) {
					
					String fileName = fileManager.saveFile(path,servletContext, file);
					NoticeFileVO noticeFileVO = new NoticeFileVO();
					
					noticeFileVO.setNoticeFileName(fileName);
					noticeFileVO.setNoticeOriName(file.getOriginalFilename());
					noticeFileVO.setNoticeNum(noticeVO.getNoticeNum());
					log.info("fileTest1 =>",file.getOriginalFilename());
					log.info("fileTest2 =>",fileName);
					
					noticeMapper.setNoticeFileAdd(noticeFileVO);
					
					}
				}
			}
		return result;
	}

	
	public NoticeVO getNoticeListDetail(NoticeVO noticeVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeListDetail(noticeVO);
	}

	
	public int setNoticeUpdate(NoticeVO noticeVO,MultipartFile [] files,ServletContext servletContext,String [] fileUpdateNumber) throws Exception {
		NoticeFileVO noticeFileVO = new NoticeFileVO();
		noticeFileVO.setNoticeNum(noticeVO.getNoticeNum());
		log.info("noticeFileVO -> {}", noticeFileVO.getNoticeNum());
		noticeMapper.setFileDeleteSort(noticeFileVO); //delete 
		int result = noticeMapper.setNoticeUpdate(noticeVO);
		String path = "resources/upload/notice" ;
	
		log.info("------------------------update----------------");
		log.info("noticeVO ->{}", files.length);

		int count = 0;
		log.info("filesLength -> {}", files.length);
		if(files.length != 0) {
			for(MultipartFile file : files) {
				
				log.info("count -> {}", count);
				
				if(!file.isEmpty()) {
					
					String fileName = fileManager.saveFile(path,servletContext, file);
					noticeFileVO = new NoticeFileVO();
					
					
						
					
					if(fileUpdateNumber != null) {
						if(fileUpdateNumber.length-1 < count) {
							System.out.println("!!");
							noticeFileVO = new NoticeFileVO();
							
							noticeFileVO.setNoticeFileName(fileName);
							noticeFileVO.setNoticeOriName(file.getOriginalFilename());
							noticeFileVO.setNoticeNum(noticeVO.getNoticeNum());
							log.info("fileTest1 -> {}",file.getOriginalFilename());
							log.info("fileTest2 -> {}",fileName);
							
							noticeMapper.setNoticeFileAdd(noticeFileVO);
						}else {
						System.out.println(Long.parseLong(fileUpdateNumber[count]));
						noticeFileVO.setNoticeFileNum(Long.parseLong(fileUpdateNumber[count]));
						noticeFileVO.setNoticeFileName(fileName);
						noticeFileVO.setNoticeOriName(file.getOriginalFilename());
//						noticeFileVO.setNoticeNum(noticeVO.getNoticeNum());
						log.info("fileTest1 -> {}",file.getOriginalFilename());
						log.info("fileTest2 -> {}",fileName);
						log.info("fileTest3 -> {}", noticeFileVO.getNoticeFileNum());
						
						noticeMapper.setNoticeFileUpdate(noticeFileVO);
						count++;
						}
					}else if(fileUpdateNumber == null)  {
						log.info("--------------없음=---------");
						noticeFileVO = new NoticeFileVO();
						
						noticeFileVO.setNoticeFileName(fileName);
						noticeFileVO.setNoticeOriName(file.getOriginalFilename());
						noticeFileVO.setNoticeNum(noticeVO.getNoticeNum());
						log.info("fileTest1 -> {}",file.getOriginalFilename());
						log.info("fileTest2 -> {}",fileName);
						
						noticeMapper.setNoticeFileAdd(noticeFileVO);
					}
					
					
					}
					
				}
			}
		
		return result;
	}

	
	public NoticeFileVO getFileCheck(NoticeFileVO noticeFileVO) throws Exception{
		
		return noticeMapper.getFileCheck(noticeFileVO);
	}
	
	public int setNoticeDelete(NoticeVO noticeVO) throws Exception {
		// TODO Auto-generated method stub
		
		return noticeMapper.setNoticeDelete(noticeVO);
	}
	
	public void saveTempFile(NoticeVO noticeVO,ServletContext servletContext) throws Exception{
		
		String path = "resources/upload/temp/";
		String realpath = servletContext.getRealPath(path);
		String [] contents = noticeVO.getContents().split("src=\"/");
		String [] split = null;
		ArrayList<String> src = new ArrayList<String>();
		
		for(int i = 1; i < contents.length ; i++) {
			split = contents[i].split("\"");
			src.add(split[0]); ///resources/upload/QNA/filename
		}
		NoticeFileVO noticeFileVO = new NoticeFileVO();
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
			    
			    
				
				noticeFileVO.setNoticeFileName(fileName);
				noticeFileVO.setNoticeOriName(fileOriName);
				noticeFileVO.setNoticeNum(noticeVO.getNoticeNum());
				noticeFileVO.setSort(true);
				noticeMapper.setNoticeFileAdd(noticeFileVO);
//			serviceDAO.addBoardFile(boardFileDTO);
		        
		}
		File folder = new File(realpath);
        if(folder.exists()) {
        	noticeMapper.setNoteFileDelete(noticeFileVO);
        	File[] files = folder.listFiles();
        	
        	for(File file : files) {
        		System.out.println("list File ! =" + file.getName());
        		file.delete();
        	}
        	
            folder.delete();
                
        }
        
		
		
	}

	public NoticeFileVO getFileNumCheck(NoticeFileVO noticeFileVO) throws Exception{
		
		return noticeMapper.getFileNumCheck(noticeFileVO);
	}
	
	public int setFileNumCheckDelete(NoticeFileVO noticeFileVO) throws Exception{
		
		return noticeMapper.setFileNumCheckDelete(noticeFileVO);
	}
	
	public int setFileDelete(NoticeFileVO noticeFileVO) throws Exception{
		
		return noticeMapper.setFileDelete(noticeFileVO);
	}
	
}
