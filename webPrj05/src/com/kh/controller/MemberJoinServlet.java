package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/member/join")
public class MemberJoinServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//전달받은 데이터들 모두 꺼내서 출력하기 (memberName, gender, age, foods)
		
		req.setCharacterEncoding("UTF-8");
		
		String memberName = req.getParameter("memberName");
		String gender = req.getParameter("gender");
		String age = req.getParameter("age");
		String[] foods = req.getParameterValues("foods");
		
		System.out.println(memberName);
		System.out.println(gender);
		System.out.println(age);
		
		if(foods != null) {
			for(String x : foods) {
				System.out.println(x);
			}
		}
		
		
	}
}
