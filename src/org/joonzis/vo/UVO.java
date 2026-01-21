package org.joonzis.vo;

import java.sql.Date;

public class UVO {
	private int u_idx;
	private String u_id;
	private String uPw;
	private String uName;
	private String uEmail;
	private Date uRegDate;
	
	public UVO() {}
	public UVO(int u_idx, String u_id, String uPw, String uName, String uEmail, Date uRegDate) {
		super();
		this.u_idx = u_idx;
		this.u_id = u_id;
		this.uPw = uPw;
		this.uName = uName;
		this.uEmail = uEmail;
		this.uRegDate = uRegDate;
	}
	
	public int getU_idx() {
		return u_idx;
	}
	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getuPw() {
		return uPw;
	}
	public void setuPw(String uPw) {
		this.uPw = uPw;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public Date getuRegDate() {
		return uRegDate;
	}
	public void setuRegDate(Date uRegDate) {
		this.uRegDate = uRegDate;
	}
	
	
	
	
}
