package DrinkDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Drink.Base;
import Drink.Drinks;
import Util.DatabaseUtil;

public class DrinkDetailDAO {

	final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	final String JDBC_URL = "jdbc:mysql://localhost:3306/myLittleCock";;
	String setbase_id;
	
	
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
	
	
	public DrinkBase getBase(int drink_id) throws Exception {
		Connection conn = DatabaseUtil.open();
		int base_id=1; //못찾으면 그냥 1번보냄
		String sqlFinddID = "select base_id from drink where drink_id =?";
		PreparedStatement pstmt = conn.prepareStatement(sqlFinddID);
		pstmt.setInt(1, drink_id);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			
			base_id = rs.getInt(1);
			System.out.println("base ID" +base_id);
			DrinkBase b = new DrinkBase();
			String sql = "select base_id, image, name from base where base_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, base_id);
			rs = pstmt.executeQuery();
			rs.next();
			try (conn;) {
				b.setBase_id(rs.getInt("base_id"));
				b.setBase_image(rs.getString("image"));
				b.setBase_name(rs.getString("name"));
				pstmt.executeQuery();
				System.out.println("base ID" + b.getBase_id());
				System.out.println("base name" + b.getBase_name());
				System.out.println("base img" + b. getBase_image());
				
				return b;
			}
		}
		return null; //에러
	}
	
	public List<Ingredient> getIngredient(int drink_id) throws SQLException{
		Connection conn = DatabaseUtil.open();
		List<Ingredient> IngredientList = new ArrayList<>();
		String sql = "select INGREDIENT_ID from DRINKDETAIL where DRINK_ID =?";
		int temp_id;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, drink_id);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Ingredient i = new Ingredient();
			temp_id = rs.getInt(1);
			System.out.println(temp_id);
			String findInSQL = "select INGREDIENT_ID,NAME,IMAGE from INGREDIENT where INGREDIENT_ID =?";
			pstmt = conn.prepareStatement(findInSQL);
			pstmt.setInt(1, temp_id);
			rs = pstmt.executeQuery();
			rs.next();
			i.setIngredient_id(rs.getInt ("INGREDIENT_ID"));
			i.setIngredient_name(rs.getString("name"));
			i.setIngredient_Image(rs.getString("image"));
			System.out.println("IN ID"+i.getIngredient_id() +"  IN name"+i.getIngredient_name()+" IN img"+i.getIngredient_Image());

			IngredientList.add(i);
		}
		//System.out.println("IngredientList" +IngredientList.get(0).getIngredient_name());
		return IngredientList;
	}
	
	public void findIngre(int get_inID) throws SQLException{
		System.out.println("확인용");
		Connection conn = DatabaseUtil.open();
		String findInSQL = "select INGREDIENT_ID,NAME,IMAGE from INGREDIENT where INGREDIENT_ID =?";
		PreparedStatement pstmt = conn.prepareStatement(findInSQL);
		pstmt.setInt(1, get_inID);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			Ingredient i = new Ingredient();
			i.setIngredient_id(rs.getInt ("INGREDIENT_ID"));
			i.setIngredient_name(rs.getString("name"));
			i.setIngredient_Image(rs.getString("image"));
			//System.out.println("IN ID"+i.getIngredient_id() +" IN name"+i.getIngredient_name()+" IN img"+i.getIngredient_Image());
		}
	}
	
	public DrinkInfo getDrinkInfo (int drink_id) throws SQLException {
		System.out.println("드링크 인포확인");
		Connection conn = DatabaseUtil.open();
		DrinkInfo dinfo = new DrinkInfo();
		String findInfoSQL = "select DRINKINFO_CONTENT,DRINKINFO_URL from DRINKINFO  where drink_id =?";
		PreparedStatement pstmt = conn.prepareStatement(findInfoSQL);
		pstmt.setInt(1, drink_id);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			dinfo.setDrinkInfo_content(rs.getString("DRINKINFO_CONTENT"));
			dinfo.setDrinkInfo_URL(rs.getString("DRINKINFO_URL"));
			
			System.out.println("드링크 컨텐트:" + dinfo.getDrinkInfo_content() +"\n드링크 url :"+dinfo
					.getDrinkInfo_URL());
			
			return dinfo;
			
		}
		
		return null; //에러
		
		
	}
	
	public void getPlus(int user_id , int drink_id) throws SQLException {
		System.out.println("드링크 인포확인");
		Connection conn = DatabaseUtil.open();
		String sql = "insert into LIKES(user_id , drink_id) values (?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, user_id);
		pstmt.setInt(2, drink_id);
		pstmt.execute();
		System.out.println("확인됨");
	}
	
	
	public static void main(String[] args) throws Exception  {
		DrinkDetailDAO da = new DrinkDetailDAO();
		
		da.getPlus(1,2);
	}
	
	
	
}
