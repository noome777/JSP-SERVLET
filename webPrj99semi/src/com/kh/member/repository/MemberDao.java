package com.kh.member.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kh.common.JDBCTemplate;
import com.kh.member.vo.MemberVo;

public class MemberDao {
	
	

	
	
	public int join(MemberVo vo, Connection conn) throws Exception {
		//회원가입
		
		//insert

		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			
			//sql 준비
			String sql = "INSERT INTO MEMBER(NO, ID, PWD, NAME, PHONE, EMAIL, ADDR, INTEREST) VALUES (SEQ_MEMBER_NO.NEXTVAL, ? , ? , ? , ? , ? , ? , ?)";
			
			//sql 객체에 담기
			pstmt = conn.prepareStatement(sql);
			
			//객체에 담고 , 미완성 sql 완성하기
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getAddr());
			pstmt.setString(7, vo.getInterest());
			
			//sql 실행 및 실행결과 받기
			result = pstmt.executeUpdate();
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (Exception e) {
			//롤백해야함
			throw e;
		} finally {
			//다 쓴 자원 정리
			JDBCTemplate.close(pstmt);
			}
		
			return result;
		
	}

}
