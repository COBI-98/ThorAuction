package com.goodee.finalproject.socialMember;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Mapper
public interface KakaoMapperIF
{
	public int setKakaoMember(KakaoVO kakaoVO) throws Exception;
}
