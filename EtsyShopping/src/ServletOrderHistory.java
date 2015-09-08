

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.Etsycart;
import model.Etsyuser;
import customTools.DBUtil;

/**
 * Servlet implementation class ServletOrderHistory
 */
@WebServlet("/OrderHistory")
public class ServletOrderHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletOrderHistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Etsyuser user = (Etsyuser) session.getAttribute("user");
		String message = showOrderHistory(user);
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/OrderConfirmation.jsp").forward(request, response);
	}
	private String showOrderHistory(Etsyuser user){
		List<Etsycart>  cartList = DBUtil.getOrderedCart(user);
		String tableData ="";

		if(cartList!=null)
		{
			tableData+="<table class='table table-bordered table-striped'>";
			tableData += "<tr>";
			tableData += "<thead>";
			tableData += "<th>";
			tableData += "";
			tableData += "</th>";
			tableData += "<th>"; 
			tableData += "Product Name";
			tableData += "</th>";
			tableData += "<th>";
			tableData += "Quantity";
			tableData += "</th>";
			tableData += "<th>";
			tableData += "Price";
			tableData += "</th>";
			tableData += "<th>";
			tableData += "TotalPrice";
			tableData += "</th>";
			tableData += "<th>";
			tableData += "";
			tableData += "</th>";
			tableData += "</thead>";
			tableData += "</tr>";
			
			for(Etsycart c : cartList){
				tableData += "<tr>";
				tableData += "<td>";
				tableData += "<img src='" + c.getEtsyitem().getItenPicture() + "' width ='200' height='200' style=align:center>";
				tableData += "</td>";
				tableData += "<td>";
				tableData += c.getEtsyitem().getItemName();
				tableData += "</td>";
				tableData += "<td>";
				tableData +=  c.getQuantity();
				tableData += "</td>";
				tableData += "<td>";
				tableData +=  "$"+c.getEtsyitem().getItemPrice();
				tableData += "</td>";
				tableData += "<td>";
				tableData +=  "$"+c.getTotalprice();
				tableData += "</td>";
				tableData += "<td>";
				tableData += "<a class='btn btn-danger btn-sm' href='ReturnItem?cartId="
						+ c.getCartId() + "&itemId=" + c.getEtsyitem().getItemId()+"'>Return</a>";
				tableData += "</td>";
				tableData += "</tr>";
			}
			tableData += "</table>";
		}else
			tableData="No order history!!";
		
		return tableData;
	}
}
