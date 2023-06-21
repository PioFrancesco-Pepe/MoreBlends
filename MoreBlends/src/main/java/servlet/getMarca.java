package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.CategoriaControl;
import control.IBeanDAO;
import model.Categoria;


/**
 * Servlet implementation class getMarca
 */
@WebServlet("/getMarca")
public class getMarca extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static IBeanDAO<Categoria> categoriaDao= new CategoriaControl();
	
    public getMarca() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Collection<Categoria> model=null;
		try {
			model = categoriaDao.doRetrieveAll("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("MarcaProdotti", model);
		RequestDispatcher dispatcher = this.getServletContext().
			getRequestDispatcher("/pages/catalogo.jsp");
		dispatcher.forward(request, response);
	}	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
