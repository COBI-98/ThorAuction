package com.goodee.finalproject.product;

import lombok.Data;

@Data
public class ProductFileVO {
	private Long rowNum;
	private Long fileNum;
	private String fileName;
	private String oriName;
	private Long productNum;
	private Boolean sort;
	
	public Boolean getSort() {
		if(sort==null) {
			sort = false;
		}
		return sort;
	}
	
}
