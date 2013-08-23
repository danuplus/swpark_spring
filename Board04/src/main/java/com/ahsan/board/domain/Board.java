package com.ahsan.board.domain;

import java.io.Serializable;
import java.util.Date;

public class Board implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer no;
	private String writer;
	private String password;
	private String title;
	private String content;
	private Date wdate;
	private Integer read_cnt;
	private Integer reply_cnt;
	private Integer group_no;
	private Integer sequence_in_group;
	private Integer indent_in_group;
	private Integer ref_no;
	
	public Board() {}

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

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}

	public Integer getRead_cnt() {
		return read_cnt;
	}

	public void setRead_cnt(Integer read_cnt) {
		this.read_cnt = read_cnt;
	}

	public Integer getReply_cnt() {
		return reply_cnt;
	}

	public void setReply_cnt(Integer reply_cnt) {
		this.reply_cnt = reply_cnt;
	}

	public Integer getGroup_no() {
		return group_no;
	}

	public void setGroup_no(Integer group_no) {
		this.group_no = group_no;
	}

	public Integer getSequence_in_group() {
		return sequence_in_group;
	}

	public void setSequence_in_group(Integer sequence_in_group) {
		this.sequence_in_group = sequence_in_group;
	}

	public Integer getIndent_in_group() {
		return indent_in_group;
	}

	public void setIndent_in_group(Integer indent_in_group) {
		this.indent_in_group = indent_in_group;
	}

	public Integer getRef_no() {
		return ref_no;
	}

	public void setRef_no(Integer ref_no) {
		this.ref_no = ref_no;
	}

}
