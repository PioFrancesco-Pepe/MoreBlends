package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.IBeanDAO;
import model.Categoria;
import control.CategoriaControl;


/**
 * Servlet implementation class GetCategoria
 */
@WebServlet("/getCategoria")
public class GetCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static IBeanDAO<Categoria> categoriaDao = new CategoriaControl();
	
    public GetCategoria() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getSession().setAttribute("Categoria", categoriaDao.doRetrieveAll(""));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("../admin/newProdotto.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
