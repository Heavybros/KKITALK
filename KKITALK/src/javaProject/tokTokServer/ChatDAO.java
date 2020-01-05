package javaProject.tokTokServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//	DAO(Data Access Object) : SQL 명령을 실행하는 클래스
public class ChatDAO {
	static String msg = "";

//	--------------------------------------------------------------------------------------------
//	-----------------------------------------회원가입-------------------------------------------

//	서버에 아이디,닉네임,패스워드 등록 메서드
	public static void join(String id,String nickName,String password) {
//		DB 접속
		Connection conn = DBUtil.getMySQLConnection();
		try {
//			DB에 입력값(id, nickName, password) 저장			
			String sql = "INSERT INTO chat(id,nickName,PASSWORD) VALUES (?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, nickName);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
			DBUtil.close(conn);
			DBUtil.close(pstmt);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
//	--------------------------------------아이디 중복 확인--------------------------------------
//	아이디 중복시 msg출력 "중복입니다." : false
//	아이디 없을시 msg출력 "가입된 인원이 없습니다." : true
//	return msg;
	public static boolean idCompare(String id, String nickName) {
		boolean result = true;
		Connection conn = DBUtil.getMySQLConnection();
		try {
			String sql = "select * from chat";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
//					입력된 id와 서버의 아이디중 중복이 있다면 
					if(rs.getString("id").equals(id)){
						result = false;
						break;
					} else if(rs.getString("nickName").equals(nickName)) {
						result = false;
						break;
					}
				} while(rs.next());
			} 
			DBUtil.close(conn);
			DBUtil.close(pstmt);
			DBUtil.close(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return result;
	}


//	--------------------------------------------------------------------------------------------
//	------------------------------------------로그인--------------------------------------------
//	1)입력된 아이디에 해당하는 비밀번호와 DB의 비밀번호가 일치하는 경우		:1
//	2)입력된 아이디에 해당하는 비밀번호와 DB의 비밀번호가 일치하지 않는 경우:2
//	3)입력된 아이디가 db에 없을 경우										:3을 리턴
	public static int login(String id,String password) {
		int result = 0;
		Connection conn = DBUtil.getMySQLConnection();	
		try {
//			서버에 입력된 id와 같은 id를 검색
			String sql = "select * from chat where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();	
			if(rs.next()) {
//				서버에 입력된 id의 password와 로그인화면에 입력한 password를 비교
				if(!rs.getString("password").equals(password)){
					System.out.println("비밀번호가 맞지 않습니다.");
					result = 2;
				}else{
//					System.out.println("로그인!");
					result = 1;
				}
//			서버에서 입력된 id와 같은 id가 없을 경우 
			} else {
//				System.out.println("가입된 아이디가 없습니다.");
				result = 3;
			}
			DBUtil.close(conn);
			DBUtil.close(pstmt);
			DBUtil.close(rs);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String getNickName(String id) {
		String nickName = "";
		Connection conn =DBUtil.getMySQLConnection();
		try {
			String sql = "select nickName from chat where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				nickName = rs.getString("nickName");
			}
			DBUtil.close(conn);
			DBUtil.close(pstmt);
			DBUtil.close(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nickName;
	}
}
