package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/member/logout")
public class MemberLogoutController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//a 태그를 통해서 요청이 들어오니까 doGet으로 오버라이드 하면 된다. 요청이 get 방식인 이유는 header.jsp의 내용이 a태그인 브라우저의 url을 바꿔서 요청을 보내주는 태그이기 때문에 
		
		//로그아웃
		req.getSession().invalidate();
		
		//메인으로 가기 -> redirect -> 로그아웃 했으므로 클라이언트에게 다시 요청을 보내라고 하기 위해
		//브라우저의 url에 /member/logout이 안 뜨는 이유 -> 리다이렉트 했기 때문에 다시 요청을 보내야 하므로
//		resp.sendRedirect("/semi");
		resp.sendRedirect(req.getContextPath());
		
	}
}
