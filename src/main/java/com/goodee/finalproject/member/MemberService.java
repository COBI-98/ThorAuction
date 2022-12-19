package com.goodee.finalproject.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.goodee.finalproject.mypage.MypageMapper;

@Service
public class MemberService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private MypageMapper mypageMapper;
	
	// 비밀번호 암호화해주는 메서드
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 메일 내용을 생성하고 임시 비밀번호로 회원 비밀번호를 변경
//	public MailVO createMailAndChangePassword(String email) throws Exception {
//		String str = getTempPassword();
//		
//		MailVO mailVO = new MailVO();
//		
//		mailVO.setAddress(email);
//		mailVO.setTitle("[bidcoin] 임시비밀번호 안내");
//		mailVO.setMessage("요청하신 임시비밀번호를 안내해 드립니다.\r\n"
//				+ "임시비밀번호로 로그인 후 원하시는 비밀번호로 변경하시기 바랍니다." + str);
//		
//		
//	}
//	
//	// 임시 비밀번호로 업데이트
//	public void updatePassword(String str, String email) {
//		
//		MemberVO memberVO = new MemberVO();
//				
//		String memberPassword = str;
//		String id = memberVO.getId();
//		mypageMapper.setUpdate(id, pw);
//	}
//	
//	// 랜덤함수로 임시비밀번호 구문 만드는 메서드
//	public String getTempPassword() throws Exception {
//		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
//                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
//		
//		String str = "";
//		
//		// 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성
//		int idx = 0;
//		for(int i = 0; i < 10; i++) {
//			idx = (int)(charSet.length * Math.random());
//			str += charSet[idx];
//		}
//		
//		return str;
//	}
	
	// 아이디 중복확인
	public MemberVO getIdCheck(String id) throws Exception {
		
		return memberMapper.getIdCheck(id);
	}
	
	// 로그인
//	public MemberVO getLogin(MemberVO memberVO) throws Exception {
//		
//		return memberMapper.getLogin(memberVO);
//	}
	
	// 회원가입 + 회원등급
	public int setJoin(MemberVO memberVO) throws Exception {
		
		// 비밀번호 암호화
		memberVO.setPw(passwordEncoder.encode(memberVO.getPw()));
		
		int result = memberMapper.setJoin(memberVO);
		
		// 회원등급
		memberMapper.setRole(memberVO);
		
		return result;
	}
	
	public int setPoint(MemberVO memberVO) throws Exception{
		int result = memberMapper.setPoint(memberVO);
		return result;
	}
	
	public MemberVO getOneMember(MemberVO memberVO) throws Exception{
		return memberMapper.getOneMember(memberVO);
	}

}
