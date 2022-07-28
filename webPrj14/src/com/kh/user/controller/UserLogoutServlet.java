package com.kh.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/user/logout")
public class UserLogoutServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그아웃 처리 == 세션 만료
		HttpSession session = req.getSession();
		//session.invalidate(); //세션만료
		
		//로그아웃 했으므로, 인덱스 페이지 (메인화면) 보여주기
		session.setAttribute("abc", "로그아웃 성공 !");
		resp.sendRedirect("/webPrj14/index.jsp");
		//클라이언트가 톰캣에 다시 요청을 보내는 것이므로, 클라이언트 입장에서는 제일 겉에 보이는 게 톰캣이니까 webprj14부터 적어준다.
		
	}

}
