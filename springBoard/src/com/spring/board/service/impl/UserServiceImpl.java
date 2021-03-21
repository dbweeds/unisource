package com.spring.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.UserDao;
import com.spring.board.service.UserService;
import com.spring.board.vo.MenuVo;
import com.spring.board.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Override
	public int userInsert(UserVo userVo) throws Exception {
		// TODO Auto-generated method stub
		return userDao.userInsert(userVo);
	}

	@Override
	public int userIdCheack(UserVo userVo) throws Exception {
		// TODO Auto-generated method stub
		return userDao.userIdCheack(userVo);
	}
	public int userPasswordCheack(UserVo userVo) throws Exception {
		// TODO Auto-generated method stub
		return userDao.userPasswordCheack(userVo);
	}

	@Override
	public List<MenuVo> SelectCodeTypeList(String codeType) throws Exception {
		// TODO Auto-generated method stub
		return userDao.SelectCodeTypeList(codeType);
	}

	@Override
	public UserVo userinfo(String userid) throws Exception {
		// TODO Auto-generated method stub
		return userDao.userinfo(userid);
	}

}
