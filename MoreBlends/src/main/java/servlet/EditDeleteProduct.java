package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.IBeanDAO;
import control.ProdottoControl;
import control.CategoriaControl;
import control.SottoCategoriaControl;
import model.Prodotto;
import model.Categoria;
import model.SottoCategoria;


@WebServlet("/editDeleteProduct")
public class EditDeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static IBeanDAO<Prodotto> productDao= new ProdottoControl();
	static IBeanDAO<Categoria> categoriaDao= new CategoriaControl();
	static IBeanDAO<SottoCategoria> sottoCategoriaDao= new SottoCategoriaControl();
	
	
    public EditDeleteProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		try {
		if(action!=null) {
			if(action.compareTo("delete")==0)
			{
				int code=Integer.parseInt(request.getParameter("code"));
				boolean b = productDao.doDelete(code);
				if(b)
					request.getSession().setAttribute("prodotti",productDao.doRetrieveAll(""));
				response.sendRedirect("./admin/viewProduct.jsp");
			}
			else if(action.compareTo("edit")==0)
			{
				int code= Integer.parseInt(request.getParameter("code"));
				Prodotto p=productDao.doRetrieveByKey(code);
				request.getSession().setAttribute("ProductEdit", p);
				request.getSession().setAttribute("Categoria",categoriaDao.doRetrieveAll(""));
				request.getSession().setAttribute("SottoCategoria",sottoCategoriaDao.doRetrieveAll(""));
				request.getSession().setAttribute("editC",categoriaDao.doRetrieveByKey(p.getIdCategoria()));
				request.getSession().setAttribute("editSC",sottoCategoriaDao.doRetrieveByKey(p.getIdSottoCategoria()));
				response.sendRedirect("./admin/editProduct.jsp");
			}
		}
	}catch (SQLException e) {
		System.out.println("Error:" + e.getMessage());
	}

}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
