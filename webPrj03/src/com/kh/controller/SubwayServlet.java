package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/subway")
public class SubwayServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String birth = req.getParameter("birth");
		
		//연산
		String year_ = birth.substring(0,4);
		int year = Integer.parseInt(year_);
		
		//현재날짜에서 연도 추출 - 출생년도 + 1 = 나이
		int todayYear = LocalDate.now().getYear();
		
		int age =  todayYear - year + 1;
		
		if(age >= 65) {
			System.out.println("무료");
		} else if(age >= 20 && age < 65) {
			System.out.println("1250원");
		} else if(age >=14 && age < 20) {
			System.out.println("720원");
		} else if(age >= 8 && age < 14) {
			System.out.println("360원");
		} else {
			System.out.println("무료");
		}
			
		//출력
		resp.setContentType("text/plain; charset-UTF-8");
		PrintWriter pw = resp.getWriter();
		
		pw.println();
	}
	
	
}
