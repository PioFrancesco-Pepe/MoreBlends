package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.ComposizioneControl;
import control.IBeanDAO;
import control.OrdineControl;
import control.PagamentoControl;
import control.ProdottoControl;
import control.SpedizioneControl;
import model.Cart;
import model.Composizione;
import model.Ordine;
import model.Pagamento;
import model.Prodotto;
import model.Spedizione;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/checkout")
public class Checkout extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static IBeanDAO<Ordine> ordineDao = new OrdineControl();
	private static IBeanDAO<Composizione> composizioneDao = new ComposizioneControl();
	private static IBeanDAO<Spedizione> spedizioneDao = new SpedizioneControl();
	private static IBeanDAO<Pagamento> pagamentoDao = new PagamentoControl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LocalDate date = LocalDate.now();
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		Ordine beanOrdine = new Ordine();
		Composizione beanComposizione = new Composizione();

		if (cart != null) {

			beanOrdine.setDataInserimento(date.toString());
			beanOrdine.setIdCliente((int) request.getSession().getAttribute("idCliente"));

			try {
				ordineDao.doSave(beanOrdine);
				beanOrdine.setIdOrdine(OrdineControl.getLastID());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Iterator<Prodotto> iterP = cart.getProducts().iterator();
			Iterator<Integer> iterQ = cart.getQuantita().iterator();
			beanComposizione.setIdOrdine(beanOrdine.getIdOrdine());
			float somma=0;
			while (iterP.hasNext() && iterQ.hasNext()) {
				Prodotto prodotto = iterP.next();
				int quantita = iterQ.next().intValue();

				beanComposizione.setIdProdotto(prodotto.getId());
				beanComposizione.setQuantita(quantita);
		
				try {
					ProdottoControl.updateQuantita(prodotto.getId(), prodotto.getQuantita(), quantita);
					composizioneDao.doSave(beanComposizione);
					somma+=prodotto.getPrezzoVendita()*quantita;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			Spedizione s= new Spedizione();
			s.setIdOrdine(beanOrdine.getIdOrdine());
			s.setDataSpedizione(date.toString());
			s.setIdMetodoSpedizione(3);
			
			try {
				spedizioneDao.doSave(s);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Pagamento p= new Pagamento();
			p.setIdOrdine(beanOrdine.getIdOrdine());
			p.setDataPagamento(date.toString());
			//p.setTotaleOrdine(Float.parseFloat(request.getParameter("totale")));
			p.setTotaleOrdine(somma);
			p.setIdMetodoPagamento(Integer.parseInt(request.getParameter("idmetodopagamento")));
			
			try {
				pagamentoDao.doSave(p);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getSession().removeAttribute("prodotti");
		request.getSession().removeAttribute("cart");
		response.sendRedirect("/MoreBlends");

	}

}
