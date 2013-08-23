package com.cafe24.ahsan.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.cafe24.ahsan.domain.Memo;

public class MemoDAOImpl extends JdbcDaoSupport implements MemoDAO {
	private static final int LINES_PER_PAGE = 15;
	
	@Override
	public List<Memo> getMemoByPage(int page) {
		// TODO Auto-generated method stub
		final String sql = "select no, memo, writer, password, wdate " +
							"from (select rownum rn, t1.* " +
							      "from (select * from memo order by no desc) t1 " +
							      "where rownum <= ?) " +
							"where rn >= ?";

		int start = (page-1) * LINES_PER_PAGE + 1;
		int stop = page * LINES_PER_PAGE;
		
		List<Memo> memos = this.getJdbcTemplate().query(sql, new RowMapper<Memo>() {

			@Override
			public Memo mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Memo memo = new Memo();
				memo.setNo(rs.getInt("no"));
				memo.setMemo(rs.getString("memo"));
				memo.setWriter(rs.getString("writer"));
				memo.setPassword(rs.getString("password"));
				memo.setWdate(rs.getTimestamp("wdate"));
				return memo;
			}
			
		}, stop, start);
		
		return memos;
	}

	@Override
	public boolean addMemo(Memo memo) {
		// TODO Auto-generated method stub
		final String sql = "insert into memo(no, memo, writer, password, wdate) " +
						"values(memo_no_seq.nextval, ?, ?, ?, sysdate)";
		return this.getJdbcTemplate().update(sql, memo.getMemo(), memo.getWriter(), memo.getPassword()) > 0;
	}

	@Override
	public boolean removeMemo(Integer no) {
		// TODO Auto-generated method stub
		final String sql = "delete from memo where no=?";
		return this.getJdbcTemplate().update(sql, no) > 0;
	}

	@Override
	public boolean checkPassword(Integer no, String password) {
		// TODO Auto-generated method stub
		final String sql = "select count(no) from memo where no=? and password=?";
		int result = this.getJdbcTemplate().queryForInt(sql, no, password);
		
		return (result==0)?false:true;
	}

	@Override
	public int getTotalPage() {
		// TODO Auto-generated method stub
		final String sql = "select count(no) from memo";
		int result = this.getJdbcTemplate().queryForInt(sql);
		
		return (int)Math.ceil((double)result/LINES_PER_PAGE);
	}	
}
