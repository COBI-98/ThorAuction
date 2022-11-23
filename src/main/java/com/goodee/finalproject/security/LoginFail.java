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
public class LoginFail implements AuthenticationFailureHandler
{
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException
	{
		log.info("===== LoginFail =====");
		log.info("ClassName: {}", exception.getClass().toString());
		log.info("LocalMessage: {}", exception.getLocalizedMessage());
		log.info("Cause: {}", exception.getCause());
		log.info("Message: {}", exception.getMessage());

		// String name = exception.getClass().toString();
		// name = name.substring(name.lastIndexOf(".") + 1);
		// if (name.equals("BadCredentialsException"))
		// {
		// name = "비번 에러";
		// }
		// else if (name.equals("InternalAuthenticationServiceException"))
		// {
		// name = "아이디 틀림";
		// }
		/* 위와 아래 같음 */
		String rs = null;
		if (exception instanceof BadCredentialsException)
		{
			rs = "비밀번호가 일치하지 않습니다.";
		}
		else if (exception instanceof InternalAuthenticationServiceException)
		{
			rs = "아이디가 일치하지 않습니다.";
		}
		// 아이디 비밀번호 전부 일치하지 않을 때
		else
		{
			rs = "로그인 실패";
		}

		// name = URLEncoder.encode(name, "UTF-8");
		// rs = URLEncoder.encode(rs, "UTF-8");
		// response.sendRedirect("/member/login?error=true&message=" + name);
		// POST 방식으로 Controller의 메서드 요청 함 - MemberController POST로 감
		request.setAttribute("msg", rs);
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
		// response.sendRedirect("/member/login?error=true&message=" + rs);
	}
}
