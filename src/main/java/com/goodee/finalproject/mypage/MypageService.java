package com.goodee.finalproject.mypage;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.finalproject.member.MemberVO;

@Service
public class MypageService {
	
	@Autowired
	private MypageMapper mypageMapper;
	
	// 회원탈퇴
	public int setDelete(MemberVO memberVO) throws Exception {
		
		int result = mypageMapper.setDelete(memberVO);
		
		return result;
	}
	
	// 회원정보 수정
	public int setUpdate(MemberVO memberVO) throws Exception {
		
		int result = mypageMapper.setUpdate(memberVO);
		
		return result;
	}

}
