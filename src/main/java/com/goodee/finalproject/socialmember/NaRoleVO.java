package com.goodee.finalproject.socialmember;

import lombok.Data;

@Data
public class NaRoleVO
{
	private Long NaRole;
	private String NaRoleName;

	public String getNaRoleName()
	{
		return this.NaRoleName;
	}
}
