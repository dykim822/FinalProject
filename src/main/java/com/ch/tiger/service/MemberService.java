package com.ch.tiger.service;

import com.ch.tiger.model.Member;

public interface MemberService {
	Member select(String mb_id); // ���̵�� ������ �������� ����

	Member selectNick(String mb_nickName); // �г��� �ߺ� üũ

	int insert(Member member); // ȸ������

	Member selectFindId(Member member); // ���̵� ã��

	Member selectFindPw(Member member); // ��й�ȣ ã��

	int update(Member member);

}
