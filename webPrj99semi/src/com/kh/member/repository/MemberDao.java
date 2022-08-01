package com.kh.member.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.kh.common.JDBCTemplate;
import com.kh.member.vo.MemberVo;

public class MemberDao {
	
	

	//DAO는 SQL 실행, SQL 실행 결과 리턴
	//DAO에서 CONNECTION을 직접 만드는 게 아니라 SERVICE에서 파라미터로 받아온다.
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
			
		} /*catch (Exception e) {
			//롤백해야함-> 예외가 발생한 것을 DAO에서 커밋하거나 롤백하기 보다는, 서비스에게 알려주려고// 근데 어차피 여기서는 finally 때문에 try catch문 써준 거라서 catch문 생략해도됨
			throw e;
			
		}*/ finally {
			//다 쓴 자원 정리
			JDBCTemplate.close(pstmt);
		}
		
			return result;
		
	}

	public MemberVo login(Connection conn, MemberVo vo) throws Exception {
		
		//connection 준비
		
		MemberVo loginMember = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//SQL 준비
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?  AND STATUS = 'N'";
		
		try {
			//SQL 객체에 담고, 물음표 채우기
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			
			
			//SQL 실행 및 결과 저장
			rs = pstmt.executeQuery();
			
			//rs에서 데이터 꺼내서 객체로 만들기
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
		}
		
		finally {
			//자원반납
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
		}
		
		//만들어진 객체 리턴
		return loginMember;
	}
}
