package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.service.NoticeService;
import com.kh.notice.vo.NoticeVo;

@WebServlet(urlPatterns = "/notice/list")
public class NoticeList extends HttpServlet {
	
	//공지사항 목록 보여주기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//1. 데이터 꺼내기 (생략)-> 특별히 클라이언트가 요청할 게 없음
		
		//2. 데이터 뭉치기 (생략)-> 특별히 클라이언트가 요청할 게 없음
		
		//3. 서비스 호출
		//서비스를 호출 했을 때 반환되는 값을 여러개의 배열로 담음
		ArrayList<NoticeVo> voList =  new NoticeService().selectList();
		
		//4. 결과에 따라 화면 만들기
		
		//db에서 조회수 같은 거 받아서 반영을 해줘야하니까 db에서 조회한 데이터가 voList에 담겨진다.
		req.setAttribute("voList", voList);
		//화면 보여주기 (연막)-> 클라이언트는 /notice/list로 가지만, 그러나 사실은 밑의 주소를 가진 JSP 파일로 포워딩해주는 -> noticeList.jsp의 스크립틀릿으로 이동
		req.getRequestDispatcher("/views/notice/noticeList.jsp").forward(req, resp);
		
	}

}
