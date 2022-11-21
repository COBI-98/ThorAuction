package com.goodee.finalproject.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
	{
		log.info("======= 로그인 성공 ====== ");
		log.info("Auth: {}", authentication);
		log.info("ID: {}", request.getParameter("kaNickName"));

//		String ch = request.getParameter("rememberId");
//		log.info("check: {}", ch);
//		if (ch != null && ch.equals("on"))
//		{
//			Cookie cookie = new Cookie("userId", request.getParameter("id"));
//			cookie.setHttpOnly(true); // cookie를 클라이언트에서 숨길건지
//			cookie.setMaxAge(60); // 클라이언트에 얼마나 저장할지 단위: 초 -> 60 입력 시 60초
//			cookie.setPath("/"); // 같은 도메인(영역 단위) 내에서 어느 URL에서 사용가능한지
//
//			response.addCookie(cookie);
//		}
//		else
//		{
//			Cookie[] cookies = request.getCookies();
//
//			for (Cookie cookie : cookies)
//			{
//				if (cookie.getName().equals("userId"))
//				{
//					cookie.setMaxAge(0);
//					cookie.setPath("/"); // cookie 삭제 시 cookie 만들 때의 path와 동일하지 않으면 삭제 안됨
//					response.addCookie(cookie);
//				}
//			}
//		}

		response.sendRedirect("/member/kakaoLogin");
	}
}
