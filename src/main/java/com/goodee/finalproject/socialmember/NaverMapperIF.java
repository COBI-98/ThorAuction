package com.goodee.finalproject.socialmember;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NaverMapperIF
{
	public int setNaver(Object object) throws Exception;

	public int setNaverDetail(NaverDetailVO naverDetailVO) throws Exception;

	public int setNaRole(NaverVO naverVO) throws Exception;
}
