package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.servicce.MemberService;
import com.kh.member.vo.MemberVo;

@WebServlet(urlPatterns = "/member/myPage")
public class MemberMypageController extends HttpServlet {

	/*
	 * 마이페이지 보여주기 (화면 보여주는 용도)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그인이 되어 있을 때만 포워딩이 될 수 있도록 만들어주는 것 -> 클라이언트가 로그인 안 한 상태로 url에 이 주소창을 입력해서 그냥 들어와버리는 것을 방지하기 위해서
		//세션을 가져온 다음에 MemberVo타입의 로그인 멤버라는 객체가 존재하지 않는지 확인하는 단계 -> 로그인멤버가 null이라면 로그인이 되어있지 않은 상태이다.
		MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
		
		//로그인 되지 않은 상태에서 url로 페이지 들어가는 것 방지용 체크
		//이게 null이 아니라면 로그인 되어있는 상태이므로 마이페이지로 보낸다. null이면 비로그인 상태이므로, 메세지를 담아서 메인페이지(req.getContextPath())로 보내준다.
		if(loginMember != null) {
			req.getRequestDispatcher("/views/member/myPageForm.jsp").forward(req, resp);
		}else {
			req.getSession().setAttribute("alertMsg", "로그인 후 접근 가능합니다.");
			//포워딩으로 하면 사용자가 보낸 마지막 요청이 접근하면 안 되는 잘못된 url이 남아있으므로, 새로고침시에 다시 잘못된 마이페이지 요청을 보내게 될까봐 리다이렉트로 해주는 것임(직접 사용자에게 한 번 더 요청을 보내도록)
			resp.sendRedirect(req.getContextPath());
		}
		
	}


/*
 * 회원 정보 변경 (진짜 컨트롤러 -> 서비스 -> DAO 거치는 용도)
 * 
 * MVC2 
 * 컨트롤러
 * - 데이터 받기 -> 객체
 * - 서비스 호출(객체) -> 서비스 단계
 * - 실행결과에 따라 화면 선택
 * 
 * 서비스
 * - 비지니스 로직 (자바 || sql)
 * - 트랜잭션 처리 (commit || rollback) -> dao단계
 * - 실행결과 리턴
 * 
 * DAO
 * - SQL 준비~결과저장(5단계) (전달받은 객체 활용)
 * 		- SELECT 쿼리 -> rs를 객체로 변환
 * 		- DML 쿼리 -> 추가작업 X, int로 반환
 * - SQL 실행 결과 리턴
 * 
 */

 	@Override
 	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 		//1. 데이터 받기 -> 객체
 		req.setCharacterEncoding("UTF-8");
 		String name = req.getParameter("memberName");
 		String Email = req.getParameter("memberEmail");
 		String Phone = req.getParameter("memberPhone");
 		String Addr = req.getParameter("memberAddr");
 		String[] interest = req.getParameterValues("interest");
 		int no = ((MemberVo) req.getSession().getAttribute("loginMember")).getNo();
 		
// 		HttpSession session = req.getSession();
// 		MemberVo temp = (MemberVo) req.getSession().getAttribute("loginMember");
// 		int no = temp.getNo();
 		
 		MemberVo vo = new MemberVo();
// 		vo.setNo(세션에 존재하는 로그인 멤버의 넘버(no)를 가져오는 작업);
 		vo.setNo(no);
 		vo.setName(name);
 		vo.setEmail(Email);
 		vo.setPhone(Phone);
 		vo.setAddr(Addr);
 		vo.setInterest(String.join(",", interest));
 		
 		
 		//2. 서비스 호출(객체)
 		MemberVo updateVo = new MemberService().edit(vo);
 		
 		//3. 실행결과에 따라 화면 선택
 		if(updateVo != null) {
 			//성공화면
 			//세션에 들어있는 로그인 멤버의 정보를 업데이트
 			req.getSession().setAttribute("loginMember", updateVo);
 			
 			//다시한번 마이페이지로 보내주기
 			resp.sendRedirect("/semi/member/myPage");
 			
 			System.out.println("정보 수정 성공");
 		}else {
 			//실패화면
 			req.setAttribute("errorMsg", "회원 정보 수정 실패");
 			req.getRequestDispatcher("/views/error/errorPage.jsp").forward(req, resp);
 			
 			System.out.println("[ERROR]" + "정보 수정 실패");
 			
 		}
 		
	 
 	}
 
}