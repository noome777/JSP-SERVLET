package com.kh.notice.service;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.notice.repository.NoticeDao;
import com.kh.notice.vo.NoticeVo;

public class NoticeService {

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

}
