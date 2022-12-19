package com.goodee.finalproject.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginSuccess implements AuthenticationSuccessHandler
{
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException
	{
		log.info("======= 로그인 성공 ====== ");
		log.info("Auth: {}", authentication);

		response.sendRedirect("/socialMember/kakaoLogin");

	}
}
