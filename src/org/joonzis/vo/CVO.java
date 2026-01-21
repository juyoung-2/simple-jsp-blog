package org.joonzis.vo;

import java.sql.Date;

public class CVO {
	private int c_idx;
	private String  writer;
	private String  content;
	private Date reg_date;
	private int m_idx;
	
	public CVO() {}
	public CVO(int c_idx, String writer, String content, Date reg_date, int m_idx) {
		super();
		this.c_idx = c_idx;
		this.writer = writer;
		this.content = content;
		this.reg_date = reg_date;
		this.m_idx = m_idx;
	}
	
	public int getC_idx() {
		return c_idx;
	}
	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getM_idx() {
		return m_idx;
	}
	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}
	
	
	
	
	
	
	
}
