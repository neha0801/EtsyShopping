

import java.io.IOException;
import java.util.List;
import java.util.Random;

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
 * Servlet implementation class ServletOrderConfirm
 */
@WebServlet("/OrderConfirm")
public class ServletOrderConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletOrderConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("order confirm do get");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		Etsyuser user = (Etsyuser) session.getAttribute("user");
		String message = "";
		System.out.println("fvdsjgfd");
			Random r = new Random();
			int confirmationNumber = 1+ r.nextInt(1000000);

			message += "<h1> Your Order confirmation number is " + confirmationNumber + "</h1>";
			message += showCart(user);
			System.out.println("user id" + user.getUserId());
			DBUtil.updateStatus(1,user);

			
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/OrderConfirmation.jsp").forward(request, response);

	}
	
	private String showCart(Etsyuser user){
		List<Etsycart> cartList = DBUtil.getUserCart(user);
		
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
			tableData += "</thead>";
			tableData += "</tr>";
			System.out.println();
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
				tableData += "</tr>";
			}
			tableData += "</table>";
		}else
			tableData="No items are ordered";
		tableData+="<div><a href='OrderHistory' class='btn pull-left btn-primary'>Order History</a></div>";
		return tableData;
	}

	


}
