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

@WebServlet("/drinkController")
public class DrinkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DrinkDAO dao;
	private ServletContext ctx;
	
	private final String START_PAGE="./searchList.jsp";
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
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
			action="getSearchList";
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
	
	public String getSearchList(HttpServletRequest request) throws Exception {
//		int base_id=Integer.parseInt(request.getParameter("base_id"));
		List<Drinks> drinkList = null;
		try {
			drinkList=dao.getDrinkAll(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("drinklist", drinkList);
		
		try {
			Base b=dao.getBase(1);
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
	
}
