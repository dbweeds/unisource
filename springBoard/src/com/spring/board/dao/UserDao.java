package com.spring.board.dao;

import java.util.List;

import com.spring.board.vo.BoardVo;
import com.spring.board.vo.MenuVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserVo;

public interface UserDao {

	public int userInsert(UserVo userVo) throws Exception;
	
	public int userIdCheack(UserVo userVo) throws Exception;
	
	public UserVo userinfo(String userid) throws Exception;
	
	public int userPasswordCheack(UserVo userVo) throws Exception;
	
	public List<MenuVo> SelectCodeTypeList(String code) throws Exception;

}
