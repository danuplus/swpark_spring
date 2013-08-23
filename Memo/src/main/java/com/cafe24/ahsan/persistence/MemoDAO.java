package com.cafe24.ahsan.persistence;

import java.util.List;

import com.cafe24.ahsan.domain.Memo;

public interface MemoDAO {
	public List<Memo> getMemoByPage(int page);
	public boolean addMemo(Memo memo);
	public boolean removeMemo(Integer no);
	public boolean checkPassword(Integer no, String password);
	public int getTotalPage();
}
