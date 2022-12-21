package CockTailDetail;

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

import User.UserDAO;
//컨트롤러가 필요해지면 넣기로..
/**
 * Servlet implementation class CocktailController
 */
@WebServlet("/CocktailController")
public class CocktailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CockTailDao cdao;
    private ServletContext ctx;
    private final String START_PAGE="./cocktailDetail.jsp";
    
    public void init(ServletConfig config) throws ServletException {
		super.init(config);

		cdao = new CockTailDao();
		ctx = getServletContext();
    
    }
   public void service(HttpServletRequest request , HttpServletResponse response)
		   throws ServletException,IOException{
	   request.setCharacterEncoding("utf-8");  //actionCock == service
	   String viewPage = null;
	   String action = request.getParameter("action");
	   cdao = new CockTailDao();
	   
	   Method m; // java.lang을 import
	   String view = null;
	   
	   if(action == null) {
		   action = "getCockTailDetail";
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
   }  //service End
   
   
   public String getCockTailDetail(HttpServletRequest req) {
	  // int drink_id = Integer.parseInt(req.getParameter("drink_id"));
	   int drink_id =1;
	   List<Cocktail> detailCock = null;
	   try {
		   detailCock=cdao.getSave(drink_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("DetailCock", detailCock);
		
	   return "cocktailDetail.jsp";
   }

}
