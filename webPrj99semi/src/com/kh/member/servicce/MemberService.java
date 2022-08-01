package com.kh.member.servicce;

import java.sql.Connection;

import com.kh.common.JDBCTemplate;
import com.kh.member.repository.MemberDao;
import com.kh.member.vo.MemberVo;

public class MemberService {

	//서비스로직 -> 결제하기 , 조회하기, 주문하기 등등
	//회원가입 과정 유효성 검사 -> 비지니스 로직 작성 (그 중, DB 갈 일 있으면? DAO 한테 부탁함, 비지니스 로직 처리결과 리턴)
	
	public int join(MemberVo vo) {
		
		
		//id 유효성 검사 (4글자 이상인지)
		if(vo.getId().length() < 4) {
			//회원가입 불가 다음단계 진행 ㄴㄴ
			//return 값이 1이면 성공, 1이 아니면 실패임 -> 그 이유는 memberJoinController가 result가 1일때만 성공으로 취급하기 때문에
			return -1;
		}
		
		//pwd 검사 (4글자 이상인지)
		if(vo.getPwd().length() < 4) {
			//회원가입 불가 다음단계 진행 ㄴㄴ
			return -2;
		}
		
		//pwd == pwd2 검사
		if(vo.getPwd().equals(vo.getPwd2()) == false) {
			//회원가입 불가 다음단계 진행 ㄴㄴ
			return -3;
		}
		
		//아이디 중복 검사
//		MemberVo selectVo = new MemberVo().checkDup(vo);
//		if(selectVo == null) {
//			//중복 ㄴㄴ. 회원가입 ㅇㅋ
//		}
		
		//VO를 DB에 insert
		//DB에 있는 모든 작업이 끝나고 커밋, 롤백 해줘야 하기 때문에 DAO에서 커밋, 롤백 하면 안 되고, 서비스에서 해줘야한다 -> 따라서, 서비스에서 커넥션을 만들어줘야만 커밋, 롤백이 가능해진다.
		//예를들어 이체의 경우 출금과 입금이 모두 이뤄져야 이체가 완료 되는 거라서 커밋과 롤백을 DAO가 관리를 한다면 DAO에서 SQL을 업데이트 하면 커밋을 찍어버리기 때문에 출금만 해주고 커밋하는 그런 
		//입금이 이루어지기 전에 커밋이 되어버리는 문제가 발생할 수가 있다. 따라서 원래는 DAO에서 커넥션을 만들어서 커밋 롤백 해줬지만, 이런 문제가 발생할 가능성이 있으므로 DAO말고 서비스에서 커넥션을 만들어 관리해주고, 
		//DAO에서는 파라미터로 커넥션을 받는 형식으로 가야한다. 만약 이체만 해주고 입금은 필요 없다면 이렇게 해줄 필요 없이 DAO에서 커밋 롤백 해줘도 상관 없다. (트랜잭션 단위로 커밋한다)
		
		
		//-> int result = new MemberDao().join(vo, conn);를 해주려면 commit, rollback을 해줘야하는데 이걸 하려면 connection이 있어야해서 다음 밑과 같은 일들을 부가적으로 해줘야함
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnetion();
			
			result = new MemberDao().join(vo, conn);
			
			if(result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
		} catch (Exception e) {
			//예외가 발생해도 롤백 해줘야하니까
			JDBCTemplate.rollback(conn);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		
		return result;
		
	}

	public MemberVo login(MemberVo vo) {

		//SQL 실행을 위해서 커넥션 준비
		Connection conn = null;
		MemberVo loginMember = null;
		try {
			conn = JDBCTemplate.getConnetion();
			
			//SQL 실행결과 리턴
			loginMember = new MemberDao().login(conn, vo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loginMember;
	}
	
}
