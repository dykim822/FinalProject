package com.ch.tiger.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch.tiger.model.Member;
import com.ch.tiger.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService mbs;
	
	// ȸ������ ������ �̵�
	@RequestMapping("joinForm")
	public String joinForm() {
		return "member/joinForm";
	}
	
	// ���̵� �ߺ�üũ �Ҷ� ����ϴ� ����
	@RequestMapping(value = "idChk", produces = "text/html;charset=utf-8")
	@ResponseBody // jsp�� �������� �ٷ� �������� ����
	public String idChk(String MB_id) {
		String msg="";
		Member member = mbs.select(MB_id);
		if (member == null) {
			msg = "��� ���� �� �̸��� �Դϴ�.";
		} else {
			msg = "�̹� ������� �̸��� �Դϴ�.";
		}
		return msg;
	}
	
	// ȸ������ ����
	@RequestMapping("join")
	public String join(Member member, Model model) {
		int result = 0;
		// form ���� �Է��� member�����͸� �����ͼ� member2 ��ü�� �����Ͽ� ���̵� �����ϴ��� Ȯ��
		Member member2 = mbs.select(member.getMB_id());
		
		return null;
	}
	
	
	// �α��� ������ �̵�
	@RequestMapping("loginForm")
	public String loginForm() {
		return "member/loginForm";
	}
	
	// �α���
	@RequestMapping("login")
	public String login(Member member, Model model, HttpSession session) { // �α���
		int result = 0;
		Member member2 = mbs.select(member.getMB_id()); // ���̵� �˻��ϴµ� �Ű������� id�� �Ѵ�.
		if (member2 != null || member2.getMB_del().equals("n")) {
			if (member2.getMB_pw().equals(member.getMB_pw())) {
				session.setAttribute("id", member.getMB_id());
				result = 1; // member2�� null�̾ƴϰ� del�� n�϶� �� ��ü�� ��й�ȣ�� �����ϸ� 1
			} else {
				result = 0; // ��й�ȣ�� �ٸ���  0
			}
		} else {
			result = -1;
		}
		model.addAttribute("result", result);
		return "member/memberMain";
	}
}
