package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.service.BoardService;

@WebServlet(urlPatterns = "/board/list")
public class BoardListController extends HttpServlet {
	
	/*
	 *게시글 목록 화면 보여주기 
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// --------------------------- 페이징 처리 ---------------------------
		int listCount;					// 현재 총 게시글 개수
		int currentPage;				// 현재 페이지 (== 사용자가 요청한 페이지)
		int pageLimit;					// 페이지 하단에 보여질 페이지 버튼의 최대 개수 (1 ~ 10)
		int boardLimit;					// 한 페이지 내 보여질 게시글 최대 개수
		// 위의 4개를 이용해서 아래 3개의 값 구하기
		int maxPage;					// 가장 마지막 페이지 (== 총 페이지 수)
		int startPage;					// 페이징바의 시작
		int endPage;					// 페이징바의 끝
		
		// listCount 값 구하기   
		listCount = new BoardService().getCount(); //DB에 가서,, BOARD 테이블의 총 게시글 개수
//		System.out.println("개수 : " + listCount);
		
		
		//currentPage 값 구하기
		currentPage = Integer.parseInt(req.getParameter("p"));
		
		//pageLimit
		pageLimit = 10;
		
		//boardLimit
		boardLimit = 10;
		
		/*
		 * maxPage : 제일 마지막 페이지 (총 페이지 수)
		 * 
		 * listCount(현재 총 게시글 개수), boardLimit(한 페이지 내 보여질 게시글 최대 개수)
		 * 
		 * ex) 게시글이 10개씩 보여진다는 가정 하에,
		 * 
		 *  listCount		|		boardLimit 				maxPage
		 *  	100			/			10 			=>	10.00	  10
		 *  
		 * 		101			/			10 			=>	10.xx	  11
		 * 		102			/			10 			=>	10.xx	  11
		 * 		103			/			10 			=>	10.xx	  11
		 * 		104			/			10 			=>	10.xx	  11
		 * 		105			/			10 			=>	10.xx	  11
		 * 		110			/			10 			=>	11.00	  11
		 * 
		 * 		111			/			10 			=>	11.xx	  12
		 *
		 *		소수점 첫째자리에서 올림처리 하면 됨
		 */
		
		maxPage = (int) Math.ceil(((double) listCount / boardLimit)) ;
		
		/*
		 *startPage : 페이징바의 시작
		 *
		 * pageLimit(페이지 하단에 보여질 페이지 버튼의 최대 개수), currentPage(현재 페이지)
		 * 위 두개의 값을 이용하여 구할 수 있음
		 * 
		 * ex) 페이징바의 목록이 10개 단위라는 가정 하에,
		 * 		startPage : 1, 11, 21, 31, 41, 51...
		 * 				  : 10n + 1                   
		 * 				  :	pageLimit * n + 1
		 * 
		 * 
		 * 		currentPage		startPage		N * pageLimit + 1
		 * 
		 * 			1				1 		=>	0 * pageLimit + 1      
		 * 			5				1		=>	0 * pageLimit + 1       N = 0
		 * 			10 				1		=>  0 * pageLimit + 1
		 * 
		 * 			11				11		=>  1 * pageLimit + 1
		 * 			15				11		=>  1 * pageLimit + 1 		N = 1
		 * 			20				11		=>  1 * pageLimit + 1
		 * 
		 * 			1~10 => n = 0
		 * 			11~20 => n = 1
		 * 			21~30 => n = 2
		 * 
		 * 		currentPage-1	/	pageLimit	=> n
		 * 			0~9			/		10		=> 0
		 * 			10~19		/		10		=> 1
		 * 			20~20		/		10		=> 2
		 * 
		 * 
		 * 		startPage = 		    n 				* 		pageLimit + 1
		 * 				  =	currentPage-1  /  pageLimit	*		pageLimit + 1
		 */
		startPage = (currentPage-1) / pageLimit * pageLimit + 1;
		
		/*
		 * endPage : 페이징바의 끝
		 * 
		 *  startPage , pageLimit, (+maxPage)
		 *  위 3개 값의 영향을 받음
		 *  
		 *  ex) pageLimit 이 10이라는 가정 하에,
		 *  
		 *  startPage : 1 => 10
		 *  startPage : 11 => 20
		 *  startPage : 21 => 30
		 */
		endPage = startPage + pageLimit - 1;
		
		//startPage 가 11이면 endPage 는 20 // 근데, maxPage가 13이면?
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		//이렇게 얻어낸 데이터들을,, 화면에 전달해서,, 페이징 처리
		//변수 많으니까,, 객체에 담아서 보내기,,
		
		//화면 보여주기
		req.getRequestDispatcher("/views/board/boardList.jsp").forward(req, resp);
		
	}

}
