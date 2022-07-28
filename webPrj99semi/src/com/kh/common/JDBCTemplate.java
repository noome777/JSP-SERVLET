package com.kh.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCTemplate {
	
	public static Connection getConnetion() throws Exception {
		

		Properties prop = new Properties();
		
		String filePath = JDBCTemplate.class.getResource("/db/setup/data.properties").getPath();
		prop.load(new FileInputStream(filePath));
		
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String dbId = prop.getProperty("dbId");
		String dbPwd = prop.getProperty("dbPwd");
		
//		String driver = "oracle.jdbc.driver.OracleDriver";
//		String url ="jdbc:oracle:thin:@localhost:1521:xe";
//		String dbId = "C##KH";
//		String dbPwd = "KH";
		
		
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url, dbId, dbPwd);
		return conn;
	}
	
	//커밋, 롤백
	
	
	//close

}
