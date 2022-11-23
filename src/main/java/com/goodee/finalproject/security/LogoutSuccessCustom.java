package com.goodee.finalproject.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.goodee.finalproject.socialmember.KakaoVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutSuccessCustom implements LogoutSuccessHandler
{
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String client_id;
	@Value("${kakao.redirect-uri.logout}")
	private String redirect_uri;
	@Value("${kakao.logout}")
	private String logout_uri;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
	{
		log.info("====== logout Success =====");

		KakaoVO kakaoVO = new KakaoVO();
		kakaoVO = (KakaoVO) authentication.getPrincipal();
		String social = kakaoVO.getSocial();
		log.info("kakao social: {}", social);

		if (social != null)
		{
			if (social.equals("kakao"))
			{
				response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id=" + client_id + "&logout_redirect_uri=" + logout_uri);
				log.info("kakao logout");
			}
		}
		// 일반 로그인
		else
		{
			log.info("normal logout");
			response.sendRedirect("/");
		}

	}
}
