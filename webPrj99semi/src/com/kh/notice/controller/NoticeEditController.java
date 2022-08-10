package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.service.NoticeService;
import com.kh.notice.vo.NoticeVo;

@WebServlet(urlPatterns = "/notice/edit")
public class NoticeEditController extends HttpServlet {

	/*
	 * 공지사항 수정 화면 보여주기
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String num = req.getParameter("num");
		
		NoticeVo vo = new NoticeService().selectOne(num);
		
		if(vo != null) {
			//성공 -> 수정한 화면 보여주기 -> 기존에 글이 보여야 하므로 기존의 데이터를 req에 담아줄 필요가 있다 따라서 포워딩해줌
			req.setAttribute("vo", vo);
			req.getRequestDispatcher("/views/notice/noticeEdit.jsp").forward(req, resp);
			
		} else {
			//실패
			req.setAttribute("errorMsg", "공지사항 수정 실패 ..");
			req.getRequestDispatcher("/views/error/errorPage.jsp").forward(req, resp);
		}
		
	}
	
	/*
	 * 공지사항 수정하기
	 */
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			req.setCharacterEncoding("UTF-8");
			
			//데이터 꺼내기
			String num = req.getParameter("num");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			//데이터 뭉치기
			NoticeVo vo = new NoticeVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setNo(num);
			
			//서비스 호출
			int result = new NoticeService().edit(vo); //update 쿼리
			
			//결과에 따라 화면 선택
			if(result == 1) {
				//성공 -> 상세보기 페이지 (데이터가 필요하므로 포워딩 또는 세션 사용)-> 그러나 쿼리스트링으로 편하게 받기 위해 리다이렉트 사용
				resp.sendRedirect(req.getContextPath()+ "/notice/detail?num="+num);
			}else {
				//실패 -> 에러페이지
				req.setAttribute("errorMsg", "공지사항 수정 실패 ..");
				req.getRequestDispatcher("/views/error/errorPage.jsp").forward(req, resp);
			}
			
			
			
	}
}
