package com.goodee.finalproject.board.application;

import java.sql.Date;
import java.util.List;

import com.goodee.finalproject.board.impl.BoardVO;
import com.goodee.finalproject.product.ProductVO;

import lombok.Data;

@Data
public class ApplicationVO extends ProductVO {

	private Long applicationNum;
	private String writer;
	private String title;
	private Date regDate;
	private Boolean auctionSort;
	
	private List<ProductVO> productVOs;
}
