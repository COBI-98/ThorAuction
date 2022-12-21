package com.goodee.finalproject.payhistory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PayHistoryMapper {
	
	public int setPayHistory(PayHistoryVO payHistoryVO) throws Exception;

	public List<PayHistoryVO> getPayHistoryList(PayHistoryVO payHistoryVO) throws Exception;
}
