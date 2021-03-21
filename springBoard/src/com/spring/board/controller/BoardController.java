package com.spring.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
public class BoardController {
	
	@Autowired 
	boardService boardService;
	@Autowired 
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private String codeType = "menu";
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model,HttpSession session,PageVo pageVo) throws Exception{	
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		List<MenuVo> menuList = new ArrayList<MenuVo>();
		UserVo userinfo;
		String userid = "";
		if((String) session.getAttribute("userid")!=null) {
			userid = (String) session.getAttribute("userid");			
		}
		int page = 1;
		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);;
		}
		userinfo =  userService.userinfo(userid);
		boardList = boardService.SelectBoardList(pageVo);
		menuList = userService.SelectCodeTypeList(codeType);
		totalCnt = boardService.selectBoardCnt(pageVo);
		
		pageVo.Page(totalCnt);
		
		model.addAttribute("userinfo", userinfo);
		model.addAttribute("menuList", menuList);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pagevo", pageVo);
		
		return "board/boardList";
	}
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model, HttpSession session
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		UserVo userinfo;
		userinfo =  userService.userinfo(boardVo.getBoardWriter());
		boardVo.setBoardWriter(userinfo.getUserName());
		if(session.getAttribute("userid") !=null) {
			userinfo = null;
			userinfo =  userService.userinfo((String)session.getAttribute("userid"));			
			model.addAttribute("username", userinfo.getUserName());
		}
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardUpdate.do", method = RequestMethod.GET)
	public String boardUpdate(Locale locale, Model model,HttpSession session
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		List<MenuVo> menuList = new ArrayList<MenuVo>();
		BoardVo boardVo = new BoardVo();
		UserVo userinfo;
		menuList = userService.SelectCodeTypeList(codeType);
		boardVo = boardService.selectBoard(boardType,boardNum);
		userinfo =  userService.userinfo(boardVo.getBoardWriter());
		boardVo.setBoardWriter(userinfo.getUserName());
		
		model.addAttribute("userinfo", userinfo);
		model.addAttribute("menuList", menuList);
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardUpdate";
	}
	@RequestMapping(value = "/board/boardUpdateAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardUpdateAction(Locale locale,BoardVo boardVo) throws Exception{
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		int resultCnt = boardService.boardUpdate(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);

		return callbackMsg;
	}
	
	@RequestMapping(value = "/board/boardDeleteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardDeleteAction(Locale locale,BoardVo boardVo) throws Exception{
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		int resultCnt = boardService.boardDelete(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);

		return callbackMsg;
	}
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model,HttpSession session) throws Exception{
		List<MenuVo> menuList = new ArrayList<MenuVo>();
		UserVo userinfo;
		String userid = "";
		if((String) session.getAttribute("userid")!=null) {
			userid = (String) session.getAttribute("userid");			
		}
		userinfo =  userService.userinfo(userid);
		model.addAttribute("userinfo", userinfo);
		menuList = userService.SelectCodeTypeList(codeType);
		model.addAttribute("menuList", menuList);
		return "board/boardWrite";
	}
	
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,HttpSession session,BoardVo boardVoList) throws Exception{
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		int resultCnt = 0;
		String userid = "";
		if((String) session.getAttribute("userid")!=null) {
			userid = (String) session.getAttribute("userid");			
		}

		for(BoardVo boardVo : boardVoList.getBoardVoList()) {
			if(boardVo.getBoardType() != null) {
				boardVo.setBoardWriter(userid);
				resultCnt = boardService.boardInsert(boardVo);			
				if(resultCnt == 0) {
					break;				
				}
			}
		}
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);

		return callbackMsg;
	}
	@RequestMapping(value = "/board/boardSearchListAction.do",method = RequestMethod.POST)
	public String boardSearchListAction(Locale locale, Model model,HttpSession session,PageVo pageVo) throws Exception{
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		List<MenuVo> menuList = new ArrayList<MenuVo>();

		int totalCnt = 0;
		UserVo userinfo;

		boardList = boardService.SelectBoardList(pageVo);
		totalCnt = boardService.selectBoardCnt(pageVo);
		
		menuList = userService.SelectCodeTypeList(codeType);
		if(session.getAttribute("userid") !=null) {
			userinfo = null;
			userinfo =  userService.userinfo((String)session.getAttribute("userid"));			
			model.addAttribute("userinfo", userinfo);
		}
		pageVo.Page(totalCnt);
		model.addAttribute("menuList", menuList);
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pagevo", pageVo);

		return "board/searchList";
	}
	/*
	 * @RequestMapping(value = "/board/boardSearchListAction.do",
	 * produces="application/json;charset=utf8", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String boardSearchListAction(Locale locale, Model
	 * model,HttpSession session,PageVo pageVo) throws Exception{ HashMap<String,
	 * Object> result = new HashMap<String, Object>(); CommonUtil commonUtil = new
	 * CommonUtil();
	 * 
	 * List<BoardVo> boardList = new ArrayList<BoardVo>(); List<MenuVo> menuList =
	 * new ArrayList<MenuVo>(); int page = 1; int totalCnt = 0;
	 * 
	 * if(pageVo.getPageNo() == 0){ pageVo.setPageNo(page);; }
	 * logger.info(""+pageVo); boardList = boardService.SelectBoardList(pageVo);
	 * totalCnt = boardService.selectBoardCnt(pageVo);
	 * 
	 * menuList = userService.SelectCodeTypeList(codeType);
	 * 
	 * 
	 * result.put("menuList", menuList); result.put("boardList", boardList);
	 * result.put("totalCnt", totalCnt); result.put("pageNo", page); String
	 * callbackMsg = commonUtil.getJsonCallBackString(" ",result);
	 * System.out.println("callbackMsg::"+callbackMsg);
	 * 
	 * return callbackMsg; }*/
	@RequestMapping(value = "/board/xlsDownload.do")
	public void xlsDownload(HttpServletResponse response,Locale locale
			,HttpSession session,PageVo pageVo) throws Exception{
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		List<MenuVo> menuList = new ArrayList<MenuVo>();
		int page = 1;
		int totalCnt = 0;

		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);;
		}
		boardList = boardService.SelectBoardList(pageVo);
		totalCnt = boardService.selectBoardCnt(pageVo);
		
		menuList = userService.SelectCodeTypeList(codeType);
		
		Workbook wb = new XSSFWorkbook();
		
		Sheet sheet1 = wb.createSheet("List");
		
		sheet1.setColumnWidth(0, 5500);
		sheet1.setColumnWidth(1, 5500);
		sheet1.setColumnWidth(2, 5500);
		sheet1.setColumnWidth(3, 5500);
		sheet1.setColumnWidth(4, 5500);
		CellStyle Style = wb.createCellStyle();

		Style.setAlignment(HorizontalAlignment.CENTER);

		Style.setBorderTop(BorderStyle.THIN);
		Style.setBorderBottom(BorderStyle.THIN);
		Style.setBorderLeft(BorderStyle.THIN);
		Style.setBorderRight(BorderStyle.THIN);
		
		Row row = null;
		Cell cell = null;
		int rowNo = 0;
		
		row = sheet1.createRow(rowNo++);
		
		cell = row.createCell(0);
		cell.setCellStyle(Style);
		cell.setCellValue("Type");
		
		cell = row.createCell(1);
		cell.setCellStyle(Style);
		cell.setCellValue("No");
		
		cell = row.createCell(2);
		cell.setCellStyle(Style);
		cell.setCellValue("Title");
		
		cell = row.createCell(3);
		cell.setCellStyle(Style);
		cell.setCellValue("Total");

		for(BoardVo vo : boardList) {
			int totalno = rowNo;
			row = sheet1.createRow(rowNo++);
				
			cell = row.createCell(0);
			cell.setCellStyle(Style);
			for(MenuVo mvo: menuList) {
				if(mvo.getCodeId().equals(vo.getBoardType())) {
					cell.setCellValue(mvo.getCodeName());	
				}
			}
			
			cell = row.createCell(1);
			cell.setCellStyle(Style);
			cell.setCellValue(vo.getBoardNum());
			
			cell = row.createCell(2);
			cell.setCellStyle(Style);
			cell.setCellValue(vo.getBoardTitle());
			
			if(totalno == 1) {
				cell = row.createCell(3);
				cell.setCellStyle(Style);
				cell.setCellValue(totalCnt);
			}
		}
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=List.xlsx");
		 
		wb.write(response.getOutputStream());
		wb.close(); 
	}
}
