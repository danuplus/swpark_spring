package com.example.jdbc;

/*
DTO : Data Transfer Object

create sequence  member_no_seq
start with 1
increment by 1;

create table member 
(no number(10) constraint member_no_pk primary key,
 name varchar2(50),
 email varchar2(100),
 phone varchar2(100));

 */
public class Member {
	private int no;
	private String name;
	private String email;
	private String phone;
	
	public Member() {}
	
	public Member(int no, String name, String email, String phone) {
		this.no = no;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
