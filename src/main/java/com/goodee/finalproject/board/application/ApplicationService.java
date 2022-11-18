package com.goodee.finalproject.board.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.finalproject.board.application.ApplicationMapper;
import com.goodee.finalproject.board.application.ApplicationVO;
import com.goodee.finalproject.util.BoardPageMaker;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationService {

	@Autowired
	private ApplicationMapper applicationMapper;
	
	
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

	
	public int setApplicationAdd(ApplicationVO applicationVO) throws Exception {
		
		return applicationMapper.setApplicationAdd(applicationVO);
	}

	
	public ApplicationVO getApplicationListDetail(ApplicationVO applicationVO) throws Exception {
		// TODO Auto-generated method stub
		return applicationMapper.getApplicationListDetail(applicationVO);
	}

	
	public int setApplicationUpdate(ApplicationVO applicationVO) throws Exception {
		
		return applicationMapper.setApplicationUpdate(applicationVO);
	}

	
	public int setApplicationDelete(ApplicationVO applicationVO) throws Exception {
		// TODO Auto-generated method stub
		return applicationMapper.setApplicationDelete(applicationVO);
	}
	
}
