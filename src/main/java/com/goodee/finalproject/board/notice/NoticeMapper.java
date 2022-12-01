package com.goodee.finalproject.board.notice;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.goodee.finalproject.board.impl.BoardMapper;
import com.goodee.finalproject.board.impl.BoardVO;
import com.goodee.finalproject.util.BoardPageMaker;

@Mapper
public interface NoticeMapper  {

	public List<NoticeVO> getNoticeList(BoardPageMaker boardPageMaker) throws Exception;

	public List<NoticeVO> getNoticeTotalList(NoticeVO noticeVO) throws Exception; 
	
	public Long getCount(BoardPageMaker boardPageMaker) throws Exception;
	
	public int setNoticeAdd(NoticeVO noticeVO) throws Exception;
	
	public int setNoticeFileAdd(NoticeFileVO noticeFileVO) throws Exception;

	public int setFileDeleteSort(NoticeFileVO noticeFileVO) throws Exception;
	
	public int setFileDelete(NoticeFileVO noticeFileVO) throws Exception;

	public NoticeFileVO getFileNumCheck(NoticeFileVO noticeFileVO ) throws Exception;
	
	public int setFileNumCheckDelete(NoticeFileVO noticeFileVO) throws Exception;
	
	public int setNoteFileDelete(NoticeFileVO noticeFileVO) throws Exception; 
	
	public NoticeFileVO getFileCheck(NoticeFileVO noticeFileVO) throws Exception;
	
	public NoticeVO getNoticeListDetail(NoticeVO noticeVO) throws Exception;
	
	public int setNoticeUpdate(NoticeVO noticeVO) throws Exception;
	
	public int setNoticeDelete(NoticeVO noticeVO) throws Exception;

	public int setNoticeFileUpdate(NoticeFileVO noticeFileVO) throws Exception;
}
