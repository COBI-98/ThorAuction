package com.goodee.finalproject.board.application;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.goodee.finalproject.board.impl.BoardMapper;
import com.goodee.finalproject.board.impl.BoardVO;
import com.goodee.finalproject.product.SaleProductVO;
import com.goodee.finalproject.board.application.ApplicationVO;
import com.goodee.finalproject.util.BoardPageMaker;

@Mapper
public interface ApplicationMapper  {
	
	public List<ApplicationVO> getApplicationList(BoardPageMaker boardPageMaker) throws Exception;

	public Long getApplicationCount(BoardPageMaker boardPageMaker) throws Exception;
	
	public int setApplicationAdd(ApplicationVO applicationVO) throws Exception;

	public ApplicationVO getApplicationListDetail(ApplicationVO applicationVO) throws Exception;
	
	public int setApplicationUpdate(ApplicationVO applicationVO) throws Exception;
	
	public int setApplicationDelete(ApplicationVO applicationVO) throws Exception;
	
	public List<ApplicationVO> getApprovalCheckList() throws Exception;
	
	public int setApprovalCheckUpdate(ApplicationVO applicationVO) throws Exception;

}
