package com.goodee.finalproject.product;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BidAmountVO {

	private Long amountId;
	private Long productId;
	private String id;
	private Long bidAmount;
	private Timestamp bidDate;
	
}
