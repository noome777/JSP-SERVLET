package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.ServiceRef;

@WebServlet(urlPatterns = "/join")
public class MemberJoin extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//클라이언트가 보낸 데이터 중, ID, PWD, NICK에 해당하는 값 출력해보기
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String nick = req.getParameter("nick");
		System.out.println("사용자로부터 받은 id : " + id + "| pwd : " + pwd+ "| nick : "+ nick);
	}
	
	
}
