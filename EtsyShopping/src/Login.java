import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.Etsyuser;

/**
 * Servlet implementation class User
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String loginErr;
	private String signupErr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        loginErr = "";
        signupErr = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("logout") != null) {

			session.invalidate();

			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		loginErr = "";
		if (request.getParameter("LoginSub") != null) {
			if (request.getParameter("name") != null) {
				String inputUserN = request.getParameter("name");

				if (EtsyuserDB.selectByName(inputUserN) == null) {
					loginErr += "<script type=\"text/javascript\">validateName()</script>";
					request.setAttribute("loginErr", loginErr);
					getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
				} else {
					if (EtsyuserDB.selectByName(inputUserN).getPassword().equals(request.getParameter("password"))) {

						Etsyuser user = EtsyuserDB.selectByName(inputUserN);
						session.setAttribute("user", user);
						System.out.println("user name login " + user.getName());

						//session.setAttribute("cartCheckout", EtsycartDB.selectByUserStatus(inputUserN, 0));
						response.sendRedirect("/EtsyShopping/EtsyProduct");
					} else {
						loginErr += "<script type=\"text/javascript\">validatePassword()</script>";
						request.setAttribute("loginErr", loginErr);
						getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
					}
				}
			}
		}
		
		signupErr = "";
		if (request.getParameter("SignupSub") != null) {
			if ((request.getParameter("name") != null) && (request.getParameter("email") != null)) {
				String inputUserE = request.getParameter("email");
				
				if (EtsyuserDB.selectByEmail(inputUserE) == null) {
					Etsyuser user = new Etsyuser();
					String name = request.getParameter("name");
					String email = request.getParameter("email");
					user.setName(name);
					user.setEmail(email);
					String password = request.getParameter("password");
					user.setPassword(password);
					EtsyuserDB.insert(user);
					System.out.println("test " + user.getName());
					session.setAttribute("user", user);


					response.sendRedirect("/EtsyShopping/EtsyProduct");
				} else {
					signupErr += "<script type=\"text/javascript\">validateEmail()</script>";
					request.setAttribute("signupErr", signupErr);
					getServletContext().getRequestDispatcher("/Signup.jsp").forward(request, response);
				}
			}
		}
	}

}
