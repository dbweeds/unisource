package com.spring.board.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.ProductGroupInfoVo;
import com.spring.board.vo.ProductGroupProductVo;
import com.spring.board.vo.ProductInfoVo;
import com.spring.board.vo.ProductPatternDetailVo;
import com.spring.board.vo.ProductPatternDetailVoList;
import com.spring.board.vo.ProductPatternInfoVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	boardService boardService;

	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception {
		model.addAttribute("ProductGroupInfo", boardService.selectProductGroupInfo());
		return "board/boardWrite";
	}
	@RequestMapping(value = "/board/ProductGroupInfoWrite.do", method = RequestMethod.GET)
	public String ProductGroupInfoWrite(Locale locale, Model model) throws Exception {
		
		return "board/ProductGroupInfoWrite";
	}
	@RequestMapping(value = "/board/boardList1.do", method = RequestMethod.GET)
	public String boardList1(Locale locale, Model model) throws Exception {
		model.addAttribute("ProductGroupInfo", boardService.selectProductGroupInfo());
		return "board/boardList1";
	}

	@RequestMapping(value = "/board/ProductGroupInfoWrite1.do", method = RequestMethod.POST)
	public String ProductGroupInfoWrite1(Locale locale, Model model,ProductGroupInfoVo PGVo,int PRDPTTYPENUM)
			throws Exception {
		int result1 = boardService.InsertProductGroupInfo(PGVo);
		ProductPatternInfoVo PTVo = new ProductPatternInfoVo();
		if(result1>0) {
			PTVo.setPRDGRIDX(PGVo.getPRDGRIDX());
			for(int i =0;i<=PRDPTTYPENUM;i++) {
				result1 = boardService.InsertProductPatternInfo(PTVo);				
			}
		}
		model.addAttribute("PGVo",PGVo);
		model.addAttribute("PTVo",PTVo);
		model.addAttribute("PRDPTTYPENUM",PRDPTTYPENUM);
		if(result1>0) {
				return "board/type1";
		}
		return "board/ProductGroupInfoWrite";
	}
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWritePost(Locale locale, Model model, ProductInfoVo infoVo, ProductGroupProductVo GPVo)
			throws Exception {
		logger.info("" + infoVo);
		int i=0;
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		int resultCnt = boardService.InsertProductInfo(infoVo);
		if (resultCnt > 0) {
			GPVo.setPRDIDX("" + infoVo.getPRDIDX());
			i = boardService.InsertProductGroupProduct(GPVo);
			if (i == 0) {
				resultCnt = 0;
			}
		}
		System.out.println(""+i);
		result.put("msg", (resultCnt > 0)?"S":"F");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);

		return callbackMsg;
	}
	@RequestMapping(value = "/board/ProductGroupInfoWrite2.do", method = RequestMethod.POST)
	@ResponseBody
	public String ProductGroupInfoWrite2Post(Locale locale, Model model,int D_PRDGRIDX,int D_PRDPTIDX, ProductPatternDetailVoList PDVoList)
			throws Exception {
		for(ProductPatternDetailVo vo:PDVoList.getProductPatternDetailList()) {
			vo.setPRDGRIDX(D_PRDGRIDX);
			vo.setPRDPTIDX(D_PRDPTIDX);
			System.out.println(vo.toString());
			boardService.InsertProductPatternDetailVo(vo);
			
		}
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();

		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		return callbackMsg;
	}
	@RequestMapping(value = "/board/uploadFile.do",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> uploadPost(MultipartFile uploadFile) {
		logger.info("테스트");
		String upload = "C:\\upload";
		String uploadFileName = null;

		// 폴더 생성
		File uploadPath = new File(upload);

		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFile.getOriginalFilename();

			File saveFile = new File(uploadPath, uploadFileName);

			try {
				uploadFile.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

		return new ResponseEntity<String>(uploadFileName, HttpStatus.OK);
	}
}
