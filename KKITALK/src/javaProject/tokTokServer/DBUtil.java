package javaProject.tokTokServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

//	MySQL에 연결하는 메소드
	public static Connection getMySQLConnection() {
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//			?useUnicode=true&characterEncoding=UTF-8을 붙혀주지 않으면 한글이모두"?"로 저장된다.
			String url = "jdbc:mysql://localhost:3306/javadbpm?useUnicode=true&characterEncoding=UTF-8"; 	
			String user = "root";
			String password = "0000";
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 로드할 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("접속 정보가 올바르지 않습니다.");
		}
		
		return conn;
	}
	
//	데이터베이스 작업에 사용한 각종 변수를 닫아주는 메소드
	public static void close(Connection conn) {
		if(conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
	}
	public static void close(Statement stmt) {
		if(stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
	}
	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}}
	}
	public static void close(ResultSet rs) {
		if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
	}
}
