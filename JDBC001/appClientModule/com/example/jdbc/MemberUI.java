package com.example.jdbc;

import java.util.List;

public class MemberUI {
	
	// MemberDAO가 주입
	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public void start() {
		// TODO Auto-generated method stub
		memberService.removeAllMember();
		System.out.println("작업 : 전부 삭제!!");
		showMembers();
		
		memberService.addMember(new Member(0, "철수", "cskim@naver.com", "010-1111-1111"));
		memberService.addMember(new Member(0, "영희", "yhkim@gmail.com", "010-2222-2222"));
		System.out.println("작업 : 2건 추가!!");
		showMembers();
		
		Member member = memberService.getMember(memberService.getLastNo());
		member.setName("영철");
		memberService.updateMember(member);
		System.out.println("작업 : 영희를 영철로 변경!!");
		showMembers();

		memberService.addMember(new Member(0, "찬호", "chpark@hotmail.com", "010-3333-3333"));
		System.out.println("작업 : 1건 추가!!");
		showMembers();
		
		int lastNo = memberService.getLastNo();
		memberService.removeMember(lastNo);
		System.out.println("작업 : 찬호 삭제!!");
		showMembers();
	}
	
	private void showMembers() {
		List<Member> members = memberService.getAllMembers();

		System.out.println("데이터베이스의 현재 상태");
		System.out.println("================================================================");
		if(members.size() > 0) {
			for(Member member : members) {
				System.out.println("번호 : " + member.getNo() + 
						", 이름 : " + member.getName() + 
						", 이메일 : " + member.getEmail() +
						", 전화번호 : " + member.getPhone());
			}
		} else {
			System.out.println("없음");
		}
		System.out.println("================================================================\n");
	}

}
