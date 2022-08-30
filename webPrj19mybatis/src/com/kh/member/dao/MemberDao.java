package com.kh.member.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.member.vo.MemberVo;

public class MemberDao {
	
	/**
	 * 회원가입
	 */
	public int insert(SqlSession ss, MemberVo vo) {
		
		//sql 준비
		//sql을 객체에 담기
		//sql 완성
		//sql 실행 및 결과 저장
		//결과 리턴
		
//		int result = ss.insert("", vo);
//		return result;
		
		return ss.insert("memberMapper.join", vo);
		
	}

	public MemberVo login(SqlSession ss, MemberVo vo) {
		return ss.selectOne("memberMapper.login", vo);
	}

}
