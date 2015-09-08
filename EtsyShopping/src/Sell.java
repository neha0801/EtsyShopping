

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Etsyitem;

/**
 * Servlet implementation class Sell
 */
@WebServlet("/Sell")
public class Sell extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sell() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/Sell.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null){
			if (request.getParameter("submit") != null) {
				String name = request.getParameter("name");
				String picture = request.getParameter("picture");
				String description = request.getParameter("description");
				double price = Double.parseDouble(request.getParameter("price"));
				double shipping = Double.parseDouble(request.getParameter("shipping"));
				
				Etsyitem newItem = new Etsyitem(name, picture, description, price, shipping);
				EtsyitemDB.insert(newItem);
				
				response.sendRedirect("/EtsyShopping/EtsyProduct");
			}
		}else {
			
		}
	}

}
