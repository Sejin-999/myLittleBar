package Drink;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DrinkDetail.DrinkBase;
import DrinkDetail.DrinkDetailDAO;
import DrinkDetail.Ingredient;

@WebServlet("/drinkController")
public class DrinkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DrinkDAO dao;
	private DrinkDetailDAO ddao;
	private ServletContext ctx;
	
	private final String START_PAGE="./main.jsp";
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ddao = new DrinkDetailDAO();
		dao=new DrinkDAO();
		ctx=getServletContext();
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action=request.getParameter("action");
		dao=new DrinkDAO();
		
		Method m;
		String view=null;
		
		if (action==null) {
			action="listBase";
		}
		
		try {
			m=this.getClass().getMethod(action, HttpServletRequest.class);
			
			view=(String)m.invoke(this, request);
		}catch (NoSuchMethodException e){
			e.printStackTrace();
			ctx.log("요청 action 없음!!");
			request.setAttribute("error", "action 파라미터가 잘못 되었습니다!!");
			view=START_PAGE;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(view.startsWith("redirect:/")) {
			String rview=view.substring("redirect:/".length());
			response.sendRedirect(rview);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}
	
	public String listBase(HttpServletRequest request) throws Exception {
		List<Base> list = null;
		try {
			list=dao.getBaseAll();
			request.setAttribute("baseList", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("베이스 목록 과정에서 문제 발생!!");
			request.setAttribute("error", "베이스 목록이 정상적으로 처리되지 않았습니다!!");
		}
		
		return "main.jsp";
	}
	
	public String getSearchList(HttpServletRequest request) throws Exception {
		int base_id=Integer.parseInt(request.getParameter("base_id"));
		List<Drinks> drinkList = null;
		try {
			drinkList=dao.getDrinkAll(base_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("drinklist", drinkList);
		
		try {
			Base b=dao.getBase(base_id);
			request.setAttribute("base", b);
		}catch(SQLException e) {
			e.printStackTrace();
			ctx.log("베이스를 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "베이스를 정상적으로 가져오지 못했습니다");
		}
		
		
		return "searchList.jsp";
	}
	
	
	public String getCartAll(HttpServletRequest request) throws Exception {
		int user_id=Integer.parseInt(request.getParameter("user_id"));
		List<Cart> cartlist=null;
		try{
			cartlist=dao.getCart(user_id);
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		request.setAttribute("cartlist", cartlist);
		
		try {
			Drinks d=dao.getDrink(3);
			request.setAttribute("drink", d);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("베이스를 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "베이스를 정상적으로 가져오지 못했습니다");
		}
		return "cartlist.jsp";
	}
	
	public String getDetailDrink(HttpServletRequest request) throws Exception {
		int drink_id = Integer.parseInt(request.getParameter("drink_id"));
		List <Ingredient> ingredientList = null;
		
		//base
		try {
			DrinkBase b=ddao.getBase(drink_id);
			request.setAttribute("base", b);
		}catch(SQLException e) {
			e.printStackTrace();
			ctx.log("디테일 드링크 : 베이스를 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "베이스를 정상적으로 가져오지 못했습니다");
		}
		// drink
		try {
			Drinks d = ddao.getDrink(drink_id);
			request.setAttribute("drink", d);
		}catch(SQLException e) {
			e.printStackTrace();
			ctx.log("디테일 드링크 : 칵테일을 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "칵테일을 정상적으로 가져오지 못했습니다");
		}
		// ingredient
		try {
			ingredientList = ddao.getIngredient(drink_id);
			request.setAttribute("ingredientList", ingredientList);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ctx.log("디테일 드링크 : 재료리스트를 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "재료리스트를 정상적으로 가져오지 못했습니다");
		}
		
		
		
		return "DetailDrink.jsp";
	}
	
}
