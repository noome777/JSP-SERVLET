package com.kh.nick.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello")
public class nickController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 꺼내기
		String nick = req.getParameter("nick");
		
		//데이터 준비하기
		req.setAttribute("nick", nick);
		
		//데이터 전달하기
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		
	}
}
