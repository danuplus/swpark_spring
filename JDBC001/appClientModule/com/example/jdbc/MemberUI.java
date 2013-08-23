package com.example.jdbc;

import java.util.List;

public class MemberUI {
	
	// MemberDAO�� ����
	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public void start() {
		// TODO Auto-generated method stub
		memberService.removeAllMember();
		System.out.println("�۾� : ���� ����!!");
		showMembers();
		
		memberService.addMember(new Member(0, "ö��", "cskim@naver.com", "010-1111-1111"));
		memberService.addMember(new Member(0, "����", "yhkim@gmail.com", "010-2222-2222"));
		System.out.println("�۾� : 2�� �߰�!!");
		showMembers();
		
		Member member = memberService.getMember(memberService.getLastNo());
		member.setName("��ö");
		memberService.updateMember(member);
		System.out.println("�۾� : ���� ��ö�� ����!!");
		showMembers();

		memberService.addMember(new Member(0, "��ȣ", "chpark@hotmail.com", "010-3333-3333"));
		System.out.println("�۾� : 1�� �߰�!!");
		showMembers();
		
		int lastNo = memberService.getLastNo();
		memberService.removeMember(lastNo);
		System.out.println("�۾� : ��ȣ ����!!");
		showMembers();
	}
	
	private void showMembers() {
		List<Member> members = memberService.getAllMembers();

		System.out.println("�����ͺ��̽��� ���� ����");
		System.out.println("================================================================");
		if(members.size() > 0) {
			for(Member member : members) {
				System.out.println("��ȣ : " + member.getNo() + 
						", �̸� : " + member.getName() + 
						", �̸��� : " + member.getEmail() +
						", ��ȭ��ȣ : " + member.getPhone());
			}
		} else {
			System.out.println("����");
		}
		System.out.println("================================================================\n");
	}

}
