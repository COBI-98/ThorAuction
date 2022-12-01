package com.goodee.finalproject.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeMapperTest {

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Test
	void test3() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		int result = 0;
		for(int i = 0; i<30;i++) {

			noticeVO.setTitle("Title"+i);
			noticeVO.setWriter("COBI");
			noticeVO.setContents("Contents"+i);
			noticeVO.setImportCheck(false);
			result = noticeMapper.setNoticeAdd(noticeVO);	
			
		}
		assertNotEquals(0, result);
	}

}
