/**
 * 
 */
package com.spring.board.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.UserDao;
import com.spring.board.vo.MenuVo;
import com.spring.board.vo.UserVo;

/**
 * @author user
 *
 */
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int userInsert(UserVo userVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("user.userInsert",userVo);
	}

	@Override
	public int userIdCheack(UserVo userVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.userIdCheack", userVo);
	}
	
	@Override
	public int userPasswordCheack(UserVo userVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.userPasswordCheack", userVo);
	}

	@Override
	public List<MenuVo> SelectCodeTypeList(String code) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(code);
		return sqlSession.selectList("user.codeList", code);
	}

	@Override
	public UserVo userinfo(String userid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("user.userinfo", userid);
	}

}
