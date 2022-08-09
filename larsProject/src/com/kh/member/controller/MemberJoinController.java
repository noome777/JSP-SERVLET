package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.service.MemberService;
import com.kh.member.vo.MemberVo;

@WebServlet(urlPatterns = "/member/join")
public class MemberJoinController extends HttpServlet {

	
	//<td><button type="button" onclick="location.href='/lars/member/join'">회원가입</button></td> -> doGet 방식 -> header.jsp
	//<form action="/lars/member/join" method="post"> -> doPost 방식 --> joinForm.jsp
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/member/joinForm.jsp").forward(req, resp);
	}
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 인코딩하기
		req.setCharacterEncoding("UTF-8");
		
		//2. 데이터 꺼내기
		String memberId = req.getParameter("memberId");
		String memberPwd = req.getParameter("memberPwd");
		String memberPwd2 = req.getParameter("memberPwd2");
		String memberName = req.getParameter("memberName");
		String memberPhone = req.getParameter("memberPhone");
		String memberEmail = req.getParameter("memberEmail");
		String memberAddr = req.getParameter("memberAddr");
		String [] interest = req.getParameterValues("interest");
		
		//3. 데이터 뭉치기(객체)
		MemberVo vo = new MemberVo();
		vo.setId(memberId);
		vo.setPwd(memberPwd);
		vo.setPwd2(memberPwd2);
		vo.setName(memberName);
		vo.setPhone(memberPhone);
		vo.setEmail(memberEmail);
		vo.setAddr(memberAddr);
		vo.setInterest(String.join(",", interest));
		
		//null처리 해주기
		String hobbys = "";
		if(interest != null) {
			hobbys = String.join(",", interest);
		}
		
		//4. 서비스 호출
		int result = new MemberService().join(vo);
		
		//5. 실행 결과에 따라 화면  보여주기
		if(result == 1) {
			//성공 후 메인화면 -> 새로고침시 똑같은 데이터를 다시 나한테 보내니까 그걸 방지하기 위한 것 redirect
			req.getSession().setAttribute("alertMsg", "회원가입 성공하셨습니다 !");
			resp.sendRedirect("/lars");
		}else {
			//실패 에러페이지
			System.out.println("ERROR - CODE : " + result + " ] 회원가입 실패");
			resp.sendRedirect("/lars/views/error/errorPage.jsp");
		}
		
		
		
		
	}
}
