import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Etsyitem;

/**
 * Servlet implementation class Details
 */
@WebServlet("/EtsyDetails")
public class EtsyDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String details;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EtsyDetails() {
		super();
		details = "";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		details = "";
		long itemId = Long.parseLong(request.getParameter("itemId"));
		//session.setAttribute("itemId", itemId);
		// boolean exist = false;
		// int cartIndex = 0;

		Etsyitem item = EtsyitemDB.selectById(itemId);

		details += "<div class=\"container\"><div class=\"jumbotron\"><h4>Item Name: "
				+ item.getItemName()
				+ "</h4><h4>Description: <br />"
				+ item.getItemDescription()
				+ "</h4><h4>Price: $"
				+ formattedPrice(item.getItemPrice()) + "</h4></div></div>";

		// if (cart != null) {
		// for (int i = 0; i < cart.size(); i++) {
		// if (cart.get(i).getPid() == productid) {
		// cartIndex = i;
		// exist = true;
		// }
		// }
		// }

		//if (session.getAttribute("user") != null) {
			// if (exist) {
			// details +=
			// "<div class=\"container\"><br /><form class=\"form-horizontal\" role=\"form\" name=\"singleProd\" id=\"singleProd\" action=\"EtsyDetails\" method=\"post\">"
			// + "<input type=\"hidden\" name=\"itemId\" value=\"" + itemId +
			// "\">"
			// + "<label class=\"control-label col-sm-2\" for=\"item" + itemId +
			// "\">Quantity:</label>"
			// + "<input type=\"text\" name=\"item" + itemId + "\" id=\"item" +
			// itemId + "\" value=\"" + cart.get(cartIndex).getPquantity() +
			// "\">"
			// +
			// "<button type=\"submit\" class=\"btn btn-default\" name=\"singleUpd\" id=\"singleUpd\">Add to Cart</button></form>";
			// } else {
			details += "<div class=\"container\"><br /><form class=\"form-horizontal\" role=\"form\" name=\"singleProd\" id=\"singleProd\" action=\"TempCart\" method=\"post\">"
					+ "<input type=\"hidden\" name=\"itemId\" value=\""
					+ itemId
					+ "\">"
					+ "<label class=\"control-label col-sm-2\" for=\"item"
					+ itemId
					+ "\">Quantity:</label>"
					+ "<input type=\"number\" name=\"quantity"
					+ "\">"
					+ "<button type=\"submit\" class=\"btn btn-default\" name=\"singleUpd\" id=\"singleUpd\">Add to Cart</button></form>";
			// }
		//}

		request.setAttribute("details", details);
		getServletContext().getRequestDispatcher("/EtsyItemDetail.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// HttpSession session = request.getSession();
		// List<Cart> cart = (List<Cart>) session.getAttribute("cartCheckout");
		// boolean exist = false;
		// long productid = Long.parseLong(request.getParameter("productid"));
		// session.setAttribute("productid", productid);
		//
		// if (request.getParameter("singleUpd") != null) {
		// for (int i = 0; i < cart.size(); i++) {
		// if (cart.get(i).getPid() == productid) {
		// cart.get(i).setPquantity(Integer.parseInt(request.getParameter("product"
		// + productid)));
		// exist = true;
		// }
		// }
		//
		// if (!exist) {
		// Product product = ProductDB.selectById(productid);
		// Cart newItem = new Cart(UserDB.selectByEmail((String)
		// session.getAttribute("email")), product.getId(), product.getName(),
		// product.getPrice(), Integer.parseInt(request.getParameter("product" +
		// productid)));
		// cart.add(newItem);
		// }
		//
		// session.setAttribute("cartCheckout", cart);
		// response.sendRedirect("/SuperShoppingCart/ProcessProduct");
		// }
	}

	public static String formattedPrice(double total) {
		DecimalFormat myFormatter = new DecimalFormat("###,##0.00");
		String output = myFormatter.format(total);
		return output;
	}

}