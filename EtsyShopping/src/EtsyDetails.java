import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		details = "";
		long itemId = Long.parseLong(request.getParameter("itemId"));

		Etsyitem item = EtsyitemDB.selectById(itemId);

		details += "<div class=\"container\"><div class=\"jumbotron\"><h4>Item Name: "
				+ item.getItemName()
				+ "</h4><h4>Description: <br />"
				+ item.getItemDescription()
				+ "</h4><h4>Price: $"
				+ formattedPrice(item.getItemPrice())
				+ "</h4><h4>Shipping Cost: $"
				+ formattedPrice(item.getItemShippingcost()) + "</div></div>";

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

	}

	public static String formattedPrice(double total) {
		DecimalFormat myFormatter = new DecimalFormat("###,##0.00");
		String output = myFormatter.format(total);
		return output;
	}

}