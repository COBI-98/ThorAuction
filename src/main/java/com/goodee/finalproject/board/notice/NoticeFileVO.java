package com.goodee.finalproject.board.notice;

import lombok.Data;

@Data
public class NoticeFileVO {
	private Long rowNum;
	private Long noticeFileNum;
	private String noticeFileName;
	private String noticeOriName;
	private Long noticeNum;
	private Boolean sort;
	
	public Boolean getSort() {
		if(sort==null) {
			sort = false;
		}
		return sort;
	}
}
