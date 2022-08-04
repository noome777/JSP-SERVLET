package com.kh.notice.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import static com.kh.common.JDBCTemplate.*;
import com.kh.notice.vo.NoticeVo;

public class NoticeDao {

	public ArrayList<NoticeVo> selectList(Connection conn) {
		
		//1.connection 준비 -> 서비스에서 얻어옴
		
		//2.sql 준비
		String sql  = "SELECT N.NO, N.TITLE, N.CONTENT, N.CNT, TO_CHAR(N.ENROLL_DATE, 'YY/MM/DD HH:MI') AS ENROLL_DATE, N.STATUS, M.NAME AS WRITER FROM NOTICE N JOIN MEMBER M ON N.WRITER = M.NO WHERE N.STATUS = 'N' ORDER BY NO DESC";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<NoticeVo> list = new ArrayList<NoticeVo>();
		
		try {
			//3.sql 담을 객체 준비 
			pstmt = conn.prepareStatement(sql);
			
			//4.sql 실행 
			rs = pstmt.executeQuery();
			
			//5.결과 저장 -> select 이므로 resultSet -> 자바 객체 (noticeVo)로 반환하기
			///NO, TITLE, CONTENT, WRITER, CNT, ENROLL_DATE, STATUS
			while(rs.next()) {
				String no = rs.getString("NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String writer = rs.getString("WRITER");
				String cnt = rs.getString("CNT");
				String enrollDate = rs.getString("ENROLL_DATE");
				String status = rs.getString("STATUS");
				
				NoticeVo vo = new NoticeVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setWriter(writer);
				vo.setCnt(cnt);
				vo.setEnrollDate(enrollDate);
				vo.setStatus(status);
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			close(rs);
			close(pstmt);
		}
		//5. 결과 리턴
		return list;
	}

	public int insertNotice(Connection conn, NoticeVo vo) {
		//conn 준비
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		//sql 준비
		String sql = "INSERT INTO NOTICE (NO ,TITLE ,CONTENT ,WRITER) VALUES (SEQ_NOTICE_NO.NEXTVAL , ? , ? , ?)";
		
		try {
			//sql 객체에 담기 및 쿼리 채우기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			
			//sql 실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		//sql 실행결과 리턴
		return result;
	}



}
