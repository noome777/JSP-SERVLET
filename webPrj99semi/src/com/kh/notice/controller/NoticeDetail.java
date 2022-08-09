package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.service.NoticeService;
import com.kh.notice.vo.NoticeVo;

@WebServlet(urlPatterns = "/notice/detail")
public class NoticeDetail extends HttpServlet{
	
	//공지사항 상세조회 화면 보여주기
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//num번 조회수 증가 먼저 해주고 밑의 작업들을 해주기 
		//noticeList.jsp 의 script에서 요청 보냄
		
		/*
		 * 공지사항 조회수 증가
		 */
		String num = req.getParameter("num");

		int result = new NoticeService().increaseNotice(num);
		
		//result에 따라서 다음 밑의 둘 중의 하나를 해주면 됨
	
		if(result == 1) {
			//result == 1이면 update가 잘 된 것임 -> 디비에 가서 특정 공지사항 정보 조회
			NoticeVo vo = new NoticeService().selectOne(num);
			
			//조회한 해당 정보 vo를 req에 담아서, 다음 타자에게 포워딩
			//근데 vo가 null이면 안 되니까 null처리
			if(vo != null){
				//vo가 null이 아니면 성공
				req.setAttribute("vo", vo);
				req.getRequestDispatcher("/views/notice/noticeDetail.jsp").forward(req, resp);
			}else {
				//vo가 null이면 조회 실패
				req.setAttribute("errorMsg", "공지사항 상세 조회 실패..");
				req.getRequestDispatcher("/views/error/errorPage.jsp").forward(req, resp);
			}
				
	
		}else {
			//조회 실패
			//에러페이지에서 request에서 데이터를 get 하므로 포워딩이 적합함
			req.setAttribute("errorMsg", "공지사항 상세 조회 실패..");
			req.getRequestDispatcher("/views/error/errorPage.jsp").forward(req, resp);
		}
		
	}
	
	

}
