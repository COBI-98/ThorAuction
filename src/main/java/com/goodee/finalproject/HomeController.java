package com.goodee.finalproject;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController
{
	// 카카오 로그인
	// @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	// private String KakaoClientID;

	@GetMapping("/")
	public String home(HttpSession session) throws Exception
	{
		// 1. RestTemplate 객체 생성
//		RestTemplate restTemplate = new RestTemplate();
//		// 2. 헤더 생성
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("key", "value");
//		// 3. 파라미터 생성
//		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
//		param.add("key", "value");
//		// 4. 요청 객체 생성
//		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<MultiValueMap<String, String>>(param, headers);
//		// 5. 요청 전송 결과 처리

		return "index";
	}
}
