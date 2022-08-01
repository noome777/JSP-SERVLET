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

@WebServlet(urlPatterns = "/member/login")
public class memberLoginController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 꺼내기
		String memberId = req.getParameter("memberId");
		String memberPwd = req.getParameter("memberPwd");
		
		//데이터 뭉치기
		MemberVo vo = new MemberVo();
		vo.setId(memberId);
		vo.setPwd(memberPwd);
		
		//서비스 로직 실행
		MemberVo loginMember = new MemberService().login(vo);
		
		//결과에 따라 화면 선택
		if(loginMember != null) {
			//로그인 성공 -> 세션에 로그인 유저의 정보 담아주기
//			HttpSession session = req.getSession();
//			session.setAttribute("loginMember", loginMember);
			req.getSession().setAttribute("loginMember", loginMember);
			
//			req.getRequestDispatcher("다음타자(성공화면)").forward(req, resp);
//			req.getRequestDispatcher("/semi").forward(req, resp); --> 그런데 위에 세션에서 데이터를 저장하고 있으니까 굳이 포워딩으로 할 필요 없이 리다이렉트로 하면 됨
			resp.sendRedirect("/semi");
			
		}else {
			//로그인 실패
//			req.getRequestDispatcher("다음타자(실패화면)").forward(req, resp);
			req.getRequestDispatcher("").forward(req, resp);
		}
	}
	
}
