package com.goodee.finalproject.board.notice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.goodee.finalproject.board.impl.BoardVO;
import com.goodee.finalproject.product.ProductVO;

import lombok.Data;

@Data
public class NoticeVO extends BoardVO {
	private Long noticeNum;
	private Boolean importCheck;
	
	private MultipartFile [] files;
	
	
	public Boolean getImportCheck() {
		if(importCheck==null) {
			importCheck = false;
		}
		return importCheck;
	}
	
	private List<NoticeFileVO> noticeFileVOs;
}
