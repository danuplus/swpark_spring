package com.ahsan.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ahsan.board.domain.Board;
import com.ahsan.board.domain.Reply;

public class BoardDAOImpl extends JdbcDaoSupport implements BoardDAO {

	private static final int LINES_PER_PAGE = 15;
	
	@Override
	public List<Board> getBoardsByPage(int page) {
		// TODO Auto-generated method stub
		final String sql = "select * " +
						   "from (select rownum rn, t1.* " +
						   "      from (select * from board4 order by group_no desc, sequence_in_group) t1 " +
						   "      where rownum <= ?) " +
						   "where rn >= ?";
		int start = (page-1) * LINES_PER_PAGE + 1;
		int stop = page * LINES_PER_PAGE;
		return this.getJdbcTemplate().query(sql, new RowMapper<Board>() {

			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Board board = new Board();
				board.setNo(rs.getInt("no"));
				board.setWriter(rs.getString("writer"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setWdate(rs.getTimestamp("wdate"));
				board.setRead_cnt(rs.getInt("read_cnt"));
				board.setReply_cnt(rs.getInt("reply_cnt"));
				board.setGroup_no(rs.getInt("group_no"));
				board.setSequence_in_group(rs.getInt("sequence_in_group"));
				board.setIndent_in_group(rs.getInt("indent_in_group"));
				board.setRef_no(rs.getInt("ref_no"));
				
				// 들여쓰기 레벨에 비례하여 제목 앞에 여백 추가. 원글이 삭제된 경우, 적절한 메시지 추가
				if(board.getIndent_in_group() > 0 || board.getRef_no() == -1) {
					StringBuffer sb = new StringBuffer();
					for(int i=0; i<board.getIndent_in_group(); i++) {
						sb.append("&nbsp;&nbsp;&nbsp;&nbsp;");
					}
					if(board.getRef_no()==-1)
						sb.append("<span class='error-text'>[원글이 삭제됨]</span>");
					board.setTitle(sb.toString() + board.getTitle());
				}
				
				return board;
			}
			
		}, stop, start);
	}

	@Override
	public int getMaxPage() {
		// TODO Auto-generated method stub
		final String sql = "select count(no) from board4";
		int total = this.getJdbcTemplate().queryForInt(sql);
		return (int)Math.ceil((double)total/LINES_PER_PAGE);
	}

	@Override
	public Board getBoardByNo(int no) {
		// TODO Auto-generated method stub
		final String sql = "select * from board4 where no=?";
		return this.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<Board>(Board.class), no);
	}

	@Override
	public List<Reply> getRepliesByRef(int no) {
		// TODO Auto-generated method stub
		final String sql = "select * from reply2 where ref_no=?";
		return this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Reply>(Reply.class), no);
	}

	@Override
	public int raiseReadCount(int no) {
		// TODO Auto-generated method stub
		final String sql = "update board4 set read_cnt=read_cnt+1 where no=?";
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public int addBoard(Board board) {
		// TODO Auto-generated method stub
		final String sql = "insert into board4(no, writer, password, title, content, " +
						   "wdate, read_cnt, reply_cnt, group_no, sequence_in_group, " +
						   "indent_in_group, ref_no) " +
						   "values(?, ?, ?, ?, ?, " +
						   "sysdate, ?, ?, ?, ?, " +
						   "?, ?)";
		
		return this.getJdbcTemplate().update(sql, 
				board.getNo(), board.getWriter(), board.getPassword(), board.getTitle(), board.getContent(),
				board.getRead_cnt(), board.getReply_cnt(), board.getGroup_no(),	board.getSequence_in_group(),
				board.getIndent_in_group(),	board.getRef_no());
	}

	@Override
	public int getBoardSequenceNo() {
		final String sql = "select board4_no_seq.nextval from dual";
		return this.getJdbcTemplate().queryForInt(sql);
	}
	
	@Override
	public int adjustBoardOrder(int group, int sequence) {
		final String sql = "update board4 set sequence_in_group=sequence_in_group+1 " +
						   "where group_no=? and sequence_in_group>?";
		return this.getJdbcTemplate().update(sql, group, sequence);
	}

	@Override
	public int addReply(Reply reply) {
		// TODO Auto-generated method stub
		final String sql = "insert into reply2(no, writer, password, memo, wdate, ref_no) " +
						   "values(reply2_no_seq.nextval, ?, ?, ?, sysdate, ?)";
		return this.getJdbcTemplate().update(sql, reply.getWriter(), reply.getPassword(), 
				reply.getMemo(), reply.getRef_no());
	}

	@Override
	public int raiseReplyCount(int no) {
		// TODO Auto-generated method stub
		final String sql = "update board4 set reply_cnt=reply_cnt+1 where no=?";
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public int lowerReplyCount(int no) {
		// TODO Auto-generated method stub
		final String sql = "update board4 set reply_cnt=reply_cnt-1 where no=?";
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public boolean checkBoardPassword(int no, String password) {
		// TODO Auto-generated method stub
		final String sql = "select count(no) from board4 where no=? and password=?";
		return this.getJdbcTemplate().queryForInt(sql, no, password)>0 ? true : false;
	}

	@Override
	public boolean checkReplyPassword(int no, String password) {
		// TODO Auto-generated method stub
		final String sql = "select count(no) from reply2 where no=? and password=?";
		return this.getJdbcTemplate().queryForInt(sql, no, password)>0 ? true : false;
	}

	@Override
	public int removeBoard(int no) {
		// TODO Auto-generated method stub
		final String sql = "delete from board4 where no=?";
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public int adjustBoardReference(int no) {
		final String sql = "update board4 set ref_no=-1 where ref_no=?";
		return this.getJdbcTemplate().update(sql, no);
	}
	
	@Override
	public int removeReply(int no) {
		// TODO Auto-generated method stub
		final String sql = "delete from reply2 where no=?";
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public int removeRepliesForReference(int no) {
		// TODO Auto-generated method stub
		final String sql = "delete from reply2 where ref_no=?";
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public int modifyBoard(Board board) {
		// TODO Auto-generated method stub
		final String sql = "update board4 set writer=?, password=?, title=?, content=? where no=?";
		return this.getJdbcTemplate().update(sql, board.getWriter(), board.getPassword(), 
				board.getTitle(), board.getContent(), board.getNo());
	}

}
