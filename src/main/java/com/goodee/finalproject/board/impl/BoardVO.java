package com.goodee.finalproject.board.impl;

import java.util.List;

import com.goodee.finalproject.product.ProductVO;

import lombok.Data;

@Data
public class BoardVO {
	private Long boardNum;
	private String title;
	private String writer;
	private String contents;
	private String regDate;
	
	private List<ProductVO> productVOs;

}
