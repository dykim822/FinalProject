package com.ch.tiger.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.tiger.model.Member;

@Repository
public class MemberDaoImpl implements MemberDao{
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public Member select(String mb_id) {
		return sst.selectOne("memberns.select", mb_id); // �α����� �� ����ϴ� ����
	}

	@Override
	public Member selectNick(String mb_nickName) {
		return sst.selectOne("memberns.selectNick", mb_nickName); // �г��� �ߺ� üũ
	}

	@Override
	public int insert(Member member) {
		return sst.insert("memberns.insert", member); // ȸ������
	}

	@Override
	public Member selectFindId(Member member) {
		return sst.selectOne("memberns.selectFindId", member);
	}

	@Override
	public Member selectFindPw(Member member) {
		return sst.selectOne("memberns.selectFindPw", member);
	}

	@Override
	public int update(Member member) {
		return sst.update("memberns.update", member);
	}
	
}
