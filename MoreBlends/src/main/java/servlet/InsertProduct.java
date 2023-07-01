package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

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
import model.Prodotto;
import model.StoricoPrezzi;
import model.Locazione;

@WebServlet("/InsertProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class InsertProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private static IBeanDAO<Prodotto> productDao = new ProdottoControl();
	private static IBeanDAO<Locazione> locazioneDao = new LocazioneControl();
	private static IBeanDAO<StoricoPrezzi> spDao= new StoricoPrezziControl();
	
	public InsertProduct() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Prodotto p = new Prodotto();
		Locazione l = new Locazione();
		StoricoPrezzi sp = new StoricoPrezzi();
		LocalDate date = LocalDate.now();
	
		p.setNome(request.getParameter("NomeProdotto"));
		p.setDescrizione(request.getParameter("desc"));
		p.setDescrizioneAmpia(request.getParameter("descA"));
		p.setDatainserimento(date.toString());
		p.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
		p.setIdSottoCategoria(Integer.parseInt(request.getParameter("sottocategoria")));
		
		try {
			productDao.doSave(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sp.setCosto(Float.parseFloat(request.getParameter("costo")));
		sp.setPv(Float.parseFloat(request.getParameter("pv")));
		sp.setDataInizio(date.toString());
		sp.setIdProdotto(ProdottoControl.getLastID());
		
		try {
			spDao.doSave(sp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		l.setIdProdotto(ProdottoControl.getLastID());
		l.setIdMagazzino(1);
		l.setQuantita(Integer.parseInt(request.getParameter("q")));
		
		try {
			locazioneDao.doSave(l);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (Part part : request.getParts()) {
			String fileName = part.getSubmittedFileName();
			if (fileName != null && !fileName.equals("")) {
				try {
					PhotoControl.updatePhoto(""+l.getIdProdotto(), part.getInputStream());
				} catch (SQLException sqlException) {
					System.out.println(sqlException);
				}
			}
		}
		response.sendRedirect("/MoreBlends");
	}

}
