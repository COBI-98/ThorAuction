package com.goodee.finalproject.product;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class SaleProductVO {
	private Long productId;
	private Long productNum;
	private Long categoryId;
	private Boolean deadlineInfo;
	private Long productHit;
	private Timestamp productDate;

	private List<ProductVO> productVOs;
	private List<CategoryVO> categoryVOs;
}
