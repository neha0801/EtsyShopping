import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class ServletTempCart
 */
@WebServlet("/TempCart")
public class ServletTempCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletTempCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopost");
		List<Etsycart> cartList = null;
		// get the quantity from item detail page
		 String quanStr= request.getParameter("quantity");
		// temp value
		//String quanStr = "1";
		// get the itemId selected from itemdetail page
		 
		// temp value to test

		HttpSession session = request.getSession();
		String itemIdStr=request.getParameter("itemId");
		System.out.println(request.getParameter("itemId"));
		long itemId = 0;
		if(itemIdStr!=null)
			itemId = Long.parseLong(request.getParameter("itemId"));
		//int itemId= Integer.parseInt(itemIdStr);
		String str = (String) session.getAttribute("delete");
		if(str!=null){
			itemId = (long) session.getAttribute("itemId");
			cartList = (List<Etsycart>) session.getAttribute("cartList");
			for(Etsycart c : cartList){
				System.out.println("delete item " + c.getEtsyitem().getItemId());
				if(c.getEtsyitem().getItemId()==itemId){
					//session.setAttribute("itemId", null);
					cartList.remove(c);
					break;
				}
			}
			if(cartList.isEmpty()){
				cartList=null;
			}
			session.setAttribute("delete",null);
		}
		// get user from the session
		Etsyuser user = (Etsyuser) session.getAttribute("user");		
		/* user.setCredit(0);
		  user.setEmail("fdkjghfdkj");
		  user.setName("fnhdjkhgf"); 
		  user.setPassword("fjd");
		 user.setUserId(4);*/
		// create a temp cart
		Etsycart cObj = new Etsycart();
		Etsyitem itemObj = new Etsyitem();
		itemObj = DBUtil.getSelectedItem(itemId);
		if (quanStr != null) {
			int quantity = Integer.parseInt(quanStr);
			cObj.setQuantity(quantity);
			Double totalPrice = quantity * itemObj.getItemPrice();
			cObj.setTotalprice(totalPrice);
			cObj.setEtsyitem(itemObj);
			// for temp cart user id is null
			cObj.setEtsyuser(user);
			// System.out.println("userid" + user.getUserId());
			System.out.println(cartList);
			cObj.setCartStatus(0);
			if (session.getAttribute("cartList") == null) {
				cartList = DBUtil.getUserCart(user);
				//cartList.add(cObj);
			} else {
				cartList = (List<Etsycart>) session.getAttribute("cartList");
				//cartList.add(cObj);
			}
			for (Etsycart c : cartList) {
					if (c.getEtsyitem().getItemId() == (cObj.getEtsyitem()
							.getItemId())) {
						c.setQuantity(cObj.getQuantity() + c.getQuantity());
						c.setTotalprice(c.getQuantity()
								* c.getEtsyitem().getItemPrice());
						break;
					} else {
						cartList.add(cObj);
						break;
					}
				
			}
			if(cartList.isEmpty()){
				cartList.add(cObj);
			}

			session.setAttribute("cartList", cartList);
		}

		String cartData = showCart(cartList);
		request.setAttribute("cartData", cartData);
		String buttons = "";
		System.out.println(user);
		if (user != null) {
			buttons += "<br><a href='CheckoutCart' class='btn pull-right btn-primary btn-lg'>Checkout</a>";
		} else
			buttons += "<br><a href='Login.jsp' class='btn pull-right btn-primary btn-lg'>CheckOut</a>";
		buttons += "<a href='EditCart?empty=y'class='btn pull-left btn-warning btn-lg'>Empty your cart</a>";
		// Long count = DBUtil.itemsInCart(user);
		// System.out.println("count " + count);
		request.setAttribute("buttons", buttons);
		session.setAttribute("user", user);
		// request.setAttribute("count", count);
		getServletContext().getRequestDispatcher("/TempCart.jsp").forward(
				request, response);
	}

	private String showCart(List<Etsycart> cartList) {

		Double checkoutPrice = 0.0;
		String tableData = "";

		if (cartList != null) {
			tableData += "<div><table class='table table-bordered table-striped'>";
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
			tableData += "<th>";
			tableData += "";
			tableData += "</th>";
			tableData += "</thead>";
			tableData += "</tr>";

			for (Etsycart c : cartList) {
				tableData += "<tr>";
				tableData += "<td>";
				tableData += "<img src='" + c.getEtsyitem().getItenPicture()
						+ "' width ='200' height='200' style=align:center>";
				tableData += "</td>";
				tableData += "<td>";
				tableData += c.getEtsyitem().getItemName();
				tableData += "</td>";
				tableData += "<td>";
				tableData += c.getQuantity();
				tableData += "</td>";
				tableData += "<td>";
				tableData += "$" + c.getEtsyitem().getItemPrice();
				tableData += "</td>";
				tableData += "<td>";
				tableData += "$" + c.getEtsyitem().getItemShippingcost();
				tableData += "</td>";
				tableData += "<td>";
				c.setTotalprice(c.getTotalprice() + c.getEtsyitem().getItemShippingcost());
				tableData += "$" + (c.getTotalprice());
				tableData += "</td>";
				tableData += "<td>";
				tableData += "<a class='btn btn-danger btn-sm' href='EditCart?itemId="
						+ c.getEtsyitem().getItemId() + "'>Delete</a>";
				tableData += "</td>";
				tableData += "</tr>";

				checkoutPrice += c.getTotalprice();
			}
			// checkoutPrice += user.getCredit();
			tableData += "</table></div>";
			tableData += "<label class='pull-right'>Tax: $"
					+ (checkoutPrice * 0.06) + "</label><br><br>";
			tableData += "<label class='btn pull-right btn-info btn-sm'>Total Price: $"
					+ (checkoutPrice + (checkoutPrice * 0.06))
					+ "</label><br><br>";
		} else
			tableData = "Your Cart is empty";

		return tableData;
	}

}
