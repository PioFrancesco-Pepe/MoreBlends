package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.ClienteControl;
import control.IBeanDAO;
import model.Cliente;

/**
 * Servlet implementation class GetClienti
 */
@WebServlet("/getClienti")
public class GetClienti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static IBeanDAO<Cliente> clienteDao= new ClienteControl();

    public GetClienti() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Cliente> clienti;
		try {
			clienti=clienteDao.doRetrieveAll("");
			request.setAttribute("clienti", clienti);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = this.getServletContext().
			getRequestDispatcher("/admin/viewOrders.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
