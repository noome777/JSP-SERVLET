package com.kh.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlTemplate {
	
	//getConnection
	
	//commit
	
	//rollback
	
	//close

	public static SqlSession getSqlSession() {
	
		SqlSession ss = null;
		
		try {
			//resources는 src처럼 최상단 경로이므로 생략
			String resource = "/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
		    ss = sqlSessionFactory.openSession(false);
			//openSession(boolean flag) : 자동커밋 여부 설정 , 기본값 : false
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ss;
		
	}
}
