package com.ahsan.board.dao;

import java.util.List;

import com.ahsan.board.domain.Board;
import com.ahsan.board.domain.Reply;

public interface BoardDAO {
	public List<Board> getBoardsByPage(int page);
	public int getMaxPage();
	public Board getBoardByNo(int no);
	public List<Reply> getRepliesByRef(int no);
	public int raiseReadCount(int no);
	public int getBoardSequenceNo();
	public int adjustBoardOrder(int group, int sequence);
	public int addBoard(Board board);
	public int addReply(Reply reply);
	public int raiseReplyCount(int no);
	public int lowerReplyCount(int no);
	public boolean checkBoardPassword(int no, String password);
	public boolean checkReplyPassword(int no, String password);
	public int removeBoard(int no);
	public int removeReply(int no);
	public int adjustBoardReference(int no);
	public int removeRepliesForReference(int no);
	public int modifyBoard(Board board);
}
