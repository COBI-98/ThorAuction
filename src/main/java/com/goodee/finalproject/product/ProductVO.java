package com.goodee.finalproject.product;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductVO {
	private Long productNum;
	private String productName;
	private Long productPrice;
	private String productInformation;
	private Long auctionPeriod;
	private Long applicationNum;
	
	private MultipartFile [] files;
	
	private List<ProductFileVO> productFileVOs;
	
}
