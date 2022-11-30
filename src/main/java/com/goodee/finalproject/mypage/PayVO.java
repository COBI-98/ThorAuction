package com.goodee.finalproject.mypage;

import com.goodee.finalproject.member.MemberVO;

import lombok.Data;

@Data
public class PayVO extends MemberVO{
	private String payUid;
	private Long payTotal;
	private String payResult;
	private String payDate;

}
