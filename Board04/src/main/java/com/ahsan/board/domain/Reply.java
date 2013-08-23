package com.ahsan.board.domain;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer no;
	private String writer;
	private String password;
	private String memo;
	private Date wdate;
	private Integer ref_no;
	
	public Reply() {}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}

	public Integer getRef_no() {
		return ref_no;
	}

	public void setRef_no(Integer ref_no) {
		this.ref_no = ref_no;
	}
	
	

}
