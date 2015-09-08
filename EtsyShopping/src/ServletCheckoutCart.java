

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.Etsycart;
import model.Etsyuser;

/**
 * Servlet implementation class ServletCheckoutCart
 */
@WebServlet("/CheckoutCart")
public class ServletCheckoutCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCheckoutCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get of cart checkout");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("checkout do post");
		HttpSession session = request.getSession();		
		Etsyuser user = (Etsyuser) session.getAttribute("user");
		//request.setAttribute("user", user.getUserName());
		//String mesg="";
		List<Etsycart> cartList = (List<Etsycart>) session.getAttribute("cartList");
		String message="";
		//message = "<h2 style='color:red'>"+mesg+ "</h2>";
		message += showCart(cartList);
		request.setAttribute("message", message);
		for(Etsycart c:cartList){
			System.out.println(c.getCartId() + " " + c.getQuantity() + " " + c.getEtsyitem().getItemName());
			DBUtil.insert(c);
		}
		
		getServletContext().getRequestDispatcher("/CheckoutCart.jsp").forward(request, response);
		session.setAttribute("cartList", null);
	}
	
	private String showCart(List<Etsycart> cartList){
		
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
			tableData += "Shiping Cost";
			tableData += "</th>";
			tableData += "<th>";
			tableData += "TotalPrice";
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
				tableData += "$" + c.getEtsyitem().getItemShippingcost();
				tableData += "</td>";
				tableData += "<td>";
				tableData +=  "$"+c.getTotalprice();
				tableData += "</td>";
				tableData += "</tr>";
			}
			tableData += "</table>";
			
			tableData+= "<a href='OrderConfirm'class='btn pull-right btn-warning' >Place your order</a>";
		}else
			tableData="Your Cart is empty";

		return tableData;
	}

}
