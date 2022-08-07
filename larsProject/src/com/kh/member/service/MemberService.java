package com.kh.member.service;

import java.sql.Connection;

import static com.kh.common.JDBCTemplate.*;

import com.kh.common.JDBCTemplate;
import com.kh.member.repository.MemberDao;
import com.kh.member.vo.MemberVo;

public class MemberService {

	public int join(MemberVo vo) {
		//1. 서비스 로직 작성 (자바  || sql)
		
		//아이디 유효성 검사 (4글자 이상)
		if(vo.getId().length() < 4) {
			return -1;
		}
		
		
		//비밀번호 검사 (4글자 이상)
		if(vo.getPwd().length() < 4) {
			return -2;
		}
		
		//pwd == pwd2
		if(vo.getPwd().equals(vo.getPwd2()) == false) {
			return -3;
		}
		
		
		
		
		Connection conn = null;
		int result = 0;
		
		//3. 트랜잭션 처리(commit || rollback)
		try {
			conn = getConnetion();
			//2. dao 메소드 호출
			result = new MemberDao().join(conn, vo);
			
			if(result == 1) {
				commit(conn);
			}else {
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

	

	public MemberVo login(MemberVo vo) {
		
		//1. 서비스 로직 수행 (자바 || sql)-> 딱히 없음
		
		
		
		Connection conn = null;
		MemberVo loginMember = null;
		
		try {
			conn = getConnetion();
			
			//2. dao 호출하기
			loginMember = new MemberDao().login(conn, vo);
			
			
			//3. 트랜잭션 처리하기
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		return loginMember;
	}


}
