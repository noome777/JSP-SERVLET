package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/notice/detail")
public class NoticeDetail extends HttpServlet{
	
	//공지사항 상세조회 화면 보여주기
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//포워딩 사용 하는 이유는 데이터를 담고 있어서도 있지만, 그냥 디렉토리 구조(실제의 경로) 숨기기 위함
		req.getRequestDispatcher("/views/notice/noticeDetail.jsp").forward(req, resp);
	}

}
