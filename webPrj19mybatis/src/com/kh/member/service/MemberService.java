package com.kh.member.service;

import org.apache.ibatis.session.SqlSession;

import static com.kh.common.SqlTemplate.*;

import com.kh.member.dao.MemberDao;
import com.kh.member.vo.MemberVo;

public class MemberService {
	
	private MemberDao dao = new MemberDao();

	/**
	 * 회원가입
	 */
	public int insert(MemberVo vo) {
		 
		//connection 준비
		SqlSession ss = getSqlSession();
		
		//dao 호출
		int result = dao.insert(ss, vo);
		
		//트랜잭션 처리
		if(result == 1) {
			ss.commit();
		}
		
		//결과 리턴
		return result;
	}

	public MemberVo login(MemberVo vo) {
		
		SqlSession ss = getSqlSession();
		
		MemberVo loginMember = dao.login(ss,vo);
		
		return loginMember;
	}


}
