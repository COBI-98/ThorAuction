package com.goodee.finalproject.product;

import java.sql.Date;

import lombok.Data;

@Data
public class AdminQuestionVO {
	private Long aqNum;
	private String aqContents;
	private Date aqDate;
	private Long questionId;
	private String name;
	
}
