package com.goodee.finalproject.product;

import java.util.List;

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

	// 상품 승인 구현 메퍼
	public ProductVO getProductApproval(ProductVO productVO) throws Exception;
	
	// 상품 게시
	public int setSaleProduct(SaleProductVO saleProductVO) throws Exception;
	
	// 상품리스트
	public List<SaleProductVO> getSaleProductList(SaleProductVO saleProductVO) throws Exception;
	
	// 카테고리리스트
	public List<CategoryVO> getCategoryList(CategoryVO categoryVO) throws Exception;

	public SaleProductVO getSaleProductListDetail(SaleProductVO saleProductVO) throws Exception;

	// 온라인상품이 마감일이 지났을때 마감정보 1로 변경
	public int setDeadLineUpdate(SaleProductVO saleProductVO) throws Exception;

}
