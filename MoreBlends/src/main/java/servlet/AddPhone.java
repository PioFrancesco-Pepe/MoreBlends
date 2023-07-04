package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.IBeanDAO;
import control.TelefonoControl;
import model.Cliente;
import model.Telefono;

/**
 * Servlet implementation class AddPhone
 */
@WebServlet("/addPhone")
public class AddPhone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static IBeanDAO<Telefono> phoneDao = new TelefonoControl();

	public AddPhone() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cliente c = (Cliente) request.getSession().getAttribute("currentUtente");
		if (c != null) {
			Telefono t = new Telefono();
			t.setIdCliente(c.getId());
			t.setNumTelefono(request.getParameter("phone"));
			if (checkTelefono(t, c))
			{
				request.getSession().setAttribute("popupP", 1);
				request.getSession().setAttribute("error","Telefono gi&agrave; presente nella lista immetti un altro numero di telefono");
				response.sendRedirect("./pages/insertOtherTelefono.jsp");
			}
			else {
				try {
					phoneDao.doSave(t);
					c.getNumeriTelefono().add(t);
					request.getSession().setAttribute("currentUtente", c);
				} catch (SQLException e) {
					response.sendRedirect(this.getServletContext().getContextPath());
				}
				request.getSession().setAttribute("popupS", 1);
				request.getSession().setAttribute("successo","Telefono inserito nella lista.");
				response.sendRedirect("./pages/Profilo.jsp");
			}
		}
	}

	private boolean checkTelefono(Telefono t, Cliente c) {
		Iterator<Telefono> iterT = c.getNumeriTelefono().iterator();
		while (iterT.hasNext()) {
			Telefono temp = iterT.next();
			if (temp.getNumTelefono().equals(t.getNumTelefono()))
				return true;
		}
		return false;
	}

}
