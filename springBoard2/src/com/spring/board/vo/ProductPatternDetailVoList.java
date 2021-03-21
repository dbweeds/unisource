package com.spring.board.vo;

import java.util.ArrayList;
import java.util.List;

public class ProductPatternDetailVoList {
	
	List<ProductPatternDetailVo> ProductPatternDetailList = new ArrayList<ProductPatternDetailVo>();

	public List<ProductPatternDetailVo> getProductPatternDetailList() {
		return ProductPatternDetailList;
	}

	public void setProductPatternDetailList(List<ProductPatternDetailVo> productPatternDetailList) {
		ProductPatternDetailList = productPatternDetailList;
	}
	
	
}
