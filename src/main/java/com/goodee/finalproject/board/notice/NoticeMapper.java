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

	public Long getCount(BoardPageMaker boardPageMaker) throws Exception;
	
	public int setNoticeAdd(NoticeVO noticeVO) throws Exception;

	public NoticeVO getNoticeListDetail(NoticeVO noticeVO) throws Exception;
	
	public int setNoticeUpdate(NoticeVO noticeVO) throws Exception;
	
	public int setNoticeDelete(NoticeVO noticeVO) throws Exception;
}
