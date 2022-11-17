package com.goodee.finalproject.board.notice;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.goodee.finalproject.board.impl.BoardMapper;
import com.goodee.finalproject.board.impl.BoardVO;

@Mapper
public interface NoticeMapper  {

	public List<NoticeVO> getNoticeList(NoticeVO noticeVO) throws Exception;

}
