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
	private Long AmountUnit;
	
	// comment용 ref 추가

	// 등록순,가격순 체크용
	private String sc; 
	
	public String getSc() {
		if(sc ==null) {
			sc = "1";
		}
		
		return sc;
	}
	
	public Boolean getDeadlineInfo() {
		if(deadlineInfo==null) {
			deadlineInfo = false;
		}
		return deadlineInfo;
	}
	
	public Long maxBidAmount;
	
	private List<ProductVO> productVOs;
	private List<CategoryVO> categoryVOs;
	private List<BidAmountVO> bidAmountVOs;
	private List<ProductQuestionVO> productQuestionVOs;
}
