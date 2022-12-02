package com.goodee.finalproject.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutCustom implements LogoutHandler
{
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String client_id;
	@Value("${kakao.redirect-uri.logout}")
	private String redirect_uri;
	@Value("${kakao.logout}")
	private String logout_uri;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	{
		log.info("=========== logout Handler ===========");

		// 1. 일반 로그인 or 소셜 로그인 판별
		// log.info("authentication getSocial: {}", authentication.getPrincipal());
		
		if (authentication == null)
		{
			try
			{
				response.sendRedirect("/");
				request.getSession().invalidate();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id=" + client_id + "&logout_redirect_uri=" + logout_uri);
				request.getSession().invalidate();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
