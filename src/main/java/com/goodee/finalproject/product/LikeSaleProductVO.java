package com.goodee.finalproject.product;

import java.util.List;

import lombok.Data;

@Data
public class LikeSaleProductVO {

	private Long likeNum;
	private Long productId;
	private String id;
	
	private List<SaleProductVO> saleProductVOs;
	private List<ProductVO> productVOs;
}
