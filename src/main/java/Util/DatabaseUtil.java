package Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	public static Connection open() {
		
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		final String JDBC_URL = "jdbc:mysql://localhost:3306/myLittleCock"; //자기 환경에 맞는 DB로 변경필
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "root", "1234");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
}