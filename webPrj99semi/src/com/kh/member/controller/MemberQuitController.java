package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.servicce.MemberService;
import com.kh.member.vo.MemberVo;

@WebServlet(urlPatterns = "/member/quit")
public class MemberQuitController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 데이터 받기 -> 객체
		int no = ((MemberVo) req.getSession().getAttribute("loginMember")).getNo();
		
		MemberVo vo = new MemberVo();
		vo.setNo(no);
		
		//2. 서비스 호출 -> 딱히 없음
		int result =  new MemberService().quit(vo);
		
		//3. 실행 결과에 따라 화면 선택
		if(result == 1) {
//-----------------> 질문
			req.setAttribute("alertMsg", "회원 탈퇴 성공!");
			
			//우선 로그아웃
			req.getSession().invalidate();
			
			//메인으로 가기 -> redirect -> 로그아웃 했으므로 클라이언트에게 다시 요청을 보내라고 하기 위해
			resp.sendRedirect("/semi");
			
			System.out.println("회원 탈퇴 성공");
			
		} else {
			System.out.println("회원 탈퇴 실패");
		}
		
		//현재 DB에서 Y로 바뀌고 회원탈퇴는 성공했는데 DB상의 데이터가 안 없어지는 상태이므로 수정 필요함.
		
	}

}
