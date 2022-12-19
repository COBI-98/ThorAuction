package com.goodee.finalproject.product;

import java.sql.Date;

import lombok.Data;

@Data
public class ProductQuestionVO {
	private Long questionId;
	private String questionTitle;
	private String questionContents;
	private Date questionDate;
	private Long productId;
	private String id;
	
	private AdminQuestionVO adminQuestionVO;
	
}
