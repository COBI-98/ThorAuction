package com.goodee.finalproject.pay;

import java.sql.Date;

import lombok.Data;

@Data
public class PayVO {
	private Long payUid;
	private Long payTotal;
	private Long payResult;
	private Date payDate;
	
	
}
