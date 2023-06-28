package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.CategoriaControl;
import control.IBeanDAO;
import control.SottoCategoriaControl;
import model.Categoria;
import model.Cliente;
import model.SottoCategoria;

/**
 * Servlet implementation class InsertMarca
 */
@WebServlet("/InsertMarcaORSistema")
public class InsertMarcaORSistema extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static IBeanDAO<Categoria> marcaDao = new CategoriaControl();
	private static IBeanDAO<SottoCategoria> sistemaDao = new SottoCategoriaControl();

	public InsertMarcaORSistema() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("currentUtente") == null)
			response.sendRedirect(this.getServletContext().getContextPath());
		if (request.getSession().getAttribute("currentUtente") != null) {
			Cliente current = (Cliente) request.getSession().getAttribute("currentUtente");
			if (current.isAdmin() == 1) {
				String add = (String) request.getSession().getAttribute("add");
				if (add.equalsIgnoreCase("M")) {
					Categoria temp = new Categoria();
					temp.setNomeCategoria(request.getParameter("nameM"));
					temp.setDescCategoria(request.getParameter("descM"));

					try {
						if (check(marcaDao.doRetrieveAll(""), add, temp)) {
							request.getSession().setAttribute("errore", "Marca già presente");
							response.sendRedirect("./admin/newMorS.jsp?action=M");
						} else {
							marcaDao.doSave(temp);
							request.getSession().setAttribute("Categoria", marcaDao.doRetrieveAll(""));
							request.getSession().setAttribute("errore", "Marca inserita");
							response.sendRedirect("./admin/newMorS.jsp?action=M");
						}

					} catch (SQLException e) {
						response.sendRedirect(this.getServletContext().getContextPath());
					}

				} else if (add.equalsIgnoreCase("S")) {
					SottoCategoria temp = new SottoCategoria();
					temp.setNomeSottoCategoria(request.getParameter("nameS"));

					try {
						if (check(sistemaDao.doRetrieveAll(""), add, temp)) {
							request.getSession().setAttribute("errore", "Sistema già presente");
							response.sendRedirect("./admin/newMorS.jsp?action=S");
						} else {
							sistemaDao.doSave(temp);
							request.getSession().setAttribute("SottoCategoria", marcaDao.doRetrieveAll(""));
							request.getSession().setAttribute("errore", "Sistema aggiunto");
							response.sendRedirect("./admin/newMorS.jsp?action=S");
						}
					} catch (SQLException | IOException e) {
						System.out.println(e);
						response.sendRedirect(this.getServletContext().getContextPath());
					}
				}
			} else
				response.sendRedirect(this.getServletContext().getContextPath());

		}
	}

	private <T> boolean check(Collection<?> items, String add, T item) {
		if (add.equalsIgnoreCase("M")) {
			Categoria c = (Categoria) item;
			Iterator<?> iter = items.iterator();
			while (iter.hasNext()) {
				Categoria temp = (Categoria) iter.next();
				if (temp.getNomeCategoria().equalsIgnoreCase(c.getNomeCategoria()))
					return true;
			}
		} else if (add.equalsIgnoreCase("S")) {
			SottoCategoria s = (SottoCategoria) item;
			Iterator<?> iter = items.iterator();
			while (iter.hasNext()) {
				SottoCategoria temp = (SottoCategoria) iter.next();
				if (temp.getNomeSottoCategoria().equalsIgnoreCase(s.getNomeSottoCategoria()))
					return true;
			}
		}
		return false;
	}

}
