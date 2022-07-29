package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/member/join")
public class memberJoinController extends HttpServlet {
	
	
	//화면 보여주는건(로그인화면, 회원가입화면) get 방식 요청이니까 doGet으로 , 
	//데이터를 받아서 데이터를 처리하는 경우(아이디, 비번 요청 등)에는 post 방식으로 처리할 테니까 doPost로 처리하는 게 best !!
	
	
	/*
	 * 회원가입 화면 보여주기
	 * 회원가입 버튼을 누르면 http://127.0.0.1:8888/semi/member/join 로 요청을 보내지만 실제로는 서블릿이 jsp에 보낸 요청을 가지고 /views/member/joinForm.jsp 여기에 데이터 넘겨줌
	//버튼태그 눌렀을 때 location.href로 url자체를 바꿔서 요청 보내는 거라서 doGet으로 해야된다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//회원가입 버튼을 누르면 http://127.0.0.1:8888  /semi/member/join 로 요청을 보내지만 실제로는 클라이언트의 요청을 가지고 서블릿 내부에서 url을 처리하여 jsp에 /views/member/joinForm.jsp 로 데이터 넘겨줌
		//즉, 사용자가 직접 jsp 파일 경로를 요청해서 보여줄 수 없게 하고 (내 폴더 노출되니까, 보안상 문제) 서블릿 주소를 통해서 보여주게 하려고 함  
		// <button onclick="location.href='/semi/member/join'">회원가입</button> 클라이언트에서   -->  (urlPatterns = "/member/join") 서블릿으로 --> but, 실제 경로는 밑의 포워딩한 경로임!!!
		
		//다른 애한테 떠넘기기
		req.getRequestDispatcher("/views/member/joinForm.jsp").forward(req, resp);
		
		
	}
	
	//회원가입 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String memberId = req.getParameter("memberId");
		String memberPwd = req.getParameter("memberPwd");
		String memberName = req.getParameter("memberName");
		String memberPhone = req.getParameter("memberPhone");
		String memberEmail = req.getParameter("memberEmail");
		String memberAddr = req.getParameter("memberAddr");
		String [] interest = req.getParameterValues("interest");
		
		System.out.println(memberId);
		System.out.println(memberPwd);
		System.out.println(memberName);
		System.out.println(memberPhone);
		System.out.println(memberEmail);
		System.out.println(memberAddr);
		System.out.println(String.join("/", interest));
	}

}
