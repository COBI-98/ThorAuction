package com.goodee.finalproject.pay;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayMapper {
	
	public int chargePoint(PayVO payVO) throws Exception;

}
