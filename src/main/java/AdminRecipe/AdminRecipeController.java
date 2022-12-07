package AdminRecipe;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class AdminRecipeController
 */
@WebServlet("/adminRecipeController") // 향후 칵테일을 관리하는 하나의 Servlet에서 구현한 기능을 합치기
@MultipartConfig(maxFileSize = 1024 * 1024 * 2, location = "")
public class AdminRecipeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminRecipeDAO adminRecipDAO;
	// 일단 하나의 DAO쓰다가 나중에 각각 엔티티에 대해 구현된 DAO에 내용합치고 분리하기
	private ServletContext ctx;
// 웹 리소스 기본 경로 지정
	private final String START_PAGE = "./main.jsp";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		adminRecipDAO = new AdminRecipeDAO();
		// baseDAO , DrinksDAO, baseDAO 각각 DAO를 가져올 것
		ctx = getServletContext();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 한글 처리
		String action = request.getParameter("action");
		// adminRecipDAO = new AdminRecipeDAO();

		Method m; // java.lang을 import
		String view = null;
		// action 파라미터 없이 접근한 경우
		if (action == null) {
			action = "defaultView";
		}
		try {

			m = this.getClass().getMethod(action, HttpServletRequest.class);
			// 메서드 실행후 리턴값 받아옴
			view = (String) m.invoke(this, request);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			ctx.log("요청 action 없음!!");
			request.setAttribute("error", "action 파라미터가 잘못 되었습니다");
			view = START_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (view.startsWith("redirect:/")) {

			String rview = view.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {

			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response); //
		}
	}

	public String uploadBase(HttpServletRequest request) {
		Base base = new Base();

		try {
			// 이미지 파일 저장
			Part part = request.getPart("file");
			String fileName = getFilename(part);
			if (fileName != null && !fileName.isEmpty()) {
				part.write(fileName);
			}
			BeanUtils.populate(base, request.getParameterMap());

			base.setImage("image/base/" + fileName);
			adminRecipDAO.insertBase(base);

		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("베이스 등록 과정에서 문제 발생!!");
			request.setAttribute("error", "베이스 등록 과정에서 문제가 발생하였습니다.");
			return "redirect:/adminRecipeController?action=defaultView";
		}
		return "redirect:/adminRecipeController?action=defaultView";
	}

	public String uploadIngredient(HttpServletRequest request) {
		Ingredient ingredient = new Ingredient();

		try {
			// 이미지 파일 저장
			Part part = request.getPart("file");
			String fileName = getFilename(part);
			if (fileName != null && !fileName.isEmpty()) {
				part.write(fileName);
			}
			BeanUtils.populate(ingredient, request.getParameterMap());

			ingredient.setImage("image/base/" + fileName);
			adminRecipDAO.insertIngredient(ingredient);

		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("재료 등록 과정에서 문제 발생!!");
			request.setAttribute("error", "재료 등록 과정에서 문제가 발생하였습니다.");
			return "redirect:/adminRecipeController?action=defaultView";
		}
		return "redirect:/adminRecipeController?action=defaultView";
	}

	public String uploadRecipe(HttpServletRequest request) {
		// Base base = new base()
		try {
			// 이미지 파일 저장
			Part part = request.getPart("file");
			String fileName = getFilename(part);
			if (fileName != null && !fileName.isEmpty()) {
				part.write(fileName);
			}

			// BeanUtils.populate(n, request.getParameterMap());
			// n.setImg("/img/" + fileName);
			// baseDAO.addBase(base);
			// 1.Drinks에 업로드하기
			// 2.방금 생긴 데이터의 id값 가져오기
			// 3. DrinksDetail테이블에 Drink id , 위의 재료를 for문으로 가져와서? 재료 Id값 넣기

		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("레시피 등록 과정에서 오류가 발생하였습니다.");
			return "redirect:/adminRecipeController?action=defaultView";
		}
		return "redirect:/adminRecipeController?action=defaultView";
	}

	public String defaultView(HttpServletRequest request) {
		return "./manageRecipe.jsp";
	}

	private String getFilename(Part part) {
		String fileName = null;
		// 파일이름이 들어있는 헤더 영역을 가지고 옴
		String header = part.getHeader("content-disposition"); // content-disposition //: response의 컨텐츠 정보 //
		// part.getHeader -> form-data; name="img"; filename="사진5.jpg"
		System.out.println("Header => " + header);
		// 파일 이름이 들어있는 속성 부분의 시작위치를 가져와 쌍따옴표 사이의 값 부분만 가지고옴
		int start = header.indexOf("filename=");
		fileName = header.substring(start + 10, header.length() - 1);
		ctx.log("파일명:" + fileName);
		return fileName;
	}
}