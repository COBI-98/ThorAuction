package com.goodee.finalproject.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.finalproject.board.notice.NoticeFileVO;
import com.goodee.finalproject.board.notice.NoticeVO;
import com.goodee.finalproject.util.FileManager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	public ProductVO getProductApproval(ProductVO productVO) throws Exception{
		
		return productMapper.getProductApproval(productVO);
	}
	
	public int setStatus(ProductVO productVO) throws Exception{
		return productMapper.setStatus(productVO);
	}
	
}
