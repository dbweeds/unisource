package com.spring.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.boardService;
import com.spring.board.vo.ProductPatternInfoVo;
import com.spring.board.vo.ProductInfoVo;
import com.spring.board.vo.ProductPatternDetailVo;
import com.spring.board.vo.ProductGroupInfoVo;
import com.spring.board.vo.ProductGroupProductVo;

@Service
public class boardServiceImpl implements boardService{
	
	@Autowired
	BoardDao boardDao;

	@Override
	public List<ProductGroupInfoVo> selectProductGroupInfo() throws Exception {
		return boardDao.selectProductGroupInfo();
	}

	@Override
	public int InsertProductInfo(ProductInfoVo infoVo) throws Exception {
		
		return boardDao.InsertProductInfo(infoVo);
	}

	@Override
	public int InsertProductGroupProduct(ProductGroupProductVo GPVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.InsertProductGroupProduct(GPVo);
	}

	@Override
	public int InsertProductGroupInfo(ProductGroupInfoVo PGVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.InsertProductGroupInfo(PGVo);
	}

	@Override
	public int InsertProductPatternInfo(ProductPatternInfoVo PTVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.InsertProductPatternInfo(PTVo);
	}

	@Override
	public int InsertProductPatternDetailVo(ProductPatternDetailVo PDVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.InsertProductPatternDetailVo(PDVo);
	}
	

	
}
