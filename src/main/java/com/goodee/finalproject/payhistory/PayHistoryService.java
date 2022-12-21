package com.goodee.finalproject.payhistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayHistoryService {
	
	@Autowired
	private PayHistoryMapper payHistoryMapper;
	
	public int setPayHistory(PayHistoryVO payHistoryVO) throws Exception {
		return payHistoryMapper.setPayHistory(payHistoryVO);
	}

	public List<PayHistoryVO> getPayHistoryList(PayHistoryVO payHistoryVO) throws Exception{
		
		return payHistoryMapper.getPayHistoryList(payHistoryVO);
	}
}
