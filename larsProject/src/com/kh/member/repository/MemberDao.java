package com.kh.member.repository;

import static com.kh.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.kh.member.vo.MemberVo;

public class MemberDao {

	public int join(Connection conn, MemberVo vo) throws Exception {
		
		//conn 얻어오기
		
		//sql 준비
		String sql = "INSERT INTO MEMBER (NO, ID, PWD, NAME, PHONE, EMAIL, ADDR, INTEREST) VALUES (SEQ_MEMBER_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		
		
		//sql 담을 객체 준비
		PreparedStatement pstmt = null;
		int result = 0;
		
		
		
		try {
			//sql 실행 및 쿼리 채우기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getAddr());
			pstmt.setString(7, vo.getInterest());
			
			//sql 결과 저장
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		
		//결과 리턴
		return result;
	
	}

	public MemberVo login(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ? AND STATUS = 'N'";
		
		PreparedStatement pstmt = null;
		MemberVo loginMember = null;
		ResultSet rs = null;
		
		try {
			//sql 담을 객체 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			
			//sql 실행 결과 저장
			rs = pstmt.executeQuery();//////////
			
			
			//rs 객체로 만들기
			if(rs.next()) {
				int no = rs.getInt("NO");
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String phone = rs.getString("PHONE");
				String email = rs.getString("EMAIL");
				String addr = rs.getString("ADDR");
				String interest = rs.getString("INTEREST");
				Timestamp enrollDate = rs.getTimestamp("ENROLL_DATE");
				Timestamp modifyDate = rs.getTimestamp("MODIFY_DATE");
				
				//자바 변수에 데이터 담기
				loginMember = new MemberVo();
				loginMember.setNo(no);
				loginMember.setId(id);
				loginMember.setName(name);
				loginMember.setPhone(phone);
				loginMember.setEmail(email);
				loginMember.setAddr(addr);
				loginMember.setInterest(interest);
				loginMember.setEnrollDate(enrollDate);
				loginMember.setModifyDate(modifyDate);
			}
			
			}  finally {
				close(rs);
				close(pstmt);
			}
		return loginMember;
		
	}

	

	
}
