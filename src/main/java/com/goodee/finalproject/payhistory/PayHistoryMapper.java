package com.goodee.finalproject.payhistory;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PayHistoryMapper {
	
	public int setPayHistory(PayHistoryVO payHistoryVO) throws Exception;

}
