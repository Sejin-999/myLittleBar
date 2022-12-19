package CockTailDetail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import Util.DatabaseUtil;


public class CockTailDao {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/tukoreadb";
	PreparedStatement pstmt;
	ResultSet rs;
	
	int setDrinkId;  // 컨트롤러를 통해 detail에 대한 아이디 값을 가져오기 
	int InLeng ; //제료가 몇개들어가 있는지 저장
	
	int saveIN;  //INGREDIENT 아이디 저장
	int [] saveINBuf = new int[100];  //INGREDIENT 아이디 저장
	int saveDrink; //(DRINK 아이디 저장)
	int saveBase; //(base 아이디 저장)
	
	String saveInName;
	String [] saveInNameBuf = new String[100]; //(INGREDIENT 이름 저장) //이후에 buf에 담아서 출력하는 형식으로...
	String saveDrinkName; //(DRINK 네임 저장)
	String saveBaseName; //(Base 네임 저장)
	
	String saveInImg_src; //(INGREDIENT 이미지 경로 저장) //이후에 buf에 담아서 출력하는 형식으로...
	String [] saveInImgBuf = new String[100] ;  //세이브한 이미지 경로를 저장하기 위한 buf
	String saveDrinkImg_src;
	String saveBaseImg_src;
	
	Cocktail c = new Cocktail(); // 모델 저장
	int i=0;  //초기화
	
	// 기본 정보 세팅 메소드 Start //
	public List<Cocktail> getSave(int getDrinkId) throws SQLException {
		Connection con = DatabaseUtil.open();
		setDrinkId = getDrinkId;
		
		/*칵테일에 재료가 몇개 들어가는지 갯수 구하기 Start*/ //나중에 재료가 몇개들어가있는지 크기를계산해서 재료를 뽑음
		String SQLINLen = "select count (INGREDIENT_ID) from DRINKDETAIL where drink_id = ?";
		pstmt = con.prepareStatement(SQLINLen);
		pstmt.setInt(1, setDrinkId);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			InLeng =rs.getInt(1);
			//System.out.println("재료 갯수는 "+InLeng);
		}
		/*칵테일에 재료가 몇개 들어가는지 갯수 구하기 End*/
	
		/*재료 아이디 가져오기 Start*/
		String SQLIN = "select INGREDIENT_ID from DRINKDETAIL where drink_id =?";
		pstmt = con.prepareStatement(SQLIN);
		pstmt.setInt(1, setDrinkId);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			saveINBuf[i] = rs.getInt(1);
			i++;
		}
		/*재료 아이디 가져오기 END*/
		
		/*Base 술 아이디 가져오기 Start*/
		String SQLBase = "select Base_ID from Drink where DRINK_ID=?";
		pstmt = con.prepareStatement(SQLBase);
		pstmt.setInt(1, setDrinkId);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			saveBase = rs.getInt(1);  
		}
		/*Base 술 아이디 가져오기 End*/
		 
		//재료 이름 가져오기 Start //
		int tempIn;
		for(int k=0; k<InLeng; k++) {
			tempIn = saveINBuf[k];
			System.out.println("뽑은 재료 아이디 "+ tempIn);
			String SQLINName = "select name , image from ingredient where ingredient_id =?";
			pstmt = con.prepareStatement(SQLINName);
			pstmt.setInt(1, tempIn);  //뽑은 제료 아아디로 이름,이미지경로 조회
			rs = pstmt.executeQuery();
			if(rs.next()) {
				saveInName = rs.getString(1);
				saveInImg_src = rs.getString(2);
				saveInNameBuf[k] = saveInName;
				saveInImgBuf[k] = saveInImg_src;
			}
		}
		/*모델 저장 Start*/
		c.setiNameBuf(saveInNameBuf); //이름저장
		c.setiImageBuf(saveInImgBuf); //이미지 저장
		
		//재료 이름 가져오기 End //
		
		//칵테일 이름 가져오기 Start //
		String SQLDrinkName = "select name , image from drink where drink_id =?";
		pstmt = con.prepareStatement(SQLDrinkName);
		pstmt.setInt(1, setDrinkId);  //저장된 재료 아이디값으로 설정
		rs = pstmt.executeQuery();
		if(rs.next()) {
			saveDrinkName = rs.getString(1);
			saveDrinkImg_src = rs.getString(2);
			c.setcName(saveDrinkName);
			c.setcImage(saveDrinkImg_src);
		}
		//칵테일 이름 가져오기 End //
		
		//베이스술 이름 가져오기 Start //
		String SQLBaseName = "select name ,image from base where base_id =?";
		pstmt = con.prepareStatement(SQLBaseName);
		pstmt.setInt(1,setDrinkId);  //저장된 재료 아이디값으로 설정
		rs = pstmt.executeQuery();
		if(rs.next()) {
			saveBaseName = rs.getString(1);
			saveBaseImg_src = rs.getString(2);
			c.setbName(saveBaseName);
			c.setbImage(saveBaseImg_src);
		}
		//System.out.println("베이스 이름 : " + c.getbName()+"\n 베이스 이미지 경로 : "+ c.getbImage());
		//베이스술 이름 가져오기 End //
		
		/*넘겨줄 정보 확인 Start*/
		//어떤식으로 뽑을 것인지...
		System.out.println("여기 있는 정보들은 모델에 저장되어 리스트로 내보낼 값들이다.");
		System.out.println("칵테일 이름 : " + c.getcName() +"\n 칵테일 이미지 경로 : "+ c.getcImage());
		System.out.println("베이스 이름 : " + c.getbName()+"\n 베이스 이미지 경로 : "+ c.getbImage());
		String [] tempINameBuf = c.getiNameBuf();
		String [] tempIImgBuf = c.getiImageBuf();
		
		for(int k=0; k<InLeng; k++) {
			System.out.println(k+"모델에 저장된 이름" +tempINameBuf[k]
					+"  "+ k+"모델에 저장된 이미지경로"+tempIImgBuf[k]);
		}
		/*넘겨줄 정보 확인 End*/
		
		//리스트 값으로 넘겨줄것
		List<Cocktail> detailCock = new ArrayList<>();
		Cocktail cock = new Cocktail();
		detailCock.add(cock);
		System.out.println("******************");
		
		return detailCock;
	}
	// 기본 정보 세팅 메소드 End //
	
	
	// 베이스 주기 Start
	public Base getBase(int getdrink_id) throws SQLException {
		Connection con = DatabaseUtil.open();
		int base_id=0;
		String SQLBase = "select Base_ID from Drink where DRINK_ID=?";
		pstmt = con.prepareStatement(SQLBase);
		pstmt.setInt(1, setDrinkId);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			saveBase = rs.getInt(1);  
		}
		
		Base b = new Base();
		base_id = saveBase;
		String sql = "select base_id, image, name from base where base_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, base_id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		try (con; pstmt; rs) {
			b.setBase_id(rs.getInt("base_id"));
			b.setImage(rs.getString("image"));
			b.setName(rs.getString("name"));
			pstmt.executeQuery();
			return b;
		}
	}
	
	// 베이스 주기 End
	
	

	public void getSaveImgSrc() {
		Connection con = DatabaseUtil.open();
	}
	// 이미지 경로 가져오기 메소드 End//
	
	public static void main(String[] args) throws IOException, SQLException {
		CockTailDao abc =  new CockTailDao();
		abc.getSave(1);
		
	}
}
