
package com.goodee.finalproject.mypage;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.finalproject.member.MemberVO;

@Mapper
public interface MypageMapper {
	
	// 총 포인트
	public int getPoint(PayVO payVO) throws Exception;
	
	// 포인트 충전
	public int chargePoint(PayVO payVO) throws Exception;
	
	// 회원탈퇴
	public int setDelete(MemberVO memberVO) throws Exception;
	
	// 회원정보 수정
	public int setUpdate(MemberVO memberVO) throws Exception;
	
	// 마이페이지 정보 출력
	public MemberVO getList(MemberVO memberVO) throws Exception;

}
