package com.goodee.finalproject.board.impl;

import java.util.List;

import com.goodee.finalproject.product.ProductVO;

import lombok.Data;


@Data
public class BoardVO {
	private String title;
	private String writer;
	private String contents;
	private String regDate;
	private Long hit;
	
	

}
