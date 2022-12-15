package AdminRecipe;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
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

import Drink.Base;
import Drink.DrinkDAO;
import Drink.Drinks;
import Util.Constants;

/**
 * Servlet implementation class AdminRecipeController
 */
@WebServlet("/adminRecipeController") // 향후 칵테일을 관리하는 하나의 Servlet에서 구현한 기능을 합치기
@MultipartConfig(maxFileSize = 1024 * 1024 * 2, location = "")
public class AdminRecipeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminRecipeDAO adminRecipDAO;
	private DrinkDAO drinkDAO;
	// 일단 하나의 DAO쓰다가 나중에 각각 엔티티에 대해 구현된 DAO에 내용합치고 분리하기
	private ServletContext ctx;
// 웹 리소스 기본 경로 지정
	private final String START_PAGE = "./main.jsp";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		adminRecipDAO = new AdminRecipeDAO();
		drinkDAO = new DrinkDAO();
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
				part.write(Constants.path + "/base/" + fileName);
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
				part.write(Constants.path + "/ingredient/" + fileName);
			}
			BeanUtils.populate(ingredient, request.getParameterMap());

			ingredient.setImage("image/ingredient/" + fileName);
			adminRecipDAO.insertIngredient(ingredient);

		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("재료 등록 과정에서 문제 발생!!");
			request.setAttribute("error", "재료 등록 과정에서 문제가 발생하였습니다.");
			return "redirect:/adminRecipeController?action=defaultView";
		}
		return "redirect:/adminRecipeController?action=defaultView";
	}

	public String defaultView(HttpServletRequest request) {

		List<Base> baseList = null;
		List<Ingredient> ingredientList = null;
		try {
			baseList = drinkDAO.getBaseAll();
			request.setAttribute("baseList", baseList);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "베이스 리스트를 가져오던 중 오류가 발생하였습니다.");
		}

		try {
			ingredientList = adminRecipDAO.getIngredientAll();
			request.setAttribute("ingredientList", ingredientList);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", request.getAttribute("error") + "<br> 재료 리스트를 가져오던 중 오류가 발생하였습니다.");
		}

		return "./manageRecipe.jsp";
	}

	public String uploadCocktail(HttpServletRequest request) {

		Drinks drink = new Drinks();
		int drink_id = 0;
		String list[] = request.getParameterValues("ingredient");
		ArrayList<String> ingredients = new ArrayList<String>();
		if (list != null) {
			for (String data : list) {
				ingredients.add(data);
			}

			ArrayList<String> removed = new ArrayList<>();
			for (String item : ingredients) {
				if (item.equals("0")) {
					removed.add(item);
				}
			}

			for (String item : removed) {
				ingredients.remove(item);
			}

			if (ingredients.isEmpty()) {
				request.setAttribute("error", "1개 이상의 재료를 업로드 해주세요.");
				return "./adminRecipeController?action=defaultView";
			}
		} else {
			request.setAttribute("error", "1개 이상의 재료를 업로드 해주세요.");
			return "./adminRecipeController?action=defaultView";
		}

		if (request.getParameter("base_type").equals("0")) {
			request.setAttribute("error", "베이스를 꼭 지정해주세요.");
			return "./adminRecipeController?action=defaultView";
		} else {

			drink.setBase_id(Integer.parseInt(request.getParameter("base_type")));
			drink.setName(request.getParameter("title"));
			drink.setImage(request.getParameter("file"));
			try {
				Part part = request.getPart("file");
				String fileName = getFilename(part);
				if (fileName != null && !fileName.isEmpty()) {
					part.write(Constants.path + "/base/" + fileName);
				}
				drink.setImage("image/drink/" + fileName);
				drink_id = adminRecipDAO.insertCocktail(drink);
			} catch (Exception e) {
				e.printStackTrace();

				request.setAttribute("error", "칵테일 업로드 중 오류가 발생하였습니다.");
				return "redirect:/adminRecipeController?action=defaultView";
			}

		}

		for (String item : ingredients) {

			try {
				adminRecipDAO.insertCocktailDetail(drink_id, Integer.parseInt(item));

			} catch (Exception e) {
				request.setAttribute("error", "재료 업로드 중 오류가 발생하였습니다.");
				return "./adminRecipeController?action=defaultView";
			}
		}

		return "redirect:/adminRecipeController?action=defaultView";
	}

	private String getFilename(Part part) {
		String fileName = null;
		// 파일이름이 들어있는 헤더 영역을 가지고 옴
		String header = part.getHeader("content-disposition"); // content-disposition //: response의 컨텐츠 정보 //
		System.out.println("Header => " + header);
		int start = header.indexOf("filename=");
		fileName = header.substring(start + 10, header.length() - 1);
		return fileName;
	}

}