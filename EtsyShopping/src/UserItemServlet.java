import model.Etsyitem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Servlet implementation class UserItemServlet
 */
@WebServlet("/UserItemServlet")
public class UserItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String sellingList;
       private String editproductform;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserItemServlet() {
        super();
        sellingList = "";
        editproductform = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sellingList = "";
		HttpSession session = request.getSession();
		List<Etsyitem> myProducts = EtsyitemDB.selectByUserName((String) session.getAttribute("userName"));
		
		if (myProducts != null) {
			sellingList += "<div class=\"container\"><br />";
			sellingList += "<table class=\"table table-striped\"><thead><tr><th>Picture</th><th>Name</th><th>Description</th><th>Price</th><th>Shipping Cost</th><th>Instock</th><th></th></tr></thead><tbody>";
			
			for (int i = 0; i < myProducts.size(); i++) {
				sellingList += "<tr><td><img src=\"" + myProducts.get(i).getItenPicture() + "\" alt=\"No image\" width=\"42\" height=\"42\"></td><td>" + myProducts.get(i).getItemName() + "</td><td>" + myProducts.get(i).getItemDescription() + "</td><td>" + formattedPrice(myProducts.get(i).getItemPrice()) + "</td><td>" + formattedPrice(myProducts.get(i).getItemShippingcost()) + "</td><td>" + myProducts.get(i).getItemInstock() + "</td><td><a href=\"UserItemServlet?itemId=" + myProducts.get(i).getItemId() + "\" class=\"btn btn-info\" role=\"button\">Edit</a></td></tr>";
			}
			
			sellingList += "</tbody></table></div>";
		} else {
			sellingList += "<div class=\"container\"><div class=\"jumbotron\">No item for sale.</div></div>";
		}
		
		if (request.getParameter("itemId") != null) {
			doPost(request, response);
		}
		
		request.setAttribute("sellingList", sellingList);
		getServletContext().getRequestDispatcher("/UserItem.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		editproductform = "";
		long itemId = Long.parseLong(request.getParameter("itemId"));
		Etsyitem currentItem = EtsyitemDB.selectById(itemId);
		editproductform = "<div class=\"container\">"
						+ "<h1>Edit your item</h1>"
						+ "<br />"
						+ "<form class=\"form-horizontal\" role=\"form\" name=\"editForm\" id=\"editForm\" onsubmit=\"return validateForm()\" action=\"Sell\" method=\"post\">"
						+ "<input type=\"hidden\" name=\"itemId\" value=\"" + itemId + "\">"
						+ "<div class=\"form-group\">"
						+ "<label class=\"control-label col-sm-2\" for=\"name\">Enter Item Name:</label>"
						+ "<div class=\"col-sm-10\">"
						+ "<input type=\"text\" class=\"form-control\" name=\"name\" id=\"name\" value=\"" + currentItem.getItemName() + "\">"
						+ "</div>"
						+ "</div>"
						+ "<div class=\"form-group\">"
						+ "<label class=\"control-label col-sm-2\" for=\"picture\">Insert picture url:</label>"
						+ "<div class=\"col-sm-10\">"
						+ "<input type=\"text\" class=\"form-control\" name=\"picture\" id=\"picture\" value=\"" + currentItem.getItenPicture() + "\">"
						+ "</div>"
						+ "</div>"
						+ "<div class=\"form-group\">"
						+ "<label class=\"control-label col-sm-2\" for=\"description\">Enter item description:</label>"
						+ "<div class=\"col-sm-10\">"
						+ "<textarea class=\"form-control\" name=\"description\" id=\"description\" value=\"" + currentItem.getItemDescription() + "\"></textarea>"
						+ "</div>"
						+ "</div>"
						+ "<div class=\"form-group\">"
						+ "<label class=\"control-label col-sm-2\" for=\"price\">Enter item price:</label>"
						+ "<div class=\"col-sm-10\">"
						+ "<input type=\"text\" class=\"form-control\" name=\"price\" id=\"price\" value=\"" + currentItem.getItemPrice() + "\">"
						+ "</div>"
						+ "</div>"
						+ "<div class=\"form-group\">"
						+ "<label class=\"control-label col-sm-2\" for=\"shipping\">Enter shipping cost:</label>"
						+ "<div class=\"col-sm-10\">"
						+ "<input type=\"text\" class=\"form-control\" name=\"shipping\" id=\"shipping\" value=\"" + currentItem.getItemShippingcost() + "\">"
						+ "</div>"
						+ "<div class=\"form-group\">"
						+ "<label class=\"control-label col-sm-2\" for=\"instock\">Instock status:</label>"
						+ "<div class=\"col-sm-10\">"
						+ "<label class=\"radio-inline\">"
					    + "<input type=\"radio\" name=\"instock\" id=\"instock\" value=\"1\">Available"
					    + "</label>"
					    + "<label class=\"radio-inline\">"
					    + "<input type=\"radio\" name=\"instock\" id=\"instock\" value=\"0\">Unavailable"
					    + "</label>"
						+ "</div>"
						+ "</div>"
						+ "</div>"
						+ "<div class=\"form-group\">"
						+ "<div class=\"col-sm-offset-2 col-sm-10\">"
						+ "<button type=\"submit\" class=\"btn btn-default\" name=\"edit\" id=\"edit\">Submit</button>"
						+ "</div>"
						+ "</div>"
						+ "</form>"
						+ "</div>";
		
		request.setAttribute("editproductform", editproductform);
		getServletContext().getRequestDispatcher("/UserItem.jsp").forward(request, response);
	}

	public static String formattedPrice(double total) {
		DecimalFormat myFormatter = new DecimalFormat("###,##0.00");
		String output = myFormatter.format(total);
		return output;
	}
}
