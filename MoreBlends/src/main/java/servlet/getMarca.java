package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.ProdottoControl;


/**
 * Servlet implementation class getMarca
 */
@WebServlet("/getMarca")
public class getMarca extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public getMarca() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> model = ProdottoControl.loadMarca();
		request.setAttribute("MarcaProdotti", model);
		RequestDispatcher dispatcher = this.getServletContext().
			getRequestDispatcher("/pages/catalogo.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
