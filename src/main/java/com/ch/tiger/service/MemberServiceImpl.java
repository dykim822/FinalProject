package com.ch.tiger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch.tiger.dao.MemberDao;
import com.ch.tiger.model.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao mbd;

	@Override
	public Member select(String mb_id) { // ȸ������ ���̵� �ߺ�üũ, �α����Ҷ� ����ϴ� ����
		return mbd.select(mb_id);
	}

	@Override
	public Member selectNick(String mb_nickName) { // �г��� �ߺ�üũ
		return mbd.selectNick(mb_nickName);
	}

	@Override
	public int insert(Member member) { // ȸ������
		return mbd.insert(member);
	}

	@Override
	public Member selectFindId(Member member) { // ���̵�  ã��
		return mbd.selectFindId(member);
	}

	@Override
	public Member selectFindPw(Member member) { // ��й�ȣ ã��
		return mbd.selectFindPw(member);
	}

	@Override
	public int update(Member member) { // ȸ������ ����
		return mbd.update(member);
	}
}
