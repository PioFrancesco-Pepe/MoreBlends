package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.IBeanDAO;
import control.ProdottoControl;
import model.Cart;
import model.Prodotto;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static IBeanDAO<Prodotto> productDao = new ProdottoControl();

	public CartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}

		String action = (String) request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("addC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					int quantita = Integer.parseInt(request.getParameter("quantita"));
					cart.addProduct(productDao.doRetrieveByKey(id), quantita,"addC");
				} else if (action.equalsIgnoreCase("deleteC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					int quantita = Integer.parseInt(request.getParameter("quantita"));
					cart.deleteProduct(productDao.doRetrieveByKey(id),quantita);
				}else if(action.equalsIgnoreCase("updateC")){
					int id = Integer.parseInt(request.getParameter("id"));
					int quantita = Integer.parseInt(request.getParameter("quantita"));
					cart.updateCart(productDao.doRetrieveByKey(id),quantita,"updateC");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);
		
		if(action.equalsIgnoreCase("addC")) {
			request.getSession().setAttribute("popup",1);
			response.sendRedirect("./pages/pageProduct.jsp");
		}
		else if(action.equalsIgnoreCase("deleteC") || action.equalsIgnoreCase("updateC"))
			response.sendRedirect("./pages/cart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
