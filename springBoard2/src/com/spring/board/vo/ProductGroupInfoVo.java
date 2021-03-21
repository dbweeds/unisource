package com.spring.board.vo;

public class ProductGroupInfoVo {
	private int PRDGRIDX;
	private String PRDGRCD;
	private String PRDGRNAME;
	private String PRDGRIMG;
	private String PRDGRVIEW = "true";
	
	public int getPRDGRIDX() {
		return PRDGRIDX;
	}
	public void setPRDGRIDX(int PRDGRIDX) {
		this.PRDGRIDX = PRDGRIDX;
	}
	public String getPRDGRCD() {
		return PRDGRCD;
	}
	public void setPRDGRCD(String PRDGRCD) {
		this.PRDGRCD = PRDGRCD;
	}
	public String getPRDGRNAME() {
		return PRDGRNAME;
	}
	public void setPRDGRNAME(String PRDGRNAME) {
		this.PRDGRNAME = PRDGRNAME;
	}
	public String getPRDGRIMG() {
		return PRDGRIMG;
	}
	public void setPRDGRIMG(String PRDGRIMG) {
		this.PRDGRIMG = PRDGRIMG;
	}
	public String getPRDGRVIEW() {
		return PRDGRVIEW;
	}
	public void setPRDGRVIEW(String PRDGRVIEW) {
		this.PRDGRVIEW = PRDGRVIEW;
	}
	@Override
	public String toString() {
		return "ProductGroupInfoVo [PRDGRIDX=" + PRDGRIDX + ", PRDGRCD=" + PRDGRCD + ", PRDGRNAME=" + PRDGRNAME
				+ ", PRDGRIMG=" + PRDGRIMG + ", PRDGRVIEW=" + PRDGRVIEW + "]";
	}
	
}
