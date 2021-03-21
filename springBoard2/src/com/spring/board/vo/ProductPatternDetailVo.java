package com.spring.board.vo;


public class ProductPatternDetailVo {
	
	private int 	PTDETAILIDX;
	private int 	PRDGRIDX;
	private int 	PRDPTIDX;
	private String 	PTDETAILTYPE="";
	private String 	PTDETAILIMG = "";
	private String 	PTDETAILDESC = "";
	private String 	PTDETAILMV = "";
	private String 	PRDIDX = "";
	private String 	PTDETAILVIEW = "true";
	
	@Override
	public String toString() {
		return "ProductPatternDetailVo [PTDETAILIDX=" + PTDETAILIDX + ", PRDGRIDX=" + PRDGRIDX + ", PRDPTIDX="
				+ PRDPTIDX + ", PTDETAILTYPE=" + PTDETAILTYPE + ", PTDETAILIMG=" + PTDETAILIMG + ", PTDETAILDESC="
				+ PTDETAILDESC + ", PTDETAILMV=" + PTDETAILMV + ", PRDIDX=" + PRDIDX + ", PTDETAILVIEW=" + PTDETAILVIEW
				+ "]";
	}
	public int getPTDETAILIDX() {
		return PTDETAILIDX;
	}
	public void setPTDETAILIDX(int PTDETAILIDX) {
		this.PTDETAILIDX = PTDETAILIDX;
	}
	public int getPRDGRIDX() {
		return PRDGRIDX;
	}
	public void setPRDGRIDX(int PRDGRIDX) {
		this.PRDGRIDX = PRDGRIDX;
	}
	public int getPRDPTIDX() {
		return PRDPTIDX;
	}
	public void setPRDPTIDX(int PRDPTIDX) {
		this.PRDPTIDX = PRDPTIDX;
	}
	public String getPTDETAILTYPE() {
		return PTDETAILTYPE;
	}
	public void setPTDETAILTYPE(String PTDETAILTYPE) {
		this.PTDETAILTYPE = PTDETAILTYPE;
	}
	public String getPTDETAILIMG() {
		return PTDETAILIMG;
	}
	public void setPTDETAILIMG(String PTDETAILIMG) {
		this.PTDETAILIMG = PTDETAILIMG;
	}
	public String getPTDETAILDESC() {
		return PTDETAILDESC;
	}
	public void setPTDETAILDESC(String PTDETAILDESC) {
		this.PTDETAILDESC = PTDETAILDESC;
	}
	public String getPTDETAILMV() {
		return PTDETAILMV;
	}
	public void setPTDETAILMV(String PTDETAILMV) {
		this.PTDETAILMV = PTDETAILMV;
	}
	public String getPRDIDX() {
		return PRDIDX;
	}
	public void setPRDIDX(String PRDIDX) {
		this.PRDIDX = PRDIDX;
	}
	public String getPTDETAILVIEW() {
		return PTDETAILVIEW;
	}
	public void setPTDETAILVIEW(String PTDETAILVIEW) {
		this.PTDETAILVIEW = PTDETAILVIEW;
	}
	
	
}
