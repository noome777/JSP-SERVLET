package com.kh.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.board.vo.BoardVo;
import com.kh.common.PageVo;

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

	
	/*
	 * 게시글 목록 조회
	 * (현재 페이지에 해당하는)
	 */
	public List<BoardVo> selectList(Connection conn, PageVo pageVo) {
		
		//conn 준비
		//sql 준비
		String sql = "SELECT * FROM ( SELECT ROWNUM AS RNUM, T.* FROM ( SELECT B.NO , B.TITLE , B.CONTENT , B.CNT , B.ENROLL_DATE , M.ID AS WRITER , C.CATEGORY_NAME FROM BOARD B JOIN MEMBER M ON B.WRITER = M.NO JOIN CATEGORY C USING(CATEGORY_NO) WHERE B.TYPE = 1 AND B.STATUS = 'N' ORDER BY B.NO DESC ) T ) WHERE RNUM BETWEEN ? AND ?";
		
		PreparedStatement pstmt = null;
		List<BoardVo> list = new ArrayList<BoardVo>();
		ResultSet rs = null;
		
		try {
			//sql 담을 객체 준비 및 쿼리 실행
			pstmt = conn.prepareStatement(sql);
			
			int start = (pageVo.getCurrentPage()-1) * pageVo.getBoardLimit() + 1;
			int end = start + pageVo.getBoardLimit() - 1;
			
			pstmt.setInt(1, start); //처음 게시글 번호
			pstmt.setInt(2, end); //마지막 게시글 번호
			
			//sql 실행 및 결과 저장 (rs -> java) while -> 1개가 아니라 여러개의 rs 가져옴
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String no = rs.getString("NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String cnt = rs.getString("CNT");
				String enrollDate = rs.getString("ENROLL_DATE");
				String writer = rs.getString("WRITER");
				String categoryName = rs.getString("CATEGORY_NAME");
				
				BoardVo vo = new BoardVo();
//				vo.setNo(rs.getString("NO"));
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setCnt(cnt);
				vo.setEnrollDate(enrollDate);
				vo.setWriter(writer);
				vo.setCategory(categoryName);
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		
		//결과 리턴
		return list;
	}

}
