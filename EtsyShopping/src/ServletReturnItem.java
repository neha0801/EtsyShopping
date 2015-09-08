

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Etsycart;
import model.Etsyitem;
import model.Etsyuser;
import customTools.DBUtil;

/**
 * Servlet implementation class SrvletReturnItem
 */
@WebServlet("/ReturnItem")
public class ServletReturnItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReturnItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get of return");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cartIdStr = request.getParameter("cartId");
		String itemIdStr = request.getParameter("itemId");
		HttpSession session = request.getSession();
		int cartId=0;
		long itemId=0;
		
		if(cartIdStr!=null && itemIdStr!=null){
			cartId= Integer.parseInt(cartIdStr);
			itemId= Long.parseLong(itemIdStr);
		}
		Etsycart cart = DBUtil.getSelectedCartItem(cartId);
		Etsyuser user = (Etsyuser) session.getAttribute("user");
		DBUtil.updateCredit(user, cart.getTotalprice());
		DBUtil.returnItem(cartId,itemId);
		getServletContext().getRequestDispatcher("/OrderHistory").forward(request, response);
		
	}

}
