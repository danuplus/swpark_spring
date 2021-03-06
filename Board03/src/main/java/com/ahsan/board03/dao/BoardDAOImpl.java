package com.ahsan.board03.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ahsan.board03.domain.Board;
import com.ahsan.board03.domain.Reply;

public class BoardDAOImpl extends JdbcDaoSupport implements BoardDAO {

	private static final int LINES_PER_PAGE = 15;
	
	@Override
	public List<Board> getBoardsByPage(int page) {
		// TODO Auto-generated method stub
		final String sql = "select no, title, writer, ref, reply, wdate " +
						   "from (select rownum rn, t1.* " +
						   "      from (select * from board3 order by no desc) t1 " +
						   "      where rownum <= ?) " +
						   "where rn >= ?";
		int start = (page-1) * LINES_PER_PAGE + 1;
		int stop = (page*LINES_PER_PAGE);
		return this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Board>(Board.class), stop, start);
	}

	@Override
	public int getMaxPage() {
		// TODO Auto-generated method stub
		final String sql = "select count(no) from board3";
		int total = this.getJdbcTemplate().queryForInt(sql);
		return (int)Math.ceil((double)total/LINES_PER_PAGE);
	}

	@Override
	public int raiseLookupCount(int no) {
		// TODO Auto-generated method stub
		final String sql = "update board3 set ref=ref+1 where no=?";
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public Board getBoardByNo(int no) {
		// TODO Auto-generated method stub
		final String sql = "select * from board3 where no=?";
		return this.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<Board>(Board.class), no);
	}

	@Override
	public List<Reply> getRepliesByReference(int reference) {
		// TODO Auto-generated method stub
		final String sql = "select no, writer, memo, wdate from reply where ref_no=? order by no";
		return this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Reply>(Reply.class), reference);
	}

	@Override
	public int addReply(Reply reply) {
		// TODO Auto-generated method stub
		final String sql = "insert into reply(no, writer, password, memo, wdate, ref_no) " +
						   "values(reply_no_seq.nextval, ?, ?, ?, sysdate, ?)";
		
		return this.getJdbcTemplate().update(sql, reply.getWriter(), reply.getPassword(), 
				reply.getMemo(), reply.getRef_no());
	}

	@Override
	public int raiseReplyCount(int no) {
		// TODO Auto-generated method stub
		final String sql = "update board3 set reply=reply+1 where no=?";
		
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public boolean checkPasswordForReply(int no, String password) {
		// TODO Auto-generated method stub
		final String sql = "select count(no) from reply where no=? and password=?";
		
		return this.getJdbcTemplate().queryForInt(sql, no, password) > 0;
	}

	@Override
	public int removeReply(int no) {
		// TODO Auto-generated method stub
		final String sql = "delete from reply where no=?";
		
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public int lowerReplyCount(int no) {
		// TODO Auto-generated method stub
		final String sql = "update board3 set reply=reply-1 where no=?";
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public boolean checkPassword(int no, String password) {
		// TODO Auto-generated method stub
		final String sql = "select count(no) from board3 where no=? and password=?";
		
		return this.getJdbcTemplate().queryForInt(sql, no, password) > 0;
	}

	@Override
	public int removeBoard(int no) {
		// TODO Auto-generated method stub
		final String sql = "delete from board3 where no=?";
		
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public int removeReplyForBoard(int no) {
		// TODO Auto-generated method stub
		final String sql = "delete from reply where ref_no=?";
		
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public int modifyBoard(Board board) {
		// TODO Auto-generated method stub
		final String sql = "update board3 set writer=?, password=?, title=?, content=? " +
						   "where no=?";
		return this.getJdbcTemplate().update(sql, board.getWriter(), board.getPassword(), 
				board.getTitle(), board.getContent(), board.getNo());
	}

	@Override
	public int addBoard(Board board) {
		// TODO Auto-generated method stub
		final String sql = "insert into board3(no, title, content, writer, password, ref, reply, wdate) " +
						   "values(board3_no_seq.nextval, ?, ?, ?, ?, 0, 0, sysdate)";
		return this.getJdbcTemplate().update(sql, board.getTitle(), board.getContent(),
				board.getWriter(), board.getPassword());
	}

}
