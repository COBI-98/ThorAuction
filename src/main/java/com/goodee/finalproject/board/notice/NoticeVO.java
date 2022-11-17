package com.goodee.finalproject.board.notice;

import java.util.List;

import com.goodee.finalproject.board.impl.BoardVO;
import com.goodee.finalproject.product.ProductVO;

import lombok.Data;

@Data
public class NoticeVO extends BoardVO {
	private Long noticeNum;
	
	
	private List<NoticeFileVO> noticeFileVOs;
}
