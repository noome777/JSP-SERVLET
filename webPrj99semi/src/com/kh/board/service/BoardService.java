package com.kh.board.service;

import java.sql.Connection;

import com.kh.board.repository.BoardDao;
import static com.kh.common.JDBCTemplate.*;

public class BoardService {
	
	private final BoardDao dao = new BoardDao();

	/*
	 * 게시글 총 개수
	 */
	
	public int getCount() {
		
		Connection conn = null;
		int result = 0;
		
		conn = getConnetion();
		
		try {
			//dao호출
			result = dao.getCount(conn); //select 쿼리 날린 거 , 숫자 데이터 받음 -> commit / rollback 필요 없음
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		return result;
	}

}
