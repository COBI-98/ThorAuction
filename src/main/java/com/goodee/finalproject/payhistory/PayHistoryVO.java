package com.goodee.finalproject.payhistory;

import java.sql.Date;
import java.util.List;

import com.goodee.finalproject.product.ProductVO;

import lombok.Data;

@Data
public class PayHistoryVO {
	private int payNum;
	private String id;
	private int cashe;
	private Date regDate;
	private int productNum;

	private List<ProductVO> productVOs;
}
