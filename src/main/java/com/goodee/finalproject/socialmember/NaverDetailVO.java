package com.goodee.finalproject.socialmember;

import lombok.Data;

@Data
public class NaverDetailVO
{
	private String NaNickName;
	private String NaBirth;
	private String NaPost;
	private String NaAddr;
	private String NaAddrDetail;
	private String NaPhone;
	private Long NaPoint;

	public String getNaBirth()
	{
		return this.NaBirth;
	}

	public String getNaPost()
	{
		return this.NaPost;
	}

	public String getNaAddr()
	{
		return this.NaAddr;
	}

	public String getNaAddrDetail()
	{
		return this.NaAddrDetail;
	}

	public String getNaPhone()
	{
		return this.NaPhone;
	}

	public Long getNaPoint()
	{
		return this.NaPoint;
	}
}
