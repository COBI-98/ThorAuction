package com.goodee.finalproject.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutCustom implements LogoutHandler
{
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	{
		log.info("=========== logout Handler ===========");

		// 1. 일반 로그인 or 소셜 로그인 판별
//		log.info("authentication getSocial: {}", authentication.getPrincipal());
		
		request.getSession().invalidate();
		
//		try
//		{
//			response.sendRedirect("/");
//		} catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
