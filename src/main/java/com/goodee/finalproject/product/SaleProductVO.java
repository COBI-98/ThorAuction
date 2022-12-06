package com.goodee.finalproject.product;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class SaleProductVO {
	private Long productId;
	private Long productNum;
	private Long categoryId;
	private Boolean deadlineInfo;
	private Long productHit;
	private Date productDate;

	private List<ProductVO> productVOs;
}
