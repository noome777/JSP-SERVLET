package com.kh.notice.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;

import com.kh.common.JDBCTemplate;
import com.kh.notice.repository.NoticeDao;
import com.kh.notice.vo.NoticeVo;

public class NoticeService {
	
	private final NoticeDao dao = new NoticeDao();

	public ArrayList<NoticeVo> selectList() {
		
		Connection conn = null;
		ArrayList<NoticeVo> voList = null;
		
		//1. 비지니스 로직 작성 (자바 || sql) -> 딱히 없음 (jdbc 커넥션 얻어올 때 static .* 추가하기)
		
		try {
			conn = getConnetion();
			//2. dao 호출 
			voList = new NoticeDao().selectList(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(conn);
		}
		
		
		//3. 실행 결과 리턴
		return voList;
	}

	public int insertNotice(NoticeVo vo) {
		//1. 비지니스 로직 작성(자바 || sql) -> 딱히 없음
		Connection conn = null;
		int result = 0;
		
		try {
			//2. dao 호출
			conn = getConnetion();
			result = new NoticeDao().insertNotice(conn, vo);
			
			if(result == 1) {
				//3. 트랜잭션 처리
				commit(conn);
			}else {
				rollback(conn);
			}
			
		} catch (Exception e) {
			rollback(conn);
			
		} finally {
			close(conn);
		}
		
		//4. 결과 리턴
		return result;
		
	}
	
	/*
	 * 공지사항 조회수 증가
	 */

	public int increaseNotice(String num) {
		Connection conn = null;
		int result = 0;
		
		//1. 서비스 메소드  (자바 || sql)
		
		try {
			conn = getConnetion();
			
			//2. dao 호출
			result = new NoticeDao().increaseNotice(conn, num);
			
			if(result == 1 ) {
				commit(conn);
			}else {
				rollback(conn);
			}
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			//3. 트랜잭션 처리
			close(conn);
		}
		
		return result;
	}

	/*
	 * 공지사항 조회
	 */
	
	public NoticeVo selectOne(String num) {
		//1. 서비스 로직 , 데이터 검사(자바 || sql)
		
		
		Connection conn = null;
		NoticeVo vo = null;
		
		try {
			conn = getConnetion();
			
			//2. dao 호출
			vo =  new NoticeDao().selectOne(conn, num);
			
			//3. select 문이므로 트랜잭션 처리(commit, rollback)은 불필요
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		
		//4. 결과 리턴
		return vo;
	}

	
	/*
	 * 공지사항 삭제
	 */
	
	public int delete(String num) {
		
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnetion();
			
			//dao 호출
			result = new NoticeDao().delete(conn, num);
			
			if(result == 1) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
			
		} finally {
			close(conn);
		}
		
		return result;
	}

	/*
	 * 공지사항 수정하기
	 */
	
	public int edit(NoticeVo vo) {
		
		//데이터 검사
		if(vo.getTitle().length() < 1) {
			
			return -1;
		}
		
		if(vo.getContent().length() < 1) {
			
			return -2;
		}
		
		
		Connection conn = null;
		int result = 0;
		
		conn = getConnetion();
		
		try {
			
			//dao 호출
			result = dao.edit(conn, vo);
			
			if(result == 1) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}
		
		return result;
	}

}
