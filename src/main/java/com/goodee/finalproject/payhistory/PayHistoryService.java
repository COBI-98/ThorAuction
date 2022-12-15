package com.goodee.finalproject.payhistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayHistoryService {
	
	@Autowired
	private PayHistoryMapper payHistoryMapper;
	
	public int setPayHistory(PayHistoryVO payHistoryVO) throws Exception {
		return payHistoryMapper.setPayHistory(payHistoryVO);
	}

}
