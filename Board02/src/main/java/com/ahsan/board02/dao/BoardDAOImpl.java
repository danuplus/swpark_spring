package com.ahsan.board02.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.ahsan.board02.domain.Board;

public class BoardDAOImpl extends NamedParameterJdbcDaoSupport implements
		BoardDAO {

	private static final int LINES_PER_PAGE = 15;
	
	@Override
	public List<Board> getBoardsByPage(int page) {
		// TODO Auto-generated method stub

		final String sql = "select * " +
						   "from (select rownum rn, t1.* " +
						   "      from (select * from board2 order by group_no desc, sequence_in_group) t1 " +
						   "      where rownum <= ?) " +
						   "where rn >= ?";
		int start = (page-1)*LINES_PER_PAGE+1;
		int stop = page*LINES_PER_PAGE;
		
		return this.getJdbcTemplate().query(sql, new RowMapper<Board>() {

			@Override
			public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Board board = new Board();
				board.setNo(rs.getInt("no")); // �۹�ȣ
				
				int indent = rs.getInt("indent_in_group");
				boolean existParent = rs.getInt("ref_no")!=-1;
				board.setTitle(addTitleHeader(indent, existParent) + rs.getString("title"));
				
				board.setRef(rs.getInt("ref")); // ��ȸ��
				board.setWriter(rs.getString("writer")); // �ۼ���
				board.setWdate(rs.getTimestamp("wdate")); //�ۼ���
				
				return board;
			}
			
		}, stop, start);
	}

	private String addTitleHeader(int indent, boolean exitParent) {
		StringBuffer sb = new StringBuffer(); 
		for(int i=0; i<indent; i++) { //�鿩����
			sb.append("&nbsp;&nbsp;&nbsp;");
		}
		if(!exitParent) { // ������ ���翩��
			sb.append("<span class='error-text'>[������ ������]</span>");
		}
		return sb.toString();
	}
	
	@Override
	public Board getBoardByNo(int no) {
		// TODO Auto-generated method stub
		final String sql = "select * from board2 where no=?";
		return this.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<Board>(Board.class), no);

	}

	@Override
	public int addBoard(Board board) {
		// TODO Auto-generated method stub
		int no = getSequence();
		board.setNo(no);			// �Ϸù�ȣ
		
		if(board.getRef_no() > 0) {	// �� ���� ���
			int parent = board.getRef_no();	// ������ ��ȣ
			Board p_board = this.getBoardByNo(parent);

			// ����� ������ ����
			adjustSequence(p_board.getGroup_no(), p_board.getSequence_in_group());
			
			board.setGroup_no(p_board.getGroup_no()); // �θ��� �׷� ��ȣ�� �����ϰ� ����
			board.setSequence_in_group(p_board.getSequence_in_group() + 1); // �θ� �ۿ� 1�� ����
			board.setIndent_in_group(p_board.getIndent_in_group() + 1); // �θ� ���� �ε�Ʈ�� 1�� ����
		} else {	// �� ���� ���
			board.setGroup_no(no);			// �� ���� ���, �Ϸù�ȣ�� �׷� ��ȣ�� ����
			board.setSequence_in_group(0);	// �� ���� ���� �׷� ������ ù ��°
			board.setIndent_in_group(0);	// �� ���� ���� �׷� ������ �ε�Ʈ ����
		}
		
		final String sql = "insert into board2(no, title, content, writer, password, wdate, " +
						   "ref, group_no, sequence_in_group, indent_in_group, ref_no) " +
						   "values(?, ?, ?, ?, ?, sysdate, 0, ?, ?, ?, ?)";
				
		return this.getJdbcTemplate().update(sql, 
				board.getNo(), board.getTitle(), board.getContent(), board.getWriter(), board.getPassword(),
				board.getGroup_no(), board.getSequence_in_group(), board.getIndent_in_group(), board.getRef_no());
	}
	
	private int getSequence() {
		final String sql = "select board2_no_seq.nextval from dual";
		return this.getJdbcTemplate().queryForInt(sql);
	}
	
	private int adjustSequence(int group, int p_sequence) {
		final String sql = "update board2 set sequence_in_group = sequence_in_group+1 " +
						   "where group_no=? and sequence_in_group>?";
		return this.getJdbcTemplate().update(sql, group, p_sequence);
	}

	@Override
	public int modifyBoard(Board board) {
		// TODO Auto-generated method stub
		final String sql = "update board2 set writer=?, title=?, password=?, content=? where no=?";
		return this.getJdbcTemplate().update(sql, board.getWriter(), board.getTitle(), 
				board.getPassword(), board.getContent(), board.getNo());
	}

	@Override
	public int removeBoard(int no) {
		// TODO Auto-generated method stub
		// ���� �� ���� �����ϴ� ����� �ִ� ���, ������ �����Ǿ��ٴ� �ǹ̷� ref_no�� -1�� ����
		adjustReference(no);
		
		final String sql = "delete from board2 where no=?";
		return this.getJdbcTemplate().update(sql, no);
	}
	
	private int adjustReference(int no) {
		final String sql = "update board2 set ref_no=-1 where ref_no=?";
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public int raiseLookUpCount(int no) {
		// TODO Auto-generated method stub
		final String sql = "update board2 set ref=ref+1 where no=?";
		return this.getJdbcTemplate().update(sql, no);
	}

	@Override
	public boolean checkPassword(int no, String password) {
		// TODO Auto-generated method stub
		final String sql = "select count(no) from board2 where no=? and password=?";
		return this.getJdbcTemplate().queryForInt(sql, no, password) > 0 ? true : false;
	}

	@Override
	public int getMaxPage() {
		// TODO Auto-generated method stub
		final String sql = "select count(no) from board2";
		int total = this.getJdbcTemplate().queryForInt(sql);
		return (int)Math.ceil((double)total/LINES_PER_PAGE) ;
	}

}
