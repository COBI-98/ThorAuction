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
			result = "ID가 존재하지 않거나 비밀번호가 일치하지 않습니다. 다시 시도해주세요.";
		} else if(exception instanceof InternalAuthenticationServiceException) {
			result = "ID가 존재하지 않거나 비밀번호가 일치하지 않습니다. 다시 시도해주세요.";
		} else {
			result = "알 수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.";
		}
		
		// redirect
		result = URLEncoder.encode(result, "UTF-8");
		response.sendRedirect("/member/login?error=true&message="+result);

	}

}
