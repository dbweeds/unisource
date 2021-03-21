package com.spring.board.vo;


public class ProductPatternInfoVo {
	
	private int 	PRDPTIDX;
	private int 	PRDGRIDX;
	private String 	PRDPTTYPE;
	private String 	PRDPTSORT;
	private String 	PRDPTVIEW = "true";
	public int getPRDPTIDX() {
		return PRDPTIDX;
	}
	public void setPRDPTIDX(int PRDPTIDX) {
		this.PRDPTIDX = PRDPTIDX;
	}
	public int getPRDGRIDX() {
		return PRDGRIDX;
	}
	public void setPRDGRIDX(int PRDGRIDX) {
		this.PRDGRIDX = PRDGRIDX;
	}
	public String getPRDPTTYPE() {
		return PRDPTTYPE;
	}
	public void setPRDPTTYPE(String PRDPTTYPE) {
		this.PRDPTTYPE = PRDPTTYPE;
	}
	public String getPRDPTSORT() {
		return PRDPTSORT;
	}
	public void setPRDPTSORT(String PRDPTSORT) {
		this.PRDPTSORT = PRDPTSORT;
	}
	public String getPRDPTVIEW() {
		return PRDPTVIEW;
	}
	public void setPRDPTVIEW(String PRDPTVIEW) {
		this.PRDPTVIEW = PRDPTVIEW;
	}
	
	
	
	
}
