package com.goodee.finalproject.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NomalLoginFail implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		log.info("====== LoginFail =====");
		
		String result = null;
		// 참조변수명 instanceof 클래스명
		if(exception instanceof BadCredentialsException) {
			result = "비밀번호가 틀렸습니다.";
		} else if(exception instanceof InternalAuthenticationServiceException) {
			result = "가입되지 않은 아이디입니다.";
		} else {
			result = "로그인 실패!";
		}
		
		// redirect
		result = URLEncoder.encode(result, "UTF-8");
		response.sendRedirect("/member/login?error=true&message="+result);

	}

}
