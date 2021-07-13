package com.ch.tiger.controller;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ch.tiger.model.Member;
import com.ch.tiger.model.Notice;
import com.ch.tiger.service.ApplyService;
import com.ch.tiger.service.CarpoolService;
import com.ch.tiger.service.MemberService;
import com.ch.tiger.service.NoticeService;
import com.ch.tiger.service.PagingBean;
import com.ch.tiger.service.QnAService;
import com.ch.tiger.service.ReportService;
import com.ch.tiger.service.ReservationService;
import com.ch.tiger.service.ReviewService;
import com.ch.tiger.service.VehicleService;
@Controller
public class AdminController {
	@Autowired
	private ApplyService as;
	@Autowired
	private CarpoolService cs;
	@Autowired
	private MemberService mbs;
	@Autowired
	private NoticeService ns;
	@Autowired
	private QnAService qs;
	@Autowired
	private ReportService rps;
	@Autowired
	private ReservationService rvs;
	@Autowired
	private ReviewService rs;
	@Autowired
	private VehicleService vs;
	@RequestMapping("adminMbList")
	public String adminMbList(Member member, String pageNum, Model model, HttpSession session) {
		if(pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int rowPerPage = 10;	// 한 화면에 보여주는 게시글 갯수
		int total = mbs.getMbTotal(member);
		int startRow = (currentPage -1) * rowPerPage + 1;
		int endRow = startRow + rowPerPage - 1;
//		Member memberDB = mbs.select(member.getMB_id());
		member.setStartRow(startRow);
		member.setEndRow(endRow);
		List<Member> list = mbs.list(member);	// 회원 목록
		int num = total - startRow + 1;
		PagingBean pb = new PagingBean(currentPage, rowPerPage, total);
//		String MB_id = (String)session.getAttribute("MB_id");
//		if(MB_id.equals("admin")) {
//			model.addAttribute("member", member);
//		}
		model.addAttribute("member", member);
		model.addAttribute("pb", pb);	// paginbean pb
		model.addAttribute("list", list);
		model.addAttribute("num", num);
		return "admin/adminMbList";
	}
	@RequestMapping("noticeWriteForm")
	public String noticeWriteForm(int NT_num, String pageNum, Model model) {
		model.addAttribute("NT_num", NT_num);
		model.addAttribute("pageNum", pageNum);
		return "notice/noticeWriteForm";
	}
	@RequestMapping("noticeWriteResult")
	public String noticeWrite(Member member, int NT_num, Notice notice, String pageNum,
			Model model, HttpSession session) {
		int number = ns.getMaxNum();	// 공지사항 번호 생성 용도
		session.setAttribute("MB_num", member.getMB_num());
//		notice.setMB_num(MB_num);
		notice.setNT_num(number);
		int result = ns.noticeWrite(notice);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "notice/noticeWriteResult";
	}
	@RequestMapping("noticeUpdateForm")
	public String noticeUpdateForm(int NT_num, String pageNum, Model model, HttpSession session) {
		Notice notice = ns.select(NT_num);
		String MB_id = (String)session.getAttribute("MB_id");
		Member memberDB = mbs.select(MB_id);
		model.addAttribute("memberDB", memberDB);	// 아이디로 DB에 있는 회원정보 조회
		model.addAttribute("notice", notice);
		model.addAttribute("pageNum", pageNum);
		return "notice/noticeUpdateForm";
	}
	@RequestMapping("noticeUpdateResult")
	public String noticeUpdate(Notice notice, String pageNum, Model model) {
		int result = ns.noticeUpdate(notice);
		model.addAttribute("notice", notice);	// 공지사항 수정 후 view로 넘어갈 때 데이터 필요
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "notice/noticeUpdateResult";
	}
	@RequestMapping("noticeDelete")
	public String noticeDelete(int NT_num, String pageNum, Model model, HttpSession session) {
		int result = ns.noticeDelete(NT_num);
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		return "notice/noticeDelete";
	}
}
