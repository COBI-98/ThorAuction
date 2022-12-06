package com.goodee.finalproject.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.goodee.finalproject.socialmember.KakaoVO;
import com.goodee.finalproject.socialmember.NaverVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutCustom implements LogoutHandler
{
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String client_id;
	@Value("${kakao.logout}")
	private String logout_uri;
	@Value("${spring.security.oauth2.client.registration.naver.client-id}")
	private String naverClient;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	{
		log.info("=========== logout Handler ===========");

		// 1. 일반 로그인 or 소셜 로그인 판별
		// log.info("authentication: {}", authentication.getClass());
		// log.info("social 가져오자: {}", authentication.getPrincipal().getClass().toString());

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
			if (authentication.getPrincipal().getClass().toString().equals("class com.goodee.finalproject.socialmember.KakaoVO"))
			{
				try
				{
					response.sendRedirect(
							"https://kauth.kakao.com/oauth/logout?client_id=" + client_id + "&logout_redirect_uri=" + logout_uri);
					request.getSession().invalidate();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (authentication.getPrincipal().getClass().toString().equals("class com.goodee.finalproject.socialmember.NaverVO"))
			{
				try
				{
					response.sendRedirect("https://nid.naver.com/nidlogin.logout=" + logout_uri);
//					response.sendRedirect("/");
					request.getSession().invalidate();
					// request.removeAttribute(client_id);
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
