package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import control.IBeanDAO;
import control.LocazioneControl;
import control.PhotoControl;
import control.ProdottoControl;
import control.StoricoPrezziControl;
import model.Locazione;
import model.Prodotto;
import model.StoricoPrezzi;
import model.Cliente;


@WebServlet("/editProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static IBeanDAO<Prodotto> productDao = new ProdottoControl();
	private static IBeanDAO<StoricoPrezzi> spDao = new StoricoPrezziControl();

	public EditProduct() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cliente cliente = (Cliente) request.getSession().getAttribute("currentUtente");
		if (cliente != null) {
			if (cliente.isAdmin() == 1) {
				Prodotto p = (Prodotto) request.getSession().getAttribute("ProductEdit");
				Locazione l = new Locazione();

				try {
					if ((p.getPrezzoVendita() != Float.parseFloat(request.getParameter("pv")))
							|| (p.getCosto() != Float.parseFloat(request.getParameter("costo")))) {
						LocalDate date = LocalDate.now();
						Iterator<StoricoPrezzi> iterSP = spDao.doRetrieveAll("").iterator();
						while (iterSP.hasNext()) {
							StoricoPrezzi temp = iterSP.next();
							if (temp.getDataFine() == null && temp.getIdProdotto() == p.getId()) {
								temp.setDataFine(date.toString());
								StoricoPrezziControl.updateSP(temp);
								StoricoPrezzi sp = new StoricoPrezzi();
								sp.setDataInizio(date.toString());
								sp.setCosto(Float.parseFloat(request.getParameter("costo")));
								sp.setPv(Float.parseFloat(request.getParameter("pv")));
								sp.setIdProdotto(p.getId());
								sp.setDataFine(null);
								spDao.doSave(sp);
							}
						}

					}

					p.setNome(request.getParameter("NomeProdotto"));
					p.setDescrizione(request.getParameter("desc"));
					p.setDescrizioneAmpia(request.getParameter("descA"));
					p.setCosto(Float.parseFloat(request.getParameter("costo")));
					p.setPrezzoVendita(Float.parseFloat(request.getParameter("pv")));
					p.setQuantita(Integer.parseInt(request.getParameter("q")));
					p.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
					p.setIdSottoCategoria(Integer.parseInt(request.getParameter("sottocategoria")));

					ProdottoControl.updateProdotto(p);

					l.setIdProdotto(p.getId());
					l.setIdMagazzino(1);
					l.setQuantita(p.getQuantita());

					LocazioneControl.updateLocazione(l);

					for (Part part : request.getParts()) {
						String fileName = part.getSubmittedFileName();
						if (fileName != null && !fileName.equals("")) {
							PhotoControl.updatePhoto("" + p.getId(), part.getInputStream());
						}
					}

					Collection<Prodotto> prodotti = productDao
							.doRetrieveAll(" p.idcategoria,p.idsottocategoria,p.idprodotto ASC");
					request.getSession().removeAttribute("prodottiV2");
					request.getSession().setAttribute("prodottiV2", prodotti);
					request.getSession().removeAttribute("ProductEdit");
					request.getSession().setAttribute("status", "Prodotto aggiornato");
					response.sendRedirect("./admin/viewProduct.jsp");
				} catch (SQLException e) {
					request.getSession().setAttribute("status", "Riprova a modificare il prodotto.");
					response.sendRedirect("./admin/viewProduct.jsp");
				}

			} else
				response.sendRedirect(this.getServletContext().getContextPath());
		}

	}
}
