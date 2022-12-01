package com.goodee.finalproject.board.impl;

import java.sql.Date;
import java.util.List;

import com.goodee.finalproject.product.ProductVO;

import lombok.Data;


@Data
public class BoardVO {
	private String title;
	private String writer;
	private String contents;
	private Date regDate;
	private Long hit;
	
	

}
