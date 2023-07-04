package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.IBeanDAO;
import control.OrdineControl;
import model.Ordine;

/**
 * Servlet implementation class GetOrdini
 */
@WebServlet("/getOrdini")
public class GetOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static IBeanDAO<Ordine> ordineDao= new OrdineControl();

    public GetOrdini() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("ordini", ordineDao.doRetrieveAll(""));
		} catch (SQLException e) {
			response.sendRedirect(this.getServletContext().getContextPath());
		}
		RequestDispatcher dispatcher = this.getServletContext().
				getRequestDispatcher("/admin/viewOrders.jsp");
			dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
