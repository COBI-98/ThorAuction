package com.goodee.finalproject.product;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

	public int setProductAdd(ProductVO productVO) throws Exception;
	
	public int setProductFileAdd(ProductFileVO productFileVO) throws Exception;
	
	public int setSummerFileDelete(ProductFileVO productFileVO) throws Exception;

	public ProductFileVO getFileNumCheck(ProductFileVO productFileVO) throws Exception;
	
	public int setFileNumCheckDelete(ProductFileVO productFileVO) throws Exception;
	
	public int setProductUpdate(ProductVO productVO) throws Exception;
	
	public int setProductFileUpdate(ProductFileVO productFileVO) throws Exception;

	public int setFileDeleteSort(ProductFileVO productFileVO) throws Exception;

	public int setProductDelete(ProductVO productVO) throws Exception;
	
	public int setFileDelete(ProductFileVO productFileVO) throws Exception;

	public ProductVO getProductNumCheck(ProductVO productVO) throws Exception;
}
