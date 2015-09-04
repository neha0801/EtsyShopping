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
@WebServlet("/ServletTempCart")
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

		// get the quantity from item detail page
		// String quanStr= request.getParameter("quantity");
		// temp value
		String quanStr = "1";
		// get the itemId selected from itemdetail page
		// int itemId = request.getParameter("itemId");
		// temp value to test
		int itemId = 1;
		HttpSession session = request.getSession();

		// get user from the session
		// Etsyuser user = (Etsyuser) session.getAttribute("user");
		Etsyuser user = new Etsyuser();
		/*
		 * user.setCredit(0); user.setEmail("fdkjghfdkj");
		 * user.setName("fnhdjkhgf"); user.setPassword("fjd");
		 * user.setUserId(0);
		 */
		// create a temp cart
		Etsycart cObj = new Etsycart();
		List<Etsycart> cartList = new ArrayList<Etsycart>();

		Etsyitem itemObj = new Etsyitem();
		itemObj = DBUtil.getSelectedItem(itemId);
		if (quanStr != null) {
			int quantity = Integer.parseInt(quanStr);
			cObj.setQuantity(quantity);
			Double totalPrice = quantity * itemObj.getItemPrice();
			cObj.setTotalprice(totalPrice);
			cObj.setEtsyitem(itemObj);
			// for temp cart user id is null
			// cObj.setEtsyuser(user);
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
		if (user != null) {
			buttons += "<br><a href='CheckoutCart' class='btn pull-right btn-primary btn-lg'>Checkout</a>";
		} else
			buttons += "<br><a href='UserProfile.jsp' class='btn pull-right btn-primary btn-lg'>CheckOut</a>";
		buttons += "<a href='EditCart?empty=y'class='btn pull-left btn-warning btn-lg'>Empty your cart</a>";
		// Long count = DBUtil.itemsInCart(user);
		// System.out.println("count " + count);
		request.setAttribute("buttons", buttons);
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
				tableData += "$" + c.getTotalprice();
				tableData += "</td>";
				tableData += "<td>";
				tableData += "<a class='btn btn-danger btn-sm' href='EditCart?prodId="
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