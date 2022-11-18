package com.goodee.finalproject.socialMember;

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
	@Value("${kakao.Admin.key}")
	private String adminKey;

	// 소셜 회원 탈퇴
	public int SocialDelete(KakaoVO kakaoVO) throws Exception
	{
		WebClient webClient = WebClient.builder() //
				.baseUrl("https://kapi.kakao.com/") //
				.build();

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("target_id_type", "user_id");
		map.add("target_id", kakaoVO.getKaNickName());

		Mono<String> Mono = webClient.post() //
				.uri("v1/user/unlink")//
				.body(BodyInserters.fromFormData(map)) //
				.header("Authorization", "KakaoAK " + adminKey) //
				.header("Content-Type", " application/x-www-form-urlencoded") //
				.retrieve() //
				.bodyToMono(String.class) //
		;

		return 1;
	}
}
