package com.goodee.finalproject.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.finalproject.member.MemberVO;

@Service
public class PayService {
	
	@Autowired
	private PayMapper payMapper;
	
	public int ChargePoint(PayVO payVO) throws Exception{
		
		return payMapper.chargePoint(payVO);
	}
}
