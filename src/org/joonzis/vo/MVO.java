package org.joonzis.vo;

import java.sql.Date;

public class MVO {
	private int m_idx;
	private String writer;
	private String title;
	private String content;
	private int hit;
	private String filename;
	private Date reg_date;
	
	public MVO() {}
	public MVO(int m_idx, String writer, String title, String content, int hit, String filename, Date reg_date) {
		super();
		this.m_idx = m_idx;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.filename = filename;
		this.reg_date = reg_date;
	}
	public int getM_idx() {
		return m_idx;
	}
	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
	
}
