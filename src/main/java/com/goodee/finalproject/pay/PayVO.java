package com.goodee.finalproject.pay;

import java.sql.Date;

import com.goodee.finalproject.member.MemberVO;

import lombok.Data;

@Data
public class PayVO extends MemberVO{
	private Long payUid;
	private Long payTotal;
	private Long payResult;
	private Date payDate;
	
	
}
