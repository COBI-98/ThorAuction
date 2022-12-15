package com.goodee.finalproject.payhistory;

import java.sql.Date;

import lombok.Data;

@Data
public class PayHistoryVO {
	private int payNum;
	private String id;
	private int cashe;
	private Date regDate;
	private int productNum;

}
