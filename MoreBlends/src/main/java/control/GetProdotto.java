package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Prodotto;

/**
 * Servlet implementation class GetProdotto
 */
@WebServlet("/getProdotto")
public class GetProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetProdotto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{		
			List<Prodotto> model = ProdottoControl.load();
			request.setAttribute("prodotti", model);
			
			RequestDispatcher dispatcher = this.getServletContext().
					getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
