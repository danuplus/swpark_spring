package com.ahsan.board03.dao;

import java.util.List;

import com.ahsan.board03.domain.Board;
import com.ahsan.board03.domain.Reply;

public interface BoardDAO {
	public List<Board> getBoardsByPage(int page);
	public int getMaxPage();
	public int raiseLookupCount(int no);
	public Board getBoardByNo(int no);
	public List<Reply> getRepliesByReference(int reference);
	public int addReply(Reply reply);
	public int raiseReplyCount(int no);
	public int lowerReplyCount(int no);
	public boolean checkPasswordForReply(int no, String password);
	public int removeReply(int no);
	public boolean checkPassword(int no, String password);
	public int removeBoard(int no);
	public int removeReplyForBoard(int no);
	public int modifyBoard(Board board);
	public int addBoard(Board board);
}
