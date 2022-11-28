package com.goodee.finalproject.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/mypage/*")
@Slf4j
public class PayController {
	
	@Autowired
	private PayService payService;
	
	@PostMapping("charge/point")
	@ResponseBody
	public void chargePoint(PayVO payVO) throws Exception {
//		int result = payService.ChargePoint(payVO);
		
		log.info("나와주세요~ : {}", payVO.getPayUid());
		log.info("나와주세요~ : {}", payVO.getPayTotal());
		log.info("나와주세요~ : {}", payVO.getPayResult());
		log.info("나와주세요~ : {}", payVO.getPayDate());
		log.info("나와주세요~ : {}", payVO.getId());
		
		
//		return result;
		
	}
}
