package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class MemberServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//아이디, 패스워드 꺼내기
		String id = req.getParameter("userId");
		String pwd = req.getParameter("userPwd");
		
		//아이디, 패스워드가 맞는지 확인
		//admin, 1234
		if(id.equals("admin") && pwd.equals("1234")) {
			//로그인 성공 --> 바로 jsp에 넘겨버린다 화면에 표시 해줘~
//			req.getRequestDispatcher("views/loginSuccess.jsp").forward(req, resp); // 상대경로--> 클라이언트가 보낸 요청(로그인 폼의 url이 로그인 성공/ 실패시 노출됨)을 가지고 서블릿쪽에서 jsp로 데이터를 보내는 것
			resp.sendRedirect("views/loginSuccess.jsp"); // 상대경로 --> 클라이언트에게 다시 새로운 요청을 보낼 수 있게 하는 것(이 경로가 로그인 성공/ 실패시 노출됨 -> 한번 보낸 로그인 데이터는 새로고침 하면 없어질 수 있도록 (서버에 과중되니까)
		} else {
			//로그인 실패
//			req.getRequestDispatcher("views/loginFail.jsp").forward(req, resp);//상대경로
			resp.sendRedirect("/loginPrj/views/loginFail.jsp");//절대경로
		}
		
		//로그인 여부에 따라 페이지 이동시키기
	}

}
