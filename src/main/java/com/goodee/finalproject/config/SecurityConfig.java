package com.goodee.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import com.goodee.finalproject.security.LoginSuccess;
import com.goodee.finalproject.security.LogoutCustom;
import com.goodee.finalproject.security.LogoutSuccessCustom;
import com.goodee.finalproject.socialmember.MemberSocialService;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
	@Autowired
	private MemberSocialService memberSocialService;
	@Autowired
	private LogoutCustom logoutCustom;
	@Autowired
	private LogoutSuccessCustom logoutSuccessCustom;
	@Autowired
	private LoginSuccess loginSuccess;

	@Bean
	WebSecurityCustomizer webSecurityCustomizer()
	{

		return web -> web //
				.ignoring() //
				.antMatchers("/images/**") //
				.antMatchers("/css/**") //
				.antMatchers("/js/**") //
				.antMatchers("favicon/**") //
				.antMatchers("/resources/**") //
		;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity //
				.cors() //
				.and() //
				.csrf() //
				.disable() //
				.authorizeRequests() //
				.antMatchers("/admin").hasRole("AMDIN") //
				.anyRequest().permitAll() //
				.and() //
				.oauth2Login() //
				.successHandler(loginSuccess).and()//
				// ---------- 로그아웃 시작 ---------------
				.logout() // 로그 아웃시
				.logoutUrl("/member/logout") // 로그아웃
				.addLogoutHandler(logoutCustom) // 로그아웃 시 실행
//				.logoutSuccessHandler(logoutSuccessCustom) // 로그아웃 성공 시 실행
				.invalidateHttpSession(true) // Session 내용 없애기
				.deleteCookies("JSESSIONID") // 쿠키 삭제
				.permitAll() //
				// ---------- 로그아웃 끝 ---------------
				.and() //
				.oauth2Login() //
				.userInfoEndpoint() //
				.userService((OAuth2UserService<OAuth2UserRequest, OAuth2User>) memberSocialService) // 소셜 로그인 실행 클래스
		;

		return httpSecurity.build();

	}
}
