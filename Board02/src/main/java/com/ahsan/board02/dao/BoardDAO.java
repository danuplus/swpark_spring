package com.ahsan.board02.dao;

import java.util.List;

import com.ahsan.board02.domain.Board;

public interface BoardDAO {
	public List<Board> getBoardsByPage(int page);
	public int getMaxPage();
	public Board getBoardByNo(int no);
	public int addBoard(Board board);
	public int modifyBoard(Board board);
	public int removeBoard(int no);
	public boolean checkPassword(int no, String password);
	public int raiseLookUpCount(int no);
}
