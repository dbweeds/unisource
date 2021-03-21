package com.spring.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
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
public class CalenderController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	int base_year = 1980;
	int base_month = 1;
	int total_sum = 0;

	int[] month_table = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	@RequestMapping(value = "/board/calendarDownload.do")
	public void calendarDownload(HttpServletResponse response, Locale locale, int calendarYear, int calendarManth)
			throws Exception {
		//OPCPackage opcPackage = OPCPackage.open(new File("C:/Users/user/Desktop/code/unisourse/calendar.xlsx"));
		Workbook wb = new XSSFWorkbook(new FileInputStream("C:/Users/user/Desktop/code/unisourse/calendar.xlsx"));
		Sheet osheet = wb.cloneSheet(0);
		Row row = null;
		Cell cell = null;
		Workbook newWB = new XSSFWorkbook();
		Sheet sheet = newWB.createSheet();
		Row r1 = null;
		Cell c1 = null;
		CellStyle newStyle = null;
//		for(int a = 0; a < osheet.getPhysicalNumberOfRows();a++) {
//			row = osheet.getRow(a);
//			r1 = sheet.createRow(a);
//			for(int b = 0; b <osheet.getRow(a).getPhysicalNumberOfCells();b++) {
//				cell = row.getCell(b);
//				c1 = r1.createCell(b);
//				newStyle = newWB.createCellStyle();
//				newStyle.cloneStyleFrom(cell.getCellStyle());
//				c1.setCellStyle(newStyle);
//			}
//		}
		int i, j;
		int d = 0;
		int year = 0;
		int month;
		int sum = 0;

		int dy = count_leap(base_year - calendarYear);
		convert_to_day(calendarYear);

		int day;
		if (calendarYear >= base_year) {

			if (is_leap_year(calendarYear) == 1)
				month_table[1] = 29;

			for (i = 0; i < (calendarManth - base_month); i++)
				total_sum += month_table[i];
			day = (total_sum + 2) % 7;
			month = total_to_month(total_sum);
			Font redFont = newWB.createFont();
			redFont.setColor(IndexedColors.RED.getIndex());
			Font blueFont = newWB.createFont();
			blueFont.setColor(IndexedColors.BLUE.getIndex());
			newStyle = newWB.createCellStyle();
			newStyle.setAlignment(HorizontalAlignment.CENTER);
			r1 = sheet.createRow(i++);
			c1 = r1.createCell(2);
			c1.setCellStyle(newStyle);
			c1.setCellValue(calendarYear+"년");
			c1 = r1.createCell(3);
			c1.setCellStyle(newStyle);
			c1.setCellValue(calendarManth+"월");
			c1 = r1.createCell(4);
			c1.setCellStyle(newStyle);
			c1.setCellValue("달력");
			r1 = sheet.createRow(i++);
			c1 = r1.createCell(1);
			c1.setCellStyle(newStyle);
			c1.setCellValue("일");
			c1 = r1.createCell(4);
			c1.setCellStyle(newStyle);
			c1.setCellValue("월");
			c1 = r1.createCell(7);
			c1.setCellStyle(newStyle);
			c1.setCellValue("화");
			c1 = r1.createCell(10);
			c1.setCellStyle(newStyle);
			c1.setCellValue("수");
			c1 = r1.createCell(13);
			c1.setCellStyle(newStyle);
			c1.setCellValue("목");
			c1 = r1.createCell(16);
			c1.setCellStyle(newStyle);
			c1.setCellValue("금");
			c1 = r1.createCell(19);
			c1.setCellStyle(newStyle);
			c1.setCellValue("토");

			int x = 0;
			int y = day;
			HashMap<Integer, Row> hash= new HashMap<Integer, Row>();
			for (j = 1; j <= month_table[month - 1]; j++)
			{
				int c = x;
				if(y!=0) {
					for (y = 0; y <  day; y++) {
						c+=3;					
					}					
				}
				for(int a = 0; a < osheet.getPhysicalNumberOfRows();a++) {
					row = osheet.getRow(a);
					if(x==0 || y!=0) {
						r1 = sheet.createRow(i);
						hash.put(i, r1);

					}
					for(int b = 0; b <osheet.getRow(a).getPhysicalNumberOfCells();b++) {
						cell = row.getCell(b);
						c1 = hash.get(i).createCell(c);
						newStyle = newWB.createCellStyle();
						newStyle.cloneStyleFrom(cell.getCellStyle());
						newStyle.setAlignment(HorizontalAlignment.CENTER);
						if(c>=0&&c<3&&a==0) {
							newStyle.setFont(redFont);
						}
						if(c>=18&&c<21&&a==0) {
							newStyle.setFont(blueFont);
						}
						c1.setCellStyle(newStyle);
						if(a==0&&b==1) {
							c1.setCellValue(j+"일");
						}
						c++;
					}
					c-=3;
					i++;
				}
				if(y!=0) {
					x+=3+(day*3);
					y=0;
				}else {
					x+=3;										
				}
				i-=4;
				if (((j + day) % 7) == 0) {
					i+=4;
					x=0;					
				}
			}
			month_table[1] = 28;
		}
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=test.xlsx");
		
		newWB.write(response.getOutputStream());
	}

	public int is_leap_year(int n) {
		if (n % 4 == 0) {
			if (n % 100 == 0) {
				if (n % 400 == 0) {
					return 1;
				}
				return 0;
			}
			return 1;
		} else {
			return 0;
		}
	}

	public int total_to_month(int total) 
	{

		boolean CHK = false;
		int i = 0; 
		int cnt = 0; 
		int chk_leap_year = base_year; 
		while (CHK != true) {
			if (is_leap_year(chk_leap_year) == 1)
				month_table[1] = 29; 

			if (total >= month_table[i]) 
			{
				total -= month_table[i++];
				cnt++; 
				if (i == 12) 
				{
					i -= 12; 
					chk_leap_year++;
				}
				month_table[1] = 28; 
			}

			else
				break;
		}
		cnt %= 12; 
		return (cnt + 1); 
	}
	public int count_leap(int n)
	{
		int i; 
		int cnt = 0; 
		for (i = base_year; i < (base_year - n); i++)
		{

			if (is_leap_year(i) == 1)
			{
				cnt++; 
			}
		}
		return cnt; 
	}
	public void convert_to_day(int calendarYear) {
		total_sum = ((calendarYear - base_year) * 365) + count_leap((base_year - calendarYear));
	}

}
