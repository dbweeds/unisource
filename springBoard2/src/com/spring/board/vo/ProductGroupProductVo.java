package com.spring.board.vo;

public class ProductGroupProductVo {
	private int PRDGRPRDIDX;
	private int PRDGRIDX;
	private String PRDIDX;
	private String PRDGRPRDVIEW = "true";
	
	public int getPRDGRPRDIDX() {
		return PRDGRPRDIDX;
	}
	public void setPRDGRPRDIDX(int PRDGRPRDIDX) {
		this.PRDGRPRDIDX = PRDGRPRDIDX;
	}
	public int getPRDGRIDX() {
		return PRDGRIDX;
	}
	public void setPRDGRIDX(int PRDGRIDX) {
		this.PRDGRIDX = PRDGRIDX;
	}
	public String getPRDIDX() {
		return PRDIDX;
	}
	public void setPRDIDX(String PRDIDX) {
		this.PRDIDX = PRDIDX;
	}
	public String getPRDGRPRDVIEW() {
		return PRDGRPRDVIEW;
	}
	public void setPRDGRPRDVIEW(String PRDGRPRDVIEW) {
		this.PRDGRPRDVIEW = PRDGRPRDVIEW;
	}
	
	

}
