package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.IBeanDAO;
import control.ProdottoControl;
import model.Prodotto;

/**
 * Servlet implementation class GetProdotto
 */
@WebServlet("/getProdotto")
public class GetProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static IBeanDAO<Prodotto> productDao= new ProdottoControl();
	
	public GetProdotto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{		
		String catalogo="/pages/catalogo.jsp";
			if(Integer.parseInt(request.getParameter("current")) == 3)
			{
				String marca=(String)request.getSession().getAttribute("marca");
				String search=(String)request.getSession().getAttribute("search");	
				String sistema=(String) request.getSession().getAttribute("sistema");
				
				if(search!=null && marca!=null && sistema!=null)
				{	
					Collection<Prodotto> model;
					model = ProdottoControl.loadSearchProduct(search,marca,sistema);
					request.setAttribute("prodottiSearch", model);
					RequestDispatcher dispatcher = this.getServletContext().
					getRequestDispatcher(catalogo);
					dispatcher.forward(request, response);
				}
				else
				{
					Collection<Prodotto> model;
					model = ProdottoControl.loadSearchProduct("","0","0");
					request.setAttribute("prodottiSearch", model);
					RequestDispatcher dispatcher = this.getServletContext().
					getRequestDispatcher(catalogo);
					dispatcher.forward(request, response);
				}
			}
			else if(Integer.parseInt(request.getParameter("current")) == 2)
			{
				List<Prodotto> model = ProdottoControl.load();
				request.setAttribute("prodotti", model);
				RequestDispatcher dispatcher = this.getServletContext().
						getRequestDispatcher(catalogo);
				dispatcher.forward(request, response);
			}
			else if(Integer.parseInt(request.getParameter("current")) == 4)
			{
				Collection<Prodotto> model;
				try {
					model = productDao.doRetrieveAll("");
					request.setAttribute("prodotti", model);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = this.getServletContext().
						getRequestDispatcher("/pages/pageProduct.jsp");
					dispatcher.forward(request, response);
				
			}
			else if(Integer.parseInt(request.getParameter("current")) == 1)
			{
				Collection<Prodotto> model;
				try {
					model = productDao.doRetrieveAll("");
					request.setAttribute("prodotti", model);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
