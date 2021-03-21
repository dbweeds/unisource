package com.spring.board.dao;

import java.util.List;

import com.spring.board.vo.ProductPatternInfoVo;
import com.spring.board.vo.ProductInfoVo;
import com.spring.board.vo.ProductPatternDetailVo;
import com.spring.board.vo.ProductGroupInfoVo;
import com.spring.board.vo.ProductGroupProductVo;

public interface BoardDao {

	public List<ProductGroupInfoVo> selectProductGroupInfo() throws Exception;
	
	public int InsertProductInfo(ProductInfoVo infoVo) throws Exception;

	public int InsertProductGroupProduct(ProductGroupProductVo GPVo) throws Exception;
	
	public int InsertProductGroupInfo(ProductGroupInfoVo PGVo) throws Exception;

	public int InsertProductPatternInfo(ProductPatternInfoVo PTVo) throws Exception;
	
	public int InsertProductPatternDetailVo(ProductPatternDetailVo PDVo) throws Exception;


}
