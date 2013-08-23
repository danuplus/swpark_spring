package com.example.jdbc;

import java.util.List;

public interface MemberService {
	public List<Member> getAllMembers();
	public boolean addMember(Member member);
	public boolean removeMember(int no);
	public boolean updateMember(Member member);
	public Member getMember(int no);
	public boolean removeAllMember();
	public int getLastNo();
}
