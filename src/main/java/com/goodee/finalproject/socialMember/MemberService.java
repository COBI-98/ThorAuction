package com.goodee.finalproject.socialMember;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class MemberService
{
	@Autowired
	public KakaoMapperIF kakaoMapperIF;

	@Value("${kakao.Admin.key}")
	private String adminKey;

	public int IdCheck(KakaoVO kakaoVO) throws Exception
	{
		return kakaoMapperIF.IdCheck(kakaoVO);
	}

	public int setKakaoDetail(KakaoDetailVO kakaoDetailVO) throws Exception
	{
		return kakaoMapperIF.setKakaoDetail(kakaoDetailVO);
	}

	public int setKakao1(KakaoVO object) throws Exception
	{
		log.info("===== kakao service =====");

		int rs = kakaoMapperIF.setKakao1(object);

		log.info("memberservice set kakao : {}", rs);

		return rs;
	}
}
