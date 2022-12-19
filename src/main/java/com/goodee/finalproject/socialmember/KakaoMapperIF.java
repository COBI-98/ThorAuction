package com.goodee.finalproject.socialmember;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Mapper
public interface KakaoMapperIF
{
	public int setKakao1(Object object) throws Exception;

	public int setKakaoDetail(KakaoDetailVO kakaoDetailVO) throws Exception;

	public int setKaRole(KakaoVO kakaoVO) throws Exception;

	public int IdCheck(KakaoVO kakaoVO) throws Exception;
	
	public KakaoDetailVO getKakaoDetail(KakaoVO kakaoVO) throws Exception;
	
	public int setPoint(KakaoDetailVO kakaoDetailVO) throws Exception;
	
	public KakaoDetailVO getOneMember(KakaoDetailVO kakaoDetailVO) throws Exception;
	
	public KakaoDetailVO getDetail(KakaoDetailVO kakaoDetailVO) throws Exception;
}
