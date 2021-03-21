package com.spring.board.vo;

import java.util.ArrayList;
import java.util.List;

public class PageVo {
	
	private int pageNo = 0;
	
	
	public List<MenuVo> menuList = new ArrayList<MenuVo>();
	
	private int totalCnt = 0;
	
	private int endPage; 
	private int startPage; 
	private boolean prev; 
	private boolean next; 
	
	@Override
	public String toString() {
		return "PageVo [pageNo=" + pageNo + ", menuList=" + menuList + ", totalCnt=" + totalCnt + ", endPage=" + endPage
				+ ", startPage=" + startPage + ", prev=" + prev + ", next=" + next + "]";
	}

	public void Page(int totalCnt) {
		this.totalCnt = totalCnt;
		this.endPage = (int) (Math.ceil(pageNo / 10.0)) * 10;
		this.startPage = endPage - 9;

		int realEnd = (int) (Math.ceil((totalCnt / 1.0) / 10));
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
	
	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public List<MenuVo> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuVo> menuList) {
		this.menuList = menuList;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
}
