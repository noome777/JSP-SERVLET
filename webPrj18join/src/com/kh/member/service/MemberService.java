package com.kh.member.service;

import java.sql.Connection;

import com.kh.common.JDBCTemplate;
import com.kh.member.dao.MemberDao;

public class MemberService {

	public int join(String userId, String userPwd) {
		
		Connection conn = null;
		
		conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().join(conn, userId, userPwd);
		
		if(result == 1) {
			//commit
			JDBCTemplate.commit(conn);
		}
		
		return result;
	}

}
