package com.goodee.finalproject.socialmember;

import java.util.List;

import lombok.Data;

@Data
public class KakaoRoleVO
{
	private Long kaRole;
	private String kaNickName;
	
	private List<KaRoleVO> kaRoleVOs;
}
