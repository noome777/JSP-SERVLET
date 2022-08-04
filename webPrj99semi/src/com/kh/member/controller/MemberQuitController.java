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
			
			//우선 로그아웃
			req.getSession().invalidate();
			
			/*
			 * 포워딩 리다이렉트

				url 바뀐다
				클라이언트가 한 번 더 요청이 온다
				다음 화면이 req에 있는 데이터를 필요로 하는가 ? -> 포워딩 필요
				내가 지금 들고 있는 데이터를 req에 담고 있어야 하면 포워딩, 그게 아니라면 redirect

			 */
		
			req.setAttribute("Msg", "회원 탈퇴 성공!");
			req.getRequestDispatcher("/views/common/header.jsp").forward(req, resp);
			
			
			//이거 안됨..........
//			req.getSession().setAttribute("alertMsg", "회원 탈퇴 성공!"); 
//			resp.sendRedirect("/views/common/header.jsp"); 
			//메인으로 가기 -> redirect -> 로그아웃 했으므로 클라이언트에게 다시 요청을 보내라고 하기 위해 -> 이걸로 해봤는데 안됨..
//			resp.sendRedirect("/views/common/header.jsp");
			
			System.out.println("회원 탈퇴 성공");
			
		} else {
			System.out.println("회원 탈퇴 실패");
		}
		
		//현재 DB에서 Y로 바뀌고 회원탈퇴는 성공했는데 DB상의 데이터가 안 없어지는 상태이므로 수정 필요함.
		
	}

}
