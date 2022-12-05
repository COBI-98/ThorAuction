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
public class NomalLoginSuccess implements AuthenticationSuccessHandler {
	@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
		
			log.info("i!i!i!i! 일반 로그인 성공~! i!i!i!i!");
			log.info("Auth : {}", authentication);
			log.info("id : {}", request.getParameter("id"));
			
			response.sendRedirect("/");
			
		}

}

