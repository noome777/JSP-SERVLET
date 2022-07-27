package com.kh.pizza.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/order")
public class PizzaController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost 호출됨 ~~~");
		
		//인코딩
		req.setCharacterEncoding("UTF-8");
		
		//데이터 꺼내기 -> 클라이언트에서 서버 쪽으로 데이터 요청하기(request), 요청한 데이터를 변수에 담기
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String addr = req.getParameter("addr");
		String memo = req.getParameter("memo");
		String base = req.getParameter("base");
		String topping = req.getParameter("topping");
		String[] sideArr = req.getParameterValues("side");
		String pay = req.getParameter("pay");
		
		//출력문 -> 데이터 출력하기
		System.out.println(name);
		System.out.println(phone);
		System.out.println(addr);
		System.out.println(memo);
		System.out.println(base);
		System.out.println(topping);
		System.out.println(String.join(",", sideArr));
		System.out.println(pay);
		
		//데이터 담기 -> 요청한 데이터를 setAttribute로 담기
		req.setAttribute("name", name);
		req.setAttribute("phone", phone);
		req.setAttribute("addr", addr);
		req.setAttribute("memo", memo);
		req.setAttribute("base", base);
		req.setAttribute("topping", topping);
		req.setAttribute("sideArr", sideArr);
		req.setAttribute("pay", pay);
		
		//담은 데이터를 가지고 다음 목적지 정하고, 이동시키기 (servlet 간에(서블릿-서블릿, 서블릿-jsp) request 데이터를 담아 넘겨주는 작업)-> 이 request는 order.jsp로 넘겨줄게 ! 너가 다음으로 갈 곳은 여기야.
		RequestDispatcher rd = req.getRequestDispatcher("views/order.jsp");
		
		//rd가 req와 resp 객체를 들고 이동을 한다. 다음 목적지를 정하고 이동시키기
		rd.forward(req, resp);
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 호출됨 ~~~");
	}

}//class





























