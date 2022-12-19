
package com.goodee.finalproject.mypage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.finalproject.board.application.ApplicationVO;
import com.goodee.finalproject.member.MemberVO;
import com.goodee.finalproject.product.BidAmountVO;
import com.goodee.finalproject.product.LikeSaleProductVO;
import com.goodee.finalproject.product.SaleProductVO;

@Mapper
public interface MypageMapper {
	
	// 신청한 상품
	public List<ApplicationVO> appProduct(ApplicationVO applicationVO) throws Exception;
	
	// 관심 상품 삭제
	public int deleteLikeProduct(LikeSaleProductVO likeSaleProductVO) throws Exception;
	
	// 관심내역
	public List<LikeSaleProductVO> likeProduct(LikeSaleProductVO likeSaleProductVO) throws Exception;
	
	// 입찰한 상품 수
	public int productCount(BidAmountVO bidAmountVO) throws Exception;
	
	// 내가 입찰한 상품의 입찰가, 입찰시간
	public List<BidAmountVO> bidHistory(BidAmountVO bidAmountVO) throws Exception;
	
	// 내가 입찰한 상품의 최고가
	public Long bidMaxHistory(BidAmountVO bidAmountVO) throws Exception;
	
	// 입찰한 상품 정보
	public List<SaleProductVO> bidProductInformation(BidAmountVO bidAmountVO) throws Exception;
	
	// 결제 내역
	public List<PayVO> payHistory(PayVO payVO) throws Exception;
	
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
