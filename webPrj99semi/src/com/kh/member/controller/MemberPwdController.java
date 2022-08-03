package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.repository.MemberDao;
import com.kh.member.servicce.MemberService;

@WebServlet(urlPatterns = "/member/pwd")
public class MemberPwdController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		//1. 데이터꺼내기
		String memberId = req.getParameter("memberId");
		String memberPwd = req.getParameter("memberPwd");
		String memberPwdNew = req.getParameter("memberPwdNew");
		String memberPwdNew2 = req.getParameter("memberPwdNew2");
		
		//데이터 뭉치기 ㄴㄴ -> 마땅한 객체가 없으니까
		
		//2. 서비스 호출
		//update 되면 int 타입의 결과 나올 것
		int result = new MemberService().changePwd(memberId, memberPwd, memberPwdNew, memberPwdNew2 );
		
		//3. 실행 결과에 따라 화면선택
		if(result == 1) {
			//성공
			req.getSession().setAttribute("alertMsg", "비밀번호 변경 성공!");
			resp.sendRedirect("/semi/member/myPage");
		}else {
			//실패
			//에러페이지
			req.setAttribute("errorMsg", "비밀번호 변경 실패 ...");
			req.getRequestDispatcher("/views/error/errorPage.jsp").forward(req,resp);
		}
		
	}

}
