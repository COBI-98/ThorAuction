package com.goodee.finalproject.socialmember;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.goodee.finalproject.member.MemberVO;

import lombok.Data;

@Data
public class KakaoVO implements OAuth2User, UserDetails
{
	private String kaNickName;
	private String kaEmail;
	private String kaName;
	
	private List<KaRoleVO> kaRoleVOs;
	private List<KakaoRoleVO> kakaoRoleVOs;
	private List<KakaoDetailVO> kakaoDetailVOs;

	private String social;

	private Map<String, Object> attributes;

	@Override
	public Map<String, Object> getAttributes()
	{
		return this.attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return this.kaName;
	}

	@Override
	public String getUsername()
	{
		// TODO Auto-generated method stub
		return this.kaNickName;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getPassword()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
