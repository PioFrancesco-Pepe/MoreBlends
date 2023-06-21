package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import control.ClienteControl;
import control.IBeanDAO;
import control.OrdineControl;
import model.Cliente;
import model.Ordine;

/**
 * Servlet implementation class FindOrder
 */
@WebServlet("/findOrderJson")
public class FindOrderJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrdineControl ordineControl = new OrdineControl();
	private IBeanDAO<Cliente> userDao = new ClienteControl();

	public FindOrderJson() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();

		String datex = request.getParameter("datex");
		String datey = request.getParameter("datey");
		String user = request.getParameter("user");

		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;
		Collection<Ordine> o = null;

		if (datex != null)
			b1 = true;
		if (datey != null)
			b2 = true;
		if (user != null)
			b3 = true;

		if (b1 && b2 && b3) {
			try {
				if (datex.equals(""))
					datex = "2000-01-01";
				if (datey.equals(""))
					datey = LocalDate.now().toString();
				o = ordineControl.findOrder(datex, datey, Integer.parseInt(user));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		String risultato = null;
		String risultato2 = "";
		if (o != null) {
			Iterator<Ordine> iterO = o.iterator();
			while (iterO.hasNext()) {
				Ordine temp = iterO.next();
				Cliente c;
				try {
					c = userDao.doRetrieveByKey(temp.getIdCliente());
					risultato = "<li><div class=\"table-order\">" + "<div class=\"idOrdine\">\r\n"
							+ "<a href=\"../currentOrdine?code=" + temp.getIdOrdine() + "\">" + temp.getIdOrdine()
							+ "</a>" + "</div>" + "<div class=\"dataOrdine\">" + temp.getDataInserimento() + "</div>"
							+ "<div class=\"cliente\">" + c.getNome() + " " + c.getCognome() + "</div>"
							+ "<div class=\"statusordine\">" + temp.getStatusOrdine() + "</div>" + "</div></li>";
					risultato2 = risultato + risultato2;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			risultato2 = "Nessun Ordine trovato.";
		}

		JSONObject json = new JSONObject();
		json.put("functionName", "aggiornaFindOrderJSON");
		json.put("result", risultato2);
		out.print(json.toString());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
