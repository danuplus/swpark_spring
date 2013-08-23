package com.cafe24.ahsan.domain;

import java.util.Date;

public class Memo {
	private Integer no;
	private String memo;
	private String writer;
	private String password;
	private Date wdate;
	
	public Memo() {}

	public Memo(Integer no, String memo, String writer, String password, Date wdate) {
		this.no = no;
		this.memo = memo;
		this.writer = writer;
		this.password = password;
		this.wdate = wdate;
	}
	
	public Memo(String memo, String writer, String password) {
		this.memo = memo;
		this.writer = writer;
		this.password = password;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
}
