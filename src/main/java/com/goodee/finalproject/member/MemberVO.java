package com.goodee.finalproject.member;

import java.util.List;

import lombok.Data;

@Data
public class MemberVO {

	private String id;
	private String pw;
	private String name;
	private Long birth;
	private String email;
	private String post;
	private String addr;
	private String addr2;
	private String phone;
	private Long point;
	
	private List<RoleVO> roleVOs;
}
