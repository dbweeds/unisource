package com.spring.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.HomeController;
import com.spring.board.service.UserService;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.MenuVo;
import com.spring.board.vo.PageVo;
import com.spring.board.vo.UserVo;
import com.spring.common.CommonUtil;

@Controller
public class UserController {
	
	@Autowired 
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private String codeType = "phone";
	
	@RequestMapping(value = "/user/userJoinAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String userJoinAction(Locale locale,UserVo userVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = userService.userInsert(userVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	@RequestMapping(value = "/user/userIdCheackAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String userIdCheackAction(Locale locale,UserVo userVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = userService.userIdCheack(userVo);
		
		result.put("cheack", (resultCnt > 0)?"F":"S");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	@RequestMapping(value = "/board/boardJoin.do", method = RequestMethod.GET)
	public String boardJoin(Locale locale, Model model) throws Exception{
		
		List<MenuVo> phoneList = new ArrayList<MenuVo>();
		phoneList = userService.SelectCodeTypeList(codeType);
		model.addAttribute("phoneList", phoneList);
		return "board/boardJoin1";
	}
	@RequestMapping(value = "/board/boardLogin.do", method = RequestMethod.GET)
	public String boardLogin(Locale locale, Model model) throws Exception{
		return "board/boardLogin";
	}
	@RequestMapping(value = "/user/userLogoutAction.do", method = RequestMethod.GET)
	public String boardLogout(Locale locale, Model model,HttpSession session) throws Exception{

		session.invalidate();
		return "redirect:/board/boardList.do";
	}
	@RequestMapping(value = "/user/userLoginAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String userLoginAction(Locale locale,HttpSession session,UserVo userVo) throws Exception{
		
		HashMap<String, String> result = new HashMap<String, String>();
		String callbackMsg = "";
		CommonUtil commonUtil = new CommonUtil();
		
		int userIdCnt = userService.userIdCheack(userVo);
		
		if(userIdCnt > 0) {
			int resultCnt = userService.userPasswordCheack(userVo);
			result.put("result", (resultCnt > 0)?"Y":"N");
			if(resultCnt > 0) {
				session.setAttribute("userid", userVo.getUserId());
			}
			callbackMsg = commonUtil.getJsonCallBackString(" ",result);
			return callbackMsg;
		}else {
			result.put("result", "idCheck");
			callbackMsg = commonUtil.getJsonCallBackString(" ",result);
			return callbackMsg;
		}

	}
}
