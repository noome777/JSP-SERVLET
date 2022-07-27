package com.kh.plus.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/plus")
public class PlusController extends HttpServlet {
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			
			//데이터 꺼내기
			 
			int x = Integer.parseInt(req.getParameter("a"));
			int y = Integer.parseInt(req.getParameter("b"));
			int result = x + y;
			
			//연산결과 보여주기
			PrintWriter out = resp.getWriter();
			out.print("<h1>"+result+"</h1>");
			
			
		}
}
