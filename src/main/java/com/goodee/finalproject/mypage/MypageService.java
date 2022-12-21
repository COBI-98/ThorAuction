package com.goodee.finalproject.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.goodee.finalproject.board.application.ApplicationVO;
import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.product.BidAmountVO;
import com.goodee.finalproject.product.LikeSaleProductVO;
import com.goodee.finalproject.product.SaleProductVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MypageService {
	
	@Autowired
	private MypageMapper mypageMapper;
	
	// 암호화된 비밀번호와 VO에 있는 비밀번호 비교해주는 메서드
//	@Autowired
//	private BCryptPasswordEncoder encoder; 
	
	// 비밀번호 암호화해주는 메서드, 암호화된 비밀번호와 VO에 있는 비밀번호 비교해주는 메서드
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 신청한 상품
	public List<ApplicationVO> appProduct(ApplicationVO applicationVO) throws Exception {
		
		return mypageMapper.appProduct(applicationVO);
	}
	
	// 관심 상품 삭제
	public int deleteLikeProduct(LikeSaleProductVO likeSaleProductVO) throws Exception {
		
		int result = mypageMapper.deleteLikeProduct(likeSaleProductVO);
		
		return result;
	}
	
	// 관심내역
	public List<LikeSaleProductVO> likeProduct(LikeSaleProductVO likeSaleProductVO) throws Exception {
		
		return mypageMapper.likeProduct(likeSaleProductVO);
	}
	
	// 입찰한 상품 수
	public int productCount(BidAmountVO bidAmountVO) throws Exception {
		
		return mypageMapper.productCount(bidAmountVO);
	}
	
	
	// 내가 입찰한 상품의 입찰가, 입찰시간
	public List<BidAmountVO> bidHistory(BidAmountVO bidAmountVO) throws Exception {
		
		return mypageMapper.bidHistory(bidAmountVO);
	}
	
	// 내가 입찰한 상품의 최고가
	public Long bidMaxHistory(BidAmountVO bidAmountVO) throws Exception {
		
		return mypageMapper.bidMaxHistory(bidAmountVO);
	}
	
	// 입찰한 상품 정보
	public List<SaleProductVO> bidProductInformation(BidAmountVO bidAmountVO) throws Exception {
		
		return mypageMapper.bidProductInformation(bidAmountVO);
	}
	
	// 결제내역
	public List<PayVO> payHistory(PayVO payVO) throws Exception {
		
		return mypageMapper.payHistory(payVO);
	}
	
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
		
		boolean matches = passwordEncoder.matches(checkPassword, realPassword);
		
		return matches;
	}
	
	// 회원탈퇴
	public int setDelete(MemberVO memberVO) throws Exception {
		
		int result = mypageMapper.setDelete(memberVO);
		
		log.info("서비스 delete : {}", memberVO);
		
		return result;
	}
	
	// 비밀번호 수정
	public int setUpdatePw(MemberVO memberVO) throws Exception {
		
		// 비밀번호 암호화
		memberVO.setPw(passwordEncoder.encode(memberVO.getPw()));
		
		int result = mypageMapper.setUpdate(memberVO);
		
		return result;
	}
	
	// 회원정보 수정
	public int setUpdate(MemberVO memberVO) throws Exception {
		
		int result = mypageMapper.setUpdate(memberVO);
		
		log.info("서비스 UPDAte : {}", memberVO);
		
		return result;
	}
	
	// 마이페이지 정보 출력
	public MemberVO getList(MemberVO memberVO) throws Exception {
		
		return mypageMapper.getList(memberVO);
	}

}
