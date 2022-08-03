package com.kh.member.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.kh.common.JDBCTemplate.*;
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
			close(pstmt);
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
				
				//자바의 변수에 위의 데이터들을 담기
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
				close(pstmt);
				close(rs);
			}
			
			//만들어진 객체 리턴
			return loginMember;
			
	}
	
	/*회원 정보 수정*/
	public int edit(Connection conn, MemberVo vo) throws Exception {
		
		//SQL 준비
		String sql = "UPDATE MEMBER SET NAME = ? , EMAIL = ? , PHONE = ? , ADDR = ? , INTEREST = ? ,MODIFY_DATE = SYSDATE WHERE NO = ?";
		
		//SQL 담을 객체 준비
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			
		//SQL 객체에 담고 완성하기
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getEmail());
		pstmt.setString(3, vo.getPhone());
		pstmt.setString(4, vo.getAddr());
		pstmt.setString(5, vo.getInterest());
		pstmt.setInt(6, vo.getNo());
			
		//SQL 실행 및 결과저장
		result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		//SQL 실행결과 리턴
		return result;
	}

	public MemberVo selectOneByNo(Connection conn, int num) {
		//connection 준비
		
		//sql 준비
		String sql = "SELECT * FROM MEMBER WHERE NO = ? AND STATUS = 'N'";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		
		try {
			//sql 담을 객체 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			//sql 실행 및 결과 저장 -> rs를 자바 객체로 만드는 작업
			rs = pstmt.executeQuery();
			
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
				
				//자바의 변수에 위의 데이터들을 담기
				vo = new MemberVo();
				vo.setNo(no);
				vo.setId(id);
				vo.setName(name);
				vo.setPhone(phone);
				vo.setEmail(email);
				vo.setAddr(addr);
				vo.setInterest(interest);
				vo.setEnrollDate(enrollDate);
				vo.setModifyDate(modifyDate);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
			
		}
		
		//sql 객체에 담기 및 쿼리 완성
		
		
		//sql 실행결과 리턴
		return vo;
	}

	public int quit(Connection conn, MemberVo vo) throws Exception {
		
		//sql 준비
		String sql = "UPDATE MEMBER SET STATUS = 'Y' , MODIFY_DATE = SYSDATE WHERE NO = ?";
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			//SQL 담을 객체 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());
			
			//sql 실행 및 결과 저장
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
		
	}

	public int changePwd(String memberId, String memberPwd, String memberPwdNew, Connection conn) throws Exception {
		//connection 준비
		
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		//sql 준비
		String sql = "UPDATE MEMBER SET PWD = ? WHERE ID = ? AND PWD = ?";
		
		try {
			//sqp 담을 객체 준비 및 sql 완성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberPwdNew);
			pstmt.setString(2, memberId);
			pstmt.setString(3, memberPwd);
			
			//sql 실행 및 결과 저장
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		//실행 결과 리턴
		return result;
	}

	
	
	
	
//	 * DAO
//	 * - SQL 실행 (전달받은 객체 활용)
//	 * 		-SELECT 쿼리 -> rs를 객체로 변환
//	 * 		- DML 쿼리 -> 추가작업 X, int로 반환
//	 * - SQL 실행 결과 리턴
}
