package com.goodee.finalproject.board.application;

import java.util.List;

import com.goodee.finalproject.board.impl.BoardVO;
import com.goodee.finalproject.product.ProductVO;

import lombok.Data;

@Data
public class ApplicationVO extends BoardVO {

	private Long applicationNum;
	private List<ProductVO> productVOs;
}
