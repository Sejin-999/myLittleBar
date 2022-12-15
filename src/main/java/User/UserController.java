package User;

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
 * Servlet implementation class NewsController
 */
@WebServlet("/userController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;
	private ServletContext ctx;
// 웹 리소스 기본 경로 지정
	private final String START_PAGE = "main.jsp";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		dao = new UserDAO();
		ctx = getServletContext();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 한글 처리
		String action = request.getParameter("action");
		dao = new UserDAO();
		// 자바 리플렉션을 사용해 if, switch 없이 요청에 따라 구현 메서드가 실행되도록 함. 즉 action이름과 동일한 메서드를 호출

		Method m; // java.lang을 import
		String view = null;
		// action 파라미터 없이 접근한 경우
		if (action == null) {
			action = "defaultView"; // action 파라미터가 없다면 action 값을 listNews로 설정하여 시작페이지로 이동 }
		}
		try {
			// 현재 클래스에서 action 이름과 HttpServletRequest 를 파라미터로 하는 메서드 찾음
			m = this.getClass().getMethod(action, HttpServletRequest.class);
			// 메서드 실행후 리턴값 받아옴
			view = (String) m.invoke(this, request);
		} catch (NoSuchMethodException e) { // NoSuchMethodErrors는 런타임(runtime) 시점에 존재하지 않는 메서드(method)를 호출하는 경우에 발생
			e.printStackTrace();
			// 에러 로그를 남기고 view 를 로그인 화면으로 지정, 앞에서와 같이 redirection 사용도 가능.
			ctx.log("요청 action 없음!!");
			request.setAttribute("error", "action 파라미터가 잘못 되었습니다!!");
			view = START_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (view.startsWith("redirect:/")) {
			// redirect:/ 문자열 이후 경로만 가지고 옴
			String rview = view.substring("redirect:/".length()); // redirect:/ 문자열 이후의 경로
			response.sendRedirect(rview);
		} else {
			// 지정된 뷰로 포워딩, 포워딩시 컨텍스트경로는 필요없음.
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response); // 지정된 뷰로 포워딩하며, 포워딩 할 때 콘텍스트 경로는 필요없음
		}
	}

	public String signUp(HttpServletRequest request) {
		Users user = new Users();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");

		if (!password.equals(rePassword)) {
			request.setAttribute("error", "재 입력된 비밀번호가 일치하지 않습니다. ");
			return "./signUp.jsp";
		}

		try {
			if (dao.checkDuplicate(email)) {
				request.setAttribute("error", "이미 가입된 아이디입니다.");
				return "./signUp.jsp";
			}
			BeanUtils.populate(user, request.getParameterMap());
			dao.signUp(user);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("회원가입 중 오류");
			request.setAttribute("error", "회원가입중 오류가 발생하였습니다.");
			return "./signUp.jsp";
		}
		return "redirect:/userController?action=defaultView"; // “redirect:/” 이후에 오는 경로 부분에서 “프로젝트명” 다음에 오는 경로만 작성
	}

	public String signIn(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Users user = new Users();

		try {

			BeanUtils.populate(user, request.getParameterMap());

			if (!dao.checkDuplicate(user.getEmail())) {
				request.setAttribute("error", "가입되지 않은 이메일입니다.");
				return "./signIn.jsp";
			}
			SignInDTO result = dao.signIn(user);
			if (result.getUser_id() != -1) { //비밀번호가 일치한다면
				if (result.getIs_admin() == true) { //관리자라면
					session.setAttribute("userEmail", user.getEmail());
					session.setAttribute("isAdmin", true);
					session.setAttribute("userId", result.getUser_id());
					return "drinkController?action=listBase";
				} else {
					session.setAttribute("userEmail", user.getEmail());
					session.setAttribute("userId", result.getUser_id());
					return "drinkController?action=listBase";
				}

			}

			else {
				request.setAttribute("error", "비밀번호가 일치하지 않습니다.");
				return "./signIn.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "로그인 중 오류가 발생하였습니다.");
			return "./signIn.jsp";
		}

	}

	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "drinkController?action=listBase";
	}

	public String manageUser(HttpServletRequest request) { // 유저 관리자 페이지
		List<Users> list;
		try {
			list = dao.getUserAll();
			request.setAttribute("userList", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("유저 관리자 페이지 오류발생");
		}

		return "./manageUser.jsp"; //
	}

	public String deleteUser(HttpServletRequest request) {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		try {
			dao.deleteUser(user_id);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("유저 삭제 중 오류 발생");
			request.setAttribute("error", "유저 삭제 중 오류가 발생하였습니다.");
			return "/userController?action=manageUser";
		}
		return "redirect:/userController?action=manageUser";
	}

	public String defaultView(HttpServletRequest request) {
		return "./signIn.jsp";
	}

}