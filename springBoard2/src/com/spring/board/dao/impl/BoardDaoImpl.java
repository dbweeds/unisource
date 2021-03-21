package com.spring.board.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.ProductPatternInfoVo;
import com.spring.board.vo.ProductInfoVo;
import com.spring.board.vo.ProductPatternDetailVo;
import com.spring.board.vo.ProductGroupInfoVo;
import com.spring.board.vo.ProductGroupProductVo;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ProductGroupInfoVo> selectProductGroupInfo() throws Exception {

		return sqlSession.selectList("board.ProductGroupInfoList");
	}

	@Override
	public int InsertProductInfo(ProductInfoVo infoVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.InsertProductInfo",infoVo);
	}

	@Override
	public int InsertProductGroupProduct(ProductGroupProductVo GPVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.InsertProductGroupProduct",GPVo);
	}

	@Override
	public int InsertProductGroupInfo(ProductGroupInfoVo PGVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.InsertProductGroupInfo",PGVo);
	}

	@Override
	public int InsertProductPatternInfo(ProductPatternInfoVo PTVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.InsertProductPatternInfo",PTVo);
	}

	@Override
	public int InsertProductPatternDetailVo(ProductPatternDetailVo PDVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("board.InsertProductPatternDetail",PDVo);
	}
	
	
}
