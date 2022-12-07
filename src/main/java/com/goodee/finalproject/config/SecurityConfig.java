	package com.goodee.finalproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.goodee.finalproject.security.LoginSuccess;
import com.goodee.finalproject.security.LogoutCustom;
import com.goodee.finalproject.security.NomalLoginFail;
import com.goodee.finalproject.security.NomalLoginSuccess;
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
	private LoginSuccess loginSuccess;
	
	// 일반 로그인 
	@Autowired
	private NomalLoginSuccess nomalLoginSuccess;
	
	@Autowired
	private NomalLoginFail nomalLoginFail;

	@Bean
	WebSecurityCustomizer webSecurityCustomizer()
	{

		return web -> web
				.ignoring()
				.antMatchers("/images/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("favicon/**")
				.antMatchers("/resources/**")
		;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity 
				.cors() 
				.and() 
				.csrf() 
				.disable() 
			.authorizeRequests() 
				.antMatchers("/admin").hasRole("AMDIN") 
				.anyRequest().permitAll() 
				.and()
			.formLogin()
				.loginPage("/member/login")
				.passwordParameter("pw")
				.usernameParameter("id")
				.successHandler(nomalLoginSuccess)
				//.defaultSuccessUrl("/")
				.failureHandler(nomalLoginFail)
				.permitAll()
				.and()
				// ---------- 로그아웃 시작 ---------------
			.logout() // 로그 아웃시
				.logoutUrl("/member/logout") // 로그아웃
				.addLogoutHandler(logoutCustom) // 로그아웃 시 실행
				.invalidateHttpSession(true) // Session 내용 없애기
				.deleteCookies("JSESSIONID") // 쿠키 삭제
				.permitAll() //
				// ---------- 로그아웃 끝 ---------------
				.and() //
			.oauth2Login() //
				.successHandler(loginSuccess)
				.userInfoEndpoint() //
				.userService(memberSocialService) // 소셜 로그인 실행 클래스
		;

		return httpSecurity.build();

	}
	
	// 평문(Clear Text)을 암호화 시켜주는 객체생성
	// 비밀번호 암호화
	@Bean
	PasswordEncoder getEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
//	// AuthenticationManager Bean 생성	
//	@Bean
//	AuthenticationManager authenticationManagerBean() throws Exception {
//		
//		return authenticationManagerBean();
//	}
}
