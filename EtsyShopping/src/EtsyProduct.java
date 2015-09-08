import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Etsyitem;

/**
 * Servlet implementation class ProcessProduct
 */
@WebServlet("/EtsyProduct")
public class EtsyProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String productListMsg;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EtsyProduct() {
        super();
        productListMsg = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productListMsg = "";
		List<Etsyitem> productList = EtsyitemDB.selectByInstock(1);
		
		if (!productList.isEmpty()) {
			for (int i = 0; i < productList.size(); i++) {
				productListMsg += "<tr><td width=\"10%\"><img src=\"" + productList.get(i).getItenPicture() + "\" alt=\"No image\" width=\"42\" height=\"42\"></td><td width=\"50%\"><a href=\"EtsyDetails?itemId=" + productList.get(i).getItemId() + "\">" + productList.get(i).getItemName() + "</a></td><td width=\"40%\">" + formattedPrice(productList.get(i).getItemPrice()) + "</td></tr>";
			}
		}
		request.setAttribute("productListMsg", productListMsg);
		getServletContext().getRequestDispatcher("/EtsyitemList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productListMsg = "";
		String keyword = request.getParameter("keyword");
		List<Etsyitem> result = new ArrayList<Etsyitem>();
		if ((keyword != null) && (!keyword.equals(""))) {
			result = EtsyitemDB.selectByKeyword(keyword);
		} else {
			result = EtsyitemDB.selectByInstock(1);
		}
		
		if (!result.isEmpty()) {
			for (int i = 0; i < result.size(); i++) {
				productListMsg += "<tr><td width=\"60%\"><a href=\"EtsyDetails?itemId=" + result.get(i).getItemId() + "\">" + result.get(i).getItemName() + "</a></td><td width=\"40%\">" + formattedPrice(result.get(i).getItemPrice()) + "</td></tr>";
			}
		}
	}
	
	public static String formattedPrice(double total) {
		DecimalFormat myFormatter = new DecimalFormat("###,##0.00");
		String output = myFormatter.format(total);
		return output;
	}

}