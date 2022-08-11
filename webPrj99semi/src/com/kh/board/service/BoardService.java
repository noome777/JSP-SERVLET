package com.kh.board.service;

import java.sql.Connection;
import java.util.List;

import com.kh.board.repository.BoardDao;
import com.kh.board.vo.BoardVo;
import com.kh.common.PageVo;

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
			// dao호출
			result = dao.getCount(conn); // select 쿼리 날린 거 , 숫자 데이터 받음 -> commit / rollback 필요 없음

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return result;
	}

	/*
	 * 게시글 리스트 조회 (현재 페이지에 보여질)
	 */

	public List<BoardVo> selectList(PageVo pageVo) {

		// 데이터 검사

		Connection conn = null;
		List<BoardVo> voList = null;

		conn = getConnetion();
		// dao 호출
		voList = dao.selectList(conn, pageVo);

		close(conn);

		return voList;
	}

}
