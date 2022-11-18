package com.goodee.finalproject.socialMember;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Data;

@Data
public class KakaoVO implements OAuth2User, UserDetails
{
	// `KANICKNAME` VARCHAR(100) NOT NULL,
	// `KAEMAIL` VARCHAR(100) NOT NULL,
	// `KABIRTH` INT NOT NULL,
	// `KANAME` VARCHAR(100) NOT NULL,
	// `KAPOST` VARCHAR(100) NOT NULL,
	// `KAADDR` VARCHAR(100) NOT NULL,
	// `KAADDRDETAIL` VARCHAR(255) NOT NULL,
	// `KAPHONE` VARCHAR(100) NOT NULL,
	// `KAPOINT` INT NOT NULL,

	private String kaNickName;
	private String kaEmail;
	private Long kaBirth;
	private String kaName;
	private String kaPost;
	private String kaAddr;
	private String kaAddrDetail;
	private String kaPhone;
	private Long kaPoint;

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
