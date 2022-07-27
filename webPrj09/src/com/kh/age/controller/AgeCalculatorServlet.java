package com.kh.age.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/calcAge")
public class AgeCalculatorServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 꺼내기
		String age_ = req.getParameter("age");
		
		//복잡한, 어려운, 자바코드로 처리해야하는, 연산하기
		int age = Integer.parseInt(age_);
		int result = age + 10;
		
		//연산 결과 보여주기 (결과 데이터를 넘겨주기 위한 준비(set)단계, result를 미리 담아둔다.
		//resp.getWriter.print("<h1>"+result+"</h1>"); 밑의 코드도 같은 결과가 나옴 -> 
		//그러나 이런식으로 하면 복잡하고, print를 많이 써야해서 밑의 코드를 통해 HTML작성이 편한 jsp로 넘겨주는 거임
		req.setAttribute("result", result);
		
		//내가 만든 연산결과를 넘겨주기. 연산결과를 화면에 잘 보여줄 수 있는애한테.
		req.getRequestDispatcher("views/result.jsp").forward(req, resp);
		
	}
	
}
































