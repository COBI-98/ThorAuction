package com.goodee.finalproject.socialmember;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class NaverVO implements OAuth2User, UserDetails
{
	private String NaNickName;
	private String NaEmail;
	private String NaName;

	private String NaSocial;

	private Map<String, Object> attriutes;

	@Override
	public Map<String, Object> getAttributes()
	{
		// TODO Auto-generated method stub
		return this.attriutes;
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
		return this.NaNickName;
	}
	
	public String getEmail()
	{
		// TODO Auto-generated method stub
		return this.NaEmail;
	}

	@Override
	public String getPassword()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername()
	{
		// TODO Auto-generated method stub
		return this.NaName;
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
}
