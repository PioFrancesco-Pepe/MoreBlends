package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.ComposizioneControl;
import control.IBeanDAO;
import control.OrdineControl;
import control.ProdottoControl;
import model.Composizione;
import model.Ordine;
import model.Prodotto;
/**
 * Servlet implementation class Ordine
 */
@WebServlet("/currentOrdine")
public class CurrentOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static IBeanDAO<Prodotto> productDao= new ProdottoControl();
	static IBeanDAO<Ordine> ordineDao = new OrdineControl();
	static IBeanDAO<Composizione> composizioneDao = new ComposizioneControl();
	
    public CurrentOrdine() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int code=Integer.parseInt(request.getParameter("code"));
		
		try {
			Collection<Prodotto> products = new LinkedList<>();
			Collection<Integer> quantita= new LinkedList<>();
			Collection<Composizione> c = composizioneDao.doRetrieveAll("");
			Iterator<Composizione> comp= c.iterator();
			while(comp.hasNext())
			{
				Composizione temp = comp.next();
				if(temp.getIdOrdine() == code)
				{
					products.add(productDao.doRetrieveByKey(temp.getIdProdotto()));
					quantita.add(temp.getQuantita());
				}
			}
			request.getSession().setAttribute("prodottiOrdine", products);
			request.getSession().setAttribute("quantitaOrdine", quantita);
			response.sendRedirect("./admin/complessivoOrdine.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}