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
	
}
