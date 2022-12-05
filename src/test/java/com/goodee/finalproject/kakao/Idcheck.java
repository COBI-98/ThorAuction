package com.goodee.finalproject.kakao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.goodee.finalproject.socialmember.KakaoVO;
import com.goodee.finalproject.socialmember.MemberSocialService;

@SpringBootTest
class Idcheck
{
	@Autowired
	private MemberSocialService memberSocialService;

//	@Test
	void test() throws Exception
	{
		KakaoVO kakaoVO = new KakaoVO();
		kakaoVO.setKaNickName("2526670487");
		kakaoVO.setKaName("김진규");
		kakaoVO.setKaEmail("kjg08070@naver.com");
		
		int rs = memberSocialService.IdCheck(kakaoVO);
		
		assertEquals(1, rs);
	}

}
