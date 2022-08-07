package com.kh.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	public static Connection getConnetion() throws Exception {
		

		Properties prop = new Properties();
		
		String filePath = JDBCTemplate.class.getResource("/db/setup/data.properties").getPath();
//		System.out.println("가져온 경로 확인 ::: " + JDBCTemplate.class.getResource("/").getPath());
		
		prop.load(new FileInputStream(filePath));
		
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String id = prop.getProperty("dbId");
		String pwd = prop.getProperty("dbPwd");
		
//		String driver = "oracle.jdbc.driver.OracleDriver";
//		String url ="jdbc:oracle:thin:@localhost:1521:xe";
//		String dbId = "C##KH";
//		String dbPwd = "KH";
		
		
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url, id, pwd);
		//직접 커밋하기 위해 자동커밋을 꺼준다 
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	
	//커밋, 롤백
	public static void commit(Connection conn) {
		
		try {
			if(conn != null)conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void rollback(Connection conn) {
			
			try {
				if(conn != null)conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	
	//close
	public static void close(Statement stmt) {
		try {
			if(stmt != null)stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs != null)rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			if(conn != null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

