package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.IBeanDAO;
import model.Categoria;
import model.Cliente;
import control.CategoriaControl;


/**
 * Servlet implementation class GetCategoria
 */
@WebServlet("/getCategoria")
public class GetCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static IBeanDAO<Categoria> categoriaDao = new CategoriaControl();
	
    public GetCategoria() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente c = (Cliente) request.getSession().getAttribute("currentUtente");

		if (c != null && c.isAdmin() == 1) {
			try {
				request.getSession().setAttribute("Categoria", categoriaDao.doRetrieveAll(""));
				if(request.getParameter("admin")!=null && request.getParameter("admin").equals("1"))
					response.sendRedirect("../admin/newProdotto.jsp");
				else if(request.getParameter("admin")!=null && request.getParameter("admin").equals("2"))
					response.sendRedirect("../admin/viewProduct.jsp");
			} catch (SQLException e) {
				response.sendRedirect(this.getServletContext().getContextPath());
			}
			
		}
			if (request.getParameter("common") != null && request.getParameter("common").equals("1")) {
				try {
					request.setAttribute("Categoria", categoriaDao.doRetrieveAll(""));
				} catch (SQLException e) {
					response.sendRedirect(this.getServletContext().getContextPath());
				}
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/pages/catalogo.jsp");
				dispatcher.forward(request, response);
			}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
