package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/score")
public class ScoreServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int kor = Integer.parseInt(req.getParameter("kor"));
		int math = Integer.parseInt(req.getParameter("math"));
		int eng = Integer.parseInt(req.getParameter("eng"));
		
		int result = (kor + math + eng) / 3;
		
		System.out.println(kor);
		System.out.println(math);
		System.out.println(eng);
		System.out.println(result);
		
		//화면에 result 보여주기(클라이언트와 자바 사이에 stream같은 것을 만들어 줌)
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = resp.getWriter();
	
		//리스폰스에서 가져온 겟라이터로 콘솔이 아닌 화면에 출력을 해준다.
		pw.println("<h1> 세 과목의 평균 : " + result + "</h1>");
		
		
		
		
	}
}
