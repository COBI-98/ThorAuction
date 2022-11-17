package com.goodee.finalproject.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.finalproject.util.BoardPageMaker;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NoticeService  {

	@Autowired
	private NoticeMapper noticeMapper;
	
	
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

	
	public int setNoticeAdd(NoticeVO noticeVO) throws Exception {
		
		return noticeMapper.setNoticeAdd(noticeVO);
	}

	
	public NoticeVO getNoticeListDetail(NoticeVO noticeVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.getNoticeListDetail(noticeVO);
	}

	
	public int setNoticeUpdate(NoticeVO noticeVO) throws Exception {
		
		return noticeMapper.setNoticeUpdate(noticeVO);
	}

	
	public int setNoticeDelete(NoticeVO noticeVO) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.setNoticeDelete(noticeVO);
	}

	
}
