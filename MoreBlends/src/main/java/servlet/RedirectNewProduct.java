package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RedirectNewProduct
 */
@WebServlet("/redirectNewProduct")
public class RedirectNewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RedirectNewProduct() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().setAttribute("idproduct",request.getParameter("id"));
		response.sendRedirect("./pages/pageProduct.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
