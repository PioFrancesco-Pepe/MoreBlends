package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

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
import model.Locazione;
import model.Prodotto;
import model.Cliente;


@WebServlet("/editProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	static IBeanDAO<Prodotto> productDao = new ProdottoControl();
	
    public EditProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente=(Cliente)request.getSession().getAttribute("currentUtente");
		if(cliente!=null ) {
			if(cliente.isAdmin()==1) {
		Prodotto p= (Prodotto)request.getSession().getAttribute("ProductEdit");
		Locazione l= new Locazione();
		
		p.setNome(request.getParameter("NomeProdotto"));
		p.setDescrizione(request.getParameter("desc"));
		p.setDescrizioneAmpia(request.getParameter("descA"));
		p.setCosto(Float.parseFloat(request.getParameter("costo")));
		p.setPrezzoVendita(Float.parseFloat(request.getParameter("pv")));
		p.setQuantita(Integer.parseInt(request.getParameter("q")));
		p.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
		p.setIdSottoCategoria(Integer.parseInt(request.getParameter("sottocategoria")));
		
		try {
			boolean b=ProdottoControl.updateProdotto(p);
				System.out.println(b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		l.setIdProdotto(p.getId());
		l.setIdMagazzino(1);
		l.setQuantita(p.getQuantita());
		
		
		
		try {
			boolean b=LocazioneControl.updateLocazione(l);
			System.out.println(b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (Part part : request.getParts()) {
			String fileName = part.getSubmittedFileName();
			if (fileName != null && !fileName.equals("")) {
				try {
					PhotoControl.updatePhoto(""+p.getId(), part.getInputStream());
				} catch (SQLException sqlException) {
					System.out.println(sqlException);
				}
			}
		}
		
		try {
			Collection<Prodotto> prodotti=productDao.doRetrieveAll("");
			request.getSession().removeAttribute("prodottiV2");
			request.getSession().setAttribute("prodottiV2",prodotti);
			request.getSession().removeAttribute("ProductEdit");
			response.sendRedirect("./admin/viewProduct.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		}else
			response.sendRedirect("");}
		
	}
}
