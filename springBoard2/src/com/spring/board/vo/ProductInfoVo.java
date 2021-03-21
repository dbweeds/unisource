package com.spring.board.vo;

public class ProductInfoVo {
	private int PRDIDX;
	private String PRDNAME;
	private String PRDPRC;
	private String PRDVIEW = "true";
	private String PRDIMG;
	
	public int getPRDIDX() {
		return PRDIDX;
	}
	public void setPRDIDX(int PRDIDX) {
		this.PRDIDX = PRDIDX;
	}
	public String getPRDNAME() {
		return PRDNAME;
	}
	public void setPRDNAME(String PRDNAME) {
		this.PRDNAME = PRDNAME;
	}
	public String getPRDPRC() {
		return PRDPRC;
	}
	public void setPRDPRC(String PRDPRC) {
		this.PRDPRC = PRDPRC;
	}
	public String getPRDVIEW() {
		return PRDVIEW;
	}
	public void setPRDVIEW(String PRDVIEW) {
		this.PRDVIEW = PRDVIEW;
	}
	public String getPRDIMG() {
		return PRDIMG;
	}
	public void setPRDIMG(String PRDIMG) {
		this.PRDIMG = PRDIMG;
	}
	@Override
	public String toString() {
		return "ProductInfoVo [PRDIDX=" + PRDIDX + ", PRDNAME=" + PRDNAME + ", PRDPRC=" + PRDPRC + ", PRDVIEW="
				+ PRDVIEW + ", PRDIMG=" + PRDIMG + "]";
	}
	
	
}
