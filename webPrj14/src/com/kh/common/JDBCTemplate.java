package com.kh.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	//커넥션 가져오기 -> 여기에서 발생하는 에러는 유저로그인서블릿과 유저조인서블릿에서의 전체 에러가 되는 것이므로 연결을 가져와야지 Connection conn = JDBCTemplate.getConnection();(이게 성공해야지만) 다음 일을 할 수 있으니까 throw 하는 거임
	//얘가 죽으면 다른 애들까지 같이 문제가 발생한다 싶으면 throw, 내가 처리하고 말 수 있는 사소한 예외, 혼자 죽어도 되는 예외는 try catch
	public static Connection getConnection() throws Exception {
		
		Properties prop = new Properties();
		
		String filePath = JDBCTemplate.class.getResource("/db/setup/data.properties").getPath();//JDBCTemplate.class 파일 담고있는 폴더
		prop.load(new FileInputStream(filePath));
		
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String dbId = prop.getProperty("dbId");
		String dbPwd = prop.getProperty("dbPwd");
		
		//String driver = "oracle.jdbc.driver.OracleDriver";
		//String url ="jdbc:oracle:thin:@localhost:1521:xe";
		//String dbId = "C##KH";
		//String dbPwd = "KH";
		
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url, dbId, dbPwd);
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
			if(conn != null) conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//close
	public static void close(Statement stmt) { //preparestatement 조상이 stmt이다.
		try {
			if(stmt != null) stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection conn) {
		try {
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
