package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/notice/list")
public class NoticeList extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//화면 보여주기 (연막)-> 그러나 사실은 JSP 파일로 포워딩해주는
		req.getRequestDispatcher("/views/notice/noticeList.jsp").forward(req, resp);
	}

}
