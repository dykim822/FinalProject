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
		String msg = "";
		Member member = mbs.select(MB_id);
		if (member == null) {
			msg = "��� ���� �� �̸��� �Դϴ�.";
		} else {
			msg = "�̹� ������� �̸��� �Դϴ�.";
		}
		return msg;
	}

	// �г��� �ߺ�üũ
	@RequestMapping(value = "nickChk", produces = "text/html;charset=utf-8")
	@ResponseBody // jsp�� �������� �ٷ� �������� ����
	public String nickChk(String MB_nickName) {
		String msg = "";
		Member member = mbs.selectNick(MB_nickName);
		if (member == null) {
			msg = "��� ���� �� �г����Դϴ�.";
		} else {
			msg = "�̹� ������� �г����Դϴ�.";
		}
		return msg;
	}

	// ȸ������ ����
	@RequestMapping("join")
	public String join(Member member, Model model) {
		int result = 0;
		// form ���� �Է��� member�����͸� �����ͼ� member2 ��ü�� �����Ͽ� ���̵� �����ϴ��� Ȯ��
		Member member2 = mbs.select(member.getMB_id());
		if (member2 == null) {
			result = mbs.insert(member);
		} else {
			result = -1;
		}
		model.addAttribute("result", result);
		return "member/joinResult";
	}

	// �α��� ������ �̵�
	@RequestMapping("loginForm")
	public String loginForm() {
		return "member/loginForm";
	}

	// �α���
	@RequestMapping("login")
	public String login(Member member, Model model, HttpSession session) {
		// memberDB ; DB ������
		Member memberDB = mbs.select(member.getMB_id());
		int result = 0; // ��ȣ�� ��ġ���� �ʴ� ���
		if (memberDB == null || memberDB.getMB_del().equals("Y")) {
			result = -1; // DB�� ���� ���̵�
		} else if (memberDB.getMB_pw().equals(member.getMB_pw())) {
			result = 1;
			session.setAttribute("MB_id", member.getMB_id());
		}
		model.addAttribute("result", result);
		return "member/loginResult";
	}
	
	// �α� �ƿ�
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/logout";
	}
	
	// ���̵� ã�� ������ �̵�
	@RequestMapping("findIdForm")
	public String findIdForm() {
		return "member/findIdForm";
	}
	
	// ���̵� ã��
	@RequestMapping("findIdResult")
	public String findIdResult(Member member, Model model) {
		int result = 0;
		Member member2 = mbs.selectFindId(member);
		if (member2 != null) {
			result = 1;
			model.addAttribute("member", member2);
		} else {
			result = -1;
		}
		model.addAttribute("result", result);
		return "member/findIdResult";
	}
	
	// ��й�ȣ ã�� ������ �̵�
	@RequestMapping("findPwForm")
	public String findPwForm(String MB_id, Model model) {
		// ���̵� ã�� �� ��й�ȣ�� ã���� ���� �ڵ����� �Ѿ�� �ϱ� ����
		model.addAttribute("MB_id", MB_id);
		return "member/findPwForm";
	}
	
	// ��й�ȣ ã��
	@RequestMapping("findPwResult")
	public String findPwResult(Member member, Model model) {
		int result = 0;
		Member member2 = mbs.selectFindPw(member);
		if (member2 != null) {
			result = 1;
			model.addAttribute("member", member2);
		} else {
			result = -1;
		}
		model.addAttribute("result", result);
		return "member/findPwResult";
	}
	
}
