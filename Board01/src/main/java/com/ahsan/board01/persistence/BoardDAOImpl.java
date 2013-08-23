package com.ahsan.board01.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ahsan.board01.domain.Board;


public class BoardDAOImpl extends JdbcDaoSupport implements BoardDAO {

	private static final int LINES_PER_PAGE = 15;
	
	@Override
	public List<Board> getBoardByPage(int page) {
		final String sql = "select no, title, notice, writer, password, wdate, ref " +
				   "from (select rownum rn, t1.* " +
				   "      from (select * from board order by no desc) t1 " +
				   "      where rownum <= ?) " +
				   "where rn >= ?";
		int start = (page -1) * LINES_PER_PAGE + 1;
		int stop = page * LINES_PER_PAGE;
		return this.getJdbcTemplate().query(sql, new RowMapper<Board>() {
			
			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				Board board = new Board();
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				board.setNotice(rs.getString("notice"));
				board.setWriter(rs.getString("writer"));
				board.setPassword(rs.getString("password"));
				board.setWdate(rs.getTimestamp("wdate"));
				board.setRef(rs.getInt("ref"));
				return board;
			}
		}, stop, start);
	}
	
	@Override
	public Board getBoardByNo(int no) {
		// TODO Auto-generated method stub
		final String sql = "select no, title, notice, writer, password, wdate, ref from board " +
						   "where no=?";
		return this.getJdbcTemplate().queryForObject(sql, new RowMapper<Board>() {

			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Board board = new Board();
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				board.setNotice(rs.getString("notice"));
				board.setWriter(rs.getString("writer"));
				board.setPassword(rs.getString("password"));
				board.setWdate(rs.getTimestamp("wdate"));
				board.setRef(rs.getInt("ref"));
				return board;
			}
			
		}, no);
	}
	
	@Override
	public boolean addBoard(Board board) {
		final String sql = "insert into board(no, title, notice, writer, password, wdate, ref) " +
				"values(board_no_seq.nextval, ?, ?, ?, ?, sysdate, 0)";
		return this.getJdbcTemplate().update(sql, board.getTitle(), board.getNotice(), board.getWriter(), board.getPassword()) > 0 ? true : false;
	}
	
	@Override
	public boolean updateBoard(Board board) {
		// TODO Auto-generated method stub
		final String sql = "update board set title=?, notice=?, writer=?, password=? " + 
						   "where no=?";
		return this.getJdbcTemplate().update(sql, 
				board.getTitle(), board.getNotice(),
				board.getWriter(), board.getPassword(), board.getNo()) > 0 ? true : false;
	}

	@Override
	public boolean removeBoard(int no) {
		// TODO Auto-generated method stub
		final String sql = "delete from board where no=?";
		return this.getJdbcTemplate().update(sql, no) > 0 ? true : false;
	}

	@Override
	public int getMaxPage() {
		// TODO Auto-generated method stub
		final String sql = "select count(no) from board";
		return (int)Math.ceil((double)this.getJdbcTemplate().queryForInt(sql) / LINES_PER_PAGE);
	}

	@Override
	public boolean checkPassword(int no, String password) {
		// TODO Auto-generated method stub
		final String sql = "select count(no) from board where no=? and password=?";
		return this.getJdbcTemplate().queryForInt(sql, no, password) > 0 ? true : false;
	}

	@Override
	public boolean raiseLookUpCount(int no) {
		// TODO Auto-generated method stub
		final String sql = "update board set ref=ref+1 where no=?";
		return this.getJdbcTemplate().update(sql, no) > 0 ? true : false;
	}
}
