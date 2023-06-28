package servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.ProdottoControl;
import model.Cliente;
/**
 * Servlet implementation class SearchAdmin
 */
@WebServlet("/searchAdmin")
public class SearchAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public SearchAdmin() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente c = (Cliente) request.getSession().getAttribute("currentUtente");
		if (c == null || c.isAdmin() == 0)
			response.sendRedirect(this.getServletContext().getContextPath());
		else {
			String marca=request.getParameter("marca");
			String search=request.getParameter("search");	
			String sistema=request.getParameter("sistema");
			
			if ((search!=null && marca!=null && sistema!=null)) {
				Collection<?> prodotti = ProdottoControl.loadSearchProduct(search,marca,sistema);
				request.getSession().setAttribute("cercaP", prodotti);
				response.sendRedirect("./admin/viewProduct.jsp");
			} else {
				Collection<?> prodotti = ProdottoControl.loadSearchProduct("", "0", "0");
				request.getSession().setAttribute("cercaP", prodotti);
				response.sendRedirect("./admin/viewProduct.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
