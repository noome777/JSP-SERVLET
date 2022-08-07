package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.service.MemberService;
import com.kh.member.vo.MemberVo;

@WebServlet(urlPatterns = "/member/login")
public class MemberLoginController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 인코딩
		req.setCharacterEncoding("UTF-8");
		
		//2. 데이터 꺼내기
		String id = req.getParameter("memberId");
		String pwd = req.getParameter("memberPwd");
		
		//3. 데이터 뭉치기
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		//4. 서비스 호출하기
		MemberVo loginMember = new MemberService().login(vo);
		
		//5. 결과에 따른 화면 선택하기
		if(loginMember != null) {
			//성공화면
			req.getSession().setAttribute("loginMember", loginMember);
			req.getSession().setAttribute("alertMsg", "로그인 성공 !");
			
			resp.sendRedirect("/lars");
			
		}else {
			//실패화면
			req.getSession().setAttribute("errorMsg", "로그인 실패 !");
			req.getRequestDispatcher("/views/error/errorPage.jsp").forward(req, resp);
		}
		
		
	}
	
	

}
