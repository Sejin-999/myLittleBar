package Drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import Util.DatabaseUtil;

public class DrinkDAO {
	
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/tukoreadb";
	
	//해당 베이스에 대한 술 리스트 가져오기
	public List<Drinks> getDrinkAll(int base_id) throws Exception{
		Connection conn=DatabaseUtil.open();
		List<Drinks> drinkList=new ArrayList<>();
		String sql="select drink_id, image, name from Drink where base_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, base_id);
		ResultSet rs=pstmt.executeQuery();
		
		try(conn;pstmt;rs){
			while(rs.next()) {
				Drinks d=new Drinks();
				d.setDrink_id(rs.getInt("drink_id"));
				d.setImage(rs.getString("image"));
				d.setName(rs.getString("name"));
				drinkList.add(d);
			}
			return drinkList;
		}
	}

	
	public Base getBase(int base_id) throws Exception{
		Connection conn=DatabaseUtil.open();
		Base b=new Base();
		String sql="select image, name from base where base_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, base_id);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		try(conn;pstmt;rs){
			b.setBase_id(rs.getInt("base_id"));
			b.setImage(rs.getString("image"));
			b.setName(rs.getString("name"));
			pstmt.executeQuery();
			return b;
		}
		
	}
	

	
	//찜 목록 리스트 가져오기
//	public List<Likes> getLikeAll(int user_id) throws Exception{
//		Connection conn=DatabaseUtil.open();
//		List<Likes> likeList=new ArrayList<>();
//		String sql="select like_id, drink_id from Likes where user_id=?";
//		PreparedStatement pstmt=conn.prepareStatement(sql);
//		pstmt.setInt(1, user_id);
//		ResultSet rs=pstmt.executeQuery();
//		rs.next();
//		try(conn;pstmt;rs){
//			while(rs.next()) {
//				Likes like=new Likes();
//				like.setLike_id(rs.getInt("like_id"));
//				like.setUser_id(rs.getInt("drink_id"));
//				likeList.add(like);
//			}
//			return likeList;
//		}
//
//	}
	
	
}
