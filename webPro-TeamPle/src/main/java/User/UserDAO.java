package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.DatabaseUtil;

/*
 *  
CREATE TABLE users (
user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
email VARCHAR NOT NULL,
password VARCHAR NOT NULL,
name VARCHAR NOT NULL,
is_admin Boolean DEFAULT FALSE
);

 */

public class UserDAO {

	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/tukoreadb";
//향후 mysql로 변경할 것 , JDPC_URL 각자 자신의 환경과 맞춰 구현하기.

	

	public int signUp(Users user) throws Exception { //회원가입
		Connection conn = DatabaseUtil.open();
		String sql = "insert into USERS(email,password,name) values(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try (conn; pstmt) {
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
	}
		return -1; //데이터 베이스 오류
		
	}
	
	public boolean checkDuplicate(String email) throws Exception  {
		Connection conn = DatabaseUtil.open();
		String sql = "SELECT email FROM USERS WHERE email = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, email);
		ResultSet rs = pstmt.executeQuery();
		try (conn; pstmt; rs) {
			if(rs.next()) {
				return true; //가입된 이메일이 있다면 true
			}
		}catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;
		
	}
	
	public int signIn(Users user) throws Exception { //로그인
		Connection conn = DatabaseUtil.open();
		String sql = "SELECT password, is_Admin FROM USERS WHERE email = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getEmail());
		ResultSet rs = pstmt.executeQuery();
		try (conn; pstmt; rs) {
			if(rs.next()) {
				if(rs.getString("password").equals(user.getPassword()))
					if(rs.getBoolean("is_Admin")) {
						return 2;
					}
					else {
						return 1; //성공
					}
				else {
					return 0; //실패
				}
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //오류	
	}
	


	

	public List<Users> getUserAll() throws Exception { //전체 회원정보 가져오기[관리자 기능] 
		Connection conn = DatabaseUtil.open();
		List<Users> userList = new ArrayList<>();
		String sql = "select users_id, email, name from USER";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		try (conn; pstmt; rs) {
			while (rs.next()) {
				Users user = new Users();
				user.setUser_id(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				userList.add(user);
			}
			return userList;
		}
	}


	public void deleteUser(int userId) throws SQLException { //회원 탈퇴기능[관리자 기능]
		Connection conn = DatabaseUtil.open();
		String sql = "delete from users where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try (conn; pstmt) {
			pstmt.setInt(1, userId);
		
			if (pstmt.executeUpdate() == 0) {
				throw new SQLException("DB에러");
			}
		}
	}
	
//	public void updateUser(User n) throws Exception { //업데이트 기능이 필요한가?
//		Connection conn = open();
//		String sql = "UPDATE NEWS SET title=?, img=?, content=? WHERE aid=?";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		try (conn; pstmt) {
//			pstmt.setString(1, n.getTitle());
//			pstmt.setString(2, n.getImg());
//			pstmt.setString(3, n.getContent());
//			pstmt.setInt(4, n.getAid());
//			pstmt.executeUpdate();
//		}
//	}
	
}
