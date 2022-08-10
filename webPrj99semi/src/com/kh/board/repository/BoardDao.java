package com.kh.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.kh.common.JDBCTemplate.*;

public class BoardDao {

	/*
	 * 게시글 총 개수
	 */

	public int getCount(Connection conn) {
		
		//conn 준비
		
		//sql 준비
		String sql = "SELECT COUNT(NO) COUNT FROM BOARD WHERE STATUS = 'N' AND TYPE = 1";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		
		try {
			//sql 담을 객체 준비 및 쿼리 채우기
			pstmt = conn.prepareStatement(sql);
			//물음표 안 됐으니 안 채워도됨
			
			//sql 실행 및 결과 저장
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("COUNT"); //실행결과 count 만 나오니까 딱히 객체로 안 바꿔줘도 됨. 그냥 실행결과 -> 자바데이터로만 바꿔줌
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		
		//실행 결과 리턴
		return count;
	}

}
