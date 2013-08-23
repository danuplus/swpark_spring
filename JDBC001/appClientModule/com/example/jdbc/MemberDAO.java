package com.example.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class MemberDAO extends JdbcDaoSupport implements MemberService {

	// dataSource∞° ¡÷¿‘µ .
	
	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		String sql = "select no, name, email, phone from member order by no";
		List<Member> members =	this.getJdbcTemplate().query(sql, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Member member = new Member();
				member.setNo(rs.getInt("no"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				return member;
			}
			
		});
		
		return members;
	}

	@Override
	public boolean addMember(Member member) {
		// TODO Auto-generated method stub
		String sql = "insert into member(no, name, email, phone) " +
					"values(member_no_seq.nextval, ?, ?, ?)";
		return this.getJdbcTemplate().update(sql, member.getName(), member.getEmail(), member.getPhone()) > 0;
		
	}

	@Override
	public boolean removeMember(int no) {
		// TODO Auto-generated method stub
		String sql = "delete from member where no=?";
		return this.getJdbcTemplate().update(sql, no) > 0;
	}

	@Override
	public boolean updateMember(Member member) {
		// TODO Auto-generated method stub
		String sql = "update member set name=?, email=?, phone=? where no=?";
		return this.getJdbcTemplate().update(sql, member.getName(), member.getEmail(), member.getPhone(), member.getNo()) > 0;
	}

	@Override
	public Member getMember(int no) {
		// TODO Auto-generated method stub
		String sql = "select no, name, email, phone from member where no=?";
		
		Member member = this.getJdbcTemplate().queryForObject(sql, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Member member = new Member();
				member.setNo(rs.getInt("no"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				return member;
			}
			
		}, no);
		
		return member;
	}

	@Override
	public boolean removeAllMember() {
		// TODO Auto-generated method stub
		String sql = "delete from member";
		return this.getJdbcTemplate().update(sql) > 0;
	}

	@Override
	public int getLastNo() {
		// TODO Auto-generated method stub
		String sql = "select max(no) from member";
		return this.getJdbcTemplate().queryForInt(sql);
	}

}
