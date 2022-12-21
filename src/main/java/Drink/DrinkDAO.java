package Drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

import Util.DatabaseUtil;

public class DrinkDAO {

	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final String JDBC_URL = "jdbc:mysql://localhost:3306/myLittleCock";

	//베이스 목록 전체 가져오기
	public List<Base> getBaseAll() throws Exception{
		Connection conn = DatabaseUtil.open();
		List<Base> baseList=new ArrayList<>();
		String sql="select base_id, name, image from Base";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		try(conn;pstmt;rs){
			while(rs.next()) {
				Base b= new Base();
				b.setBase_id(rs.getInt("base_id"));
				b.setName(rs.getString("name"));
				b.setImage(rs.getString("image"));
				baseList.add(b);
			}
			return baseList;
		}
	}
	
	
	// 해당 베이스에 대한 술 리스트 가져오기
	public List<Drinks> getDrinkAll(int base_id) throws Exception {
		Connection conn = DatabaseUtil.open();
		List<Drinks> drinkList = new ArrayList<>();
		String sql = "select drink_id, image, name from Drink where base_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, base_id);
		ResultSet rs = pstmt.executeQuery();
		try (conn; pstmt; rs) {
			while (rs.next()) {
				Drinks d = new Drinks();
				d.setDrink_id(rs.getInt("drink_id"));
				d.setImage(rs.getString("image"));
				d.setName(rs.getString("name"));
				drinkList.add(d);
			}
			return drinkList;
		}
	}
	

	public Base getBase(int base_id) throws Exception {
		Connection conn = DatabaseUtil.open();
		Base b = new Base();
		String sql = "select base_id, image, name from base where base_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, base_id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		try (conn; pstmt; rs) {
			b.setBase_id(rs.getInt("base_id"));
			b.setImage(rs.getString("image"));
			b.setName(rs.getString("name"));
			pstmt.executeQuery();
			return b;
		}

	}

	// 찜 목록 리스트 가져오기
	public List<Cart> getCart(int user_id) throws Exception{
		Connection conn=DatabaseUtil.open();
		List<Cart> cartlist=new ArrayList<>();
		String sql="select user_id, like_id, drink_id from Likes where user_id=?";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, user_id);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		try(conn;pstmt;rs){
			while (rs.next()) {
				Cart d = new Cart();
				d.setLike_id(rs.getInt("like_id"));
				d.setDrink_id(rs.getInt("drink_id"));
				d.setUser_id(rs.getInt("user_id"));
				cartlist.add(d);
			}
			return cartlist;

		}
	}
	// 찜 목록에 해당하는 술 가져오기
	public Drinks getDrink(int drink_id) throws Exception {
		Connection conn = DatabaseUtil.open();
		Drinks d = new Drinks();
		String sql = "select drink_id, image, name from Drink where drink_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, drink_id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		try (conn; pstmt; rs) {

			d.setDrink_id(rs.getInt("drink_id"));
			d.setImage(rs.getString("image"));
			d.setName(rs.getString("name"));
			pstmt.executeQuery();

			return d;
		}
	}

}
