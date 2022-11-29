package com.goodee.finalproject.mypage;

import java.sql.Date;

import com.goodee.finalproject.member.MemberVO;

import lombok.Data;

@Data
public class PayVO extends MemberVO{
	private String payUid;
	private Long payTotal;
	private String payResult;
	private String payDate;

}
