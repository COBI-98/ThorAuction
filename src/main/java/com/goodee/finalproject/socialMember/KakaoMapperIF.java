package com.goodee.finalproject.socialMember;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KakaoMapperIF
{
	public int setKakaoMember(KakaoVO kakaoVO) throws Exception;
}
