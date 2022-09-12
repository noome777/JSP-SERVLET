package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.service.MemberService;
import com.kh.member.vo.MemberVo;

@WebServlet(urlPatterns = "/member/select")
public class MemberLoginController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 꺼내기
		String userId = req.getParameter("userId");
		String userPwd = req.getParameter("userPwd");
		
		//vo
		MemberVo vo = new MemberVo();
		vo.setId(userId);
		vo.setPwd(userPwd);
//		vo.setPwd(userPwd);
		
		//service 호출
		MemberVo loginMember = new MemberService().login(vo);
		
		//화면선택
		if(loginMember != null) {
			System.out.println("로그인 성공");
			System.out.println(loginMember);
		} else {
			System.out.println("로그인 실패");
		}

		
	}
}
