package com.goodee.finalproject.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.goodee.finalproject.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MypageService {
	
	@Autowired
	private MypageMapper mypageMapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder; 
	
	// 포인트 충전 + 누적 포인트
	public int ChargePoint(PayVO payVO) throws Exception {
		
		int result = mypageMapper.chargePoint(payVO);
		
		// 결제 완료 시 총포인트 업데이트
		mypageMapper.getPoint(payVO);
		
		return result;
	}
	
	// 비밀번호 체크
	public boolean checkPw(MemberVO memberVO, String checkPassword) throws Exception {
				
		String realPassword = memberVO.getPw();
		
		log.info("서비스 : {}", memberVO);
		log.info("서비스 : {}", memberVO.getId());
		log.info("서비스 : {}", checkPassword);
		log.info("서비스 : {}", realPassword);
		
		boolean matches = encoder.matches(checkPassword, realPassword);
		
		return matches;
	}
	
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
	
	// 마이페이지 정보 출력
	public MemberVO getList(MemberVO memberVO) throws Exception {
		
		return mypageMapper.getList(memberVO);
	}

}
