package com.ahsan.board01.persistence;

import java.util.List;

import com.ahsan.board01.domain.Board;

public interface BoardDAO {
	public List<Board> getBoardByPage(int page);
	public Board getBoardByNo(int no);
	public boolean addBoard(Board board);
	public boolean updateBoard(Board board);
	public boolean removeBoard(int no);
	public int getMaxPage();
	public boolean checkPassword(int no, String password);
	public boolean raiseLookUpCount(int no);
}
