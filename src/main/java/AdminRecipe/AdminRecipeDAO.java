package AdminRecipe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Drink.Base;
import Drink.Drinks;
import Util.DatabaseUtil;

public class AdminRecipeDAO {

	public int insertBase(Base base) throws Exception { // base데이터 추가
		Connection conn = DatabaseUtil.open();
		String sql = "insert into Base(name,image) values(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try (conn; pstmt) {
			pstmt.setString(1, base.getName());
			pstmt.setString(2, base.getImage());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터 베이스 오류

	}

	public int insertIngredient(Ingredient ingredient) throws Exception { // 재료 데이터 추가
		Connection conn = DatabaseUtil.open();
		String sql = "insert into Ingredient(name,image) values(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try (conn; pstmt) {
			pstmt.setString(1, ingredient.getName());
			pstmt.setString(2, ingredient.getImage());

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터 베이스 오류

	}

	public List<Ingredient> getIngredientAll() throws Exception {
		Connection conn = DatabaseUtil.open();
		List<Ingredient> ingredientList = new ArrayList<>();
		String sql = "select ingredient_id, name, image from Ingredient";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		try (conn; pstmt; rs) {
			while (rs.next()) {
				Ingredient data = new Ingredient();
				data.setIngredient_id(rs.getInt("ingredient_id"));
				data.setName(rs.getString("name"));
				data.setImage(rs.getString("image"));
				ingredientList.add(data);
			}
			return ingredientList;
		}
	}

	public int insertCocktail(Drinks drink) throws Exception { // base데이터 추가
		Connection conn = DatabaseUtil.open();

		String sql = "insert into Drink(name,image, base_id) values(?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		try (conn; pstmt;) {
			pstmt.setString(1, drink.getName());
			pstmt.setString(2, drink.getImage());
			pstmt.setInt(3, drink.getBase_id());
			pstmt.executeUpdate(); // 데이터 삽입
			ResultSet rs = pstmt.getGeneratedKeys();
			try (rs) {
				if (rs.next()) {
					return rs.getInt(1); // 생성된 drink_id값 반환
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터 베이스 오류

	}

	public int insertCocktailDetail(int drinkId, int ingredientId) throws Exception { // 재료 데이터 추가

		Connection conn = DatabaseUtil.open();
		String sql = "insert into DrinkDetail(drink_id,ingredient_id) values(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try (conn; pstmt) {

			pstmt.setInt(1, drinkId);
			pstmt.setInt(2, ingredientId);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터 베이스 오류

	}

	public int insertDetailInfo(String content, String link, int drinkId) throws Exception{
		Connection conn = DatabaseUtil.open();
		String sql = "insert into DrinkInfo(drinkInfo_content,drinkInfo_url, drink_id) values(?,?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try (conn; pstmt) {

			pstmt.setString(1, content);
			pstmt.setString(2, link);
			pstmt.setInt(3, drinkId);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터 베이스 오류

	}

}