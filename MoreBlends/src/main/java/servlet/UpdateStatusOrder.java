package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.OrdineControl;
import model.Cliente;

/**
 * Servlet implementation class UpdateStatusOrder
 */
@WebServlet("/UpdateStatusOrder")
public class UpdateStatusOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateStatusOrder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = (Cliente) request.getSession().getAttribute("currentUtente");
		if (cliente != null &&  (cliente.isAdmin() == 1)) {
			
			int code=Integer.parseInt(request.getParameter("Ordine"));
			int status=Integer.parseInt(request.getParameter("Status"));
			
			try {
				boolean b=OrdineControl.updateOrdine(status,code);
				if(b)
				{
					request.getSession().setAttribute("error","Ordine aggiornato");
					response.sendRedirect("./admin/gestioneOrdini.jsp");
				}
				else {
					request.getSession().setAttribute("error","Errore");
					response.sendRedirect("./admin/gestioneOrdini.jsp");
				}
			} catch (SQLException e) {
				request.getSession().setAttribute("error","Errore riprova");
				response.sendRedirect("./admin/gestioneOrdini.jsp");
			}
		}
		else {
			response.sendRedirect(this.getServletContext().getContextPath());
		}
	}

}
