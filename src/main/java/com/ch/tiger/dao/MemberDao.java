package com.ch.tiger.dao;

import com.ch.tiger.model.Member;

public interface MemberDao {
	Member select(String mb_id); // ȸ������ ���̵� �ߺ�üũ, �α����Ҷ� ����ϴ� ����

	Member selectNick(String mb_nickName); // �г��� �ߺ�üũ

	int insert(Member member); // ȸ������ �Է�

	Member selectFindId(Member member); // ���̵� ã��

	Member selectFindPw(Member member); // ��й�ȣ ã��

	int update(Member member); // ȸ������ ����

}
