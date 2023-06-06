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
			if(Integer.parseInt(request.getParameter("current")) == 3)
			{
				String marca=(String)request.getSession().getAttribute("marca");
				String search=(String)request.getSession().getAttribute("search");	
				if(search!=null && marca!=null)
				{	
					List<Prodotto> model;
					if(!(marca.equals("0")))
						model = ProdottoControl.loadSearchProduct(search,marca);
					else
						model = ProdottoControl.loadSearchProduct(search);
					request.setAttribute("prodottiSearch", model);
					RequestDispatcher dispatcher = this.getServletContext().
					getRequestDispatcher("/pages/catalogo.jsp");
					dispatcher.forward(request, response);
				}
				else
				{
					if(search==null)
						search="";
					List<Prodotto> model;
					model = ProdottoControl.loadSearchProduct(search);
					request.setAttribute("prodottiSearch", model);
					RequestDispatcher dispatcher = this.getServletContext().
					getRequestDispatcher("/pages/catalogo.jsp");
					dispatcher.forward(request, response);
				}
					
			}
			else if(Integer.parseInt(request.getParameter("current")) == 2)
			{
				List<Prodotto> model = ProdottoControl.load();
				request.setAttribute("prodotti", model);
				RequestDispatcher dispatcher = this.getServletContext().
						getRequestDispatcher("/pages/catalogo.jsp");
				dispatcher.forward(request, response);
			}
			else if(Integer.parseInt(request.getParameter("current")) == 1){
			List<Prodotto> model = ProdottoControl.load();
			request.setAttribute("prodotti", model);
			RequestDispatcher dispatcher = this.getServletContext().
					getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
