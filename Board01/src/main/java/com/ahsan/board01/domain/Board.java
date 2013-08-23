package com.ahsan.board01.domain;

import java.io.Serializable;
import java.util.Date;

public class Board implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer no;
	private String title;
	private String notice;
	private String writer;
	private String password;
	private Date wdate;
	private Integer ref;
	public Board() {}
	public Board(Integer no, String title, String notice, String writer,
			String password, Date wdate, Integer ref) {
		super();
		this.no = no;
		this.title = title;
		this.notice = notice;
		this.writer = writer;
		this.password = password;
		this.wdate = wdate;
		this.ref = ref;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public Integer getRef() {
		return ref;
	}
	public void setRef(Integer ref) {
		this.ref = ref;
	}
	
}
