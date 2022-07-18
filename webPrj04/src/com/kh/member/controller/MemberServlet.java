package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//webPrj04 중에서 /member/join 이런 요청이 들어오면 이 서블릿이 담당을 하겠다.
@WebServlet(urlPatterns = "/member/join")
public class MemberServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("MemberServlet > service 호출됨");
	}
}
