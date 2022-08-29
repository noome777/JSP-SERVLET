package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/test02")
public class Test02 extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 담기
		req.setAttribute("userId", "swy");
		
		String[] foodArr = {"치킨", "피자", "햄버거"};
		req.setAttribute("foods", foodArr);
		
		req.getRequestDispatcher("/views/view02.jsp").forward(req, resp);
	}

}
