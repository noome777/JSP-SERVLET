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
	 * 마이페이지 보여주기
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
		
		//이게 null이 아니라면 로그인 되어있는 상태
		if(loginMember != null) {
			req.getRequestDispatcher("/views/member/myPageForm.jsp").forward(req, resp);
		}else {
			req.getSession().setAttribute("alertMsg", "로그인 후 접근 가능합니다.");
			resp.sendRedirect(req.getContextPath());
		}
		
	}


/*
 * 회원 정보 변경
 * 
 * 컨트롤러
 * - 데이터 받기 -> 객체
 * - 서비스 호출(객체)
 * - 실행결과에 따라 화면 선택
 * 
 * 서비스
 * - 비지니스 로직 (자바 || sql)
 * - 트랜잭션 처리 (commit || rollback)
 * - 실행결과 리턴
 * 
 * DAO
 * - SQL 실행 (전달받은 객체 활용)
 * 		-SELECT 쿼리 -> rs를 객체로 변환
 * 		- DML 쿼리 -> 추가작업 X, int로 반환
 * - SQL 실행 결과 리턴
 * 
 */

 	@Override
 	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 		//데이터 받기 -> 객체
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
// 		vo.setNo(세션의 로그인 멤버의 넘버);
 		vo.setNo(no);
 		vo.setName(name);
 		vo.setEmail(Email);
 		vo.setPhone(Phone);
 		vo.setAddr(Addr);
 		vo.setInterest(String.join(",", interest));
 		
 		
 		//서비스 호출(객체)
 		int result = new MemberService().edit(vo);
 		
 		if(result == 1) {
 			//성공화면
 			System.out.println("정보 수정 성공");
 		}else {
 			//실패화면
 			System.out.println("정보 수정 실패");
 			
 		}
 		
	 
 	}
 
}