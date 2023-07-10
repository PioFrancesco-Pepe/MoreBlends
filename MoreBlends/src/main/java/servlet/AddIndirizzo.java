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
import control.IndirizzoControl;
import model.Cliente;
import model.Indirizzo;

/**
 * Servlet implementation class AddIndirizzo
 */
@WebServlet("/addIndirizzo")
public class AddIndirizzo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IBeanDAO<Indirizzo> indirizzoDao = new IndirizzoControl();
       
    public AddIndirizzo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente c = (Cliente) request.getSession().getAttribute("currentUtente");
		if(c!=null)
		{
			Indirizzo i= new Indirizzo();
			i.setIdCliente(c.getId());
			i.setVia(request.getParameter("via"));
			i.setCivico(request.getParameter("civico"));
			i.setCAP(request.getParameter("CAP"));
			i.setLocalita(request.getParameter("localita"));
			i.setSiglaProvincia(request.getParameter("sp"));
			if(checkIndirizzo(i, c)) 
			{
				request.getSession().setAttribute("popupI", 1);
				request.getSession().setAttribute("error","Indirizzo gi&agrave; presente nella lista immetti un altro indirizzo");
				response.sendRedirect("./pages/insertOtherIndirizzo.jsp");
			}
			else {
				try {
					indirizzoDao.doSave(i);
					c.getIndirizzi().add(i);
					request.getSession().setAttribute("currentUtente", c);
				} catch (SQLException e) {
					request.getSession().setAttribute("popupI", 1);
					request.getSession().setAttribute("error","Errore riprova a reinserire l'indirizzo");
					response.sendRedirect("./pages/insertOtherIndirizzo.jsp");
				}
				request.getSession().setAttribute("popupS", 1);
				request.getSession().setAttribute("successo","Indirizzo inserito nella lista.");
				response.sendRedirect("./pages/Profilo.jsp");
			}
		}	
	}
	
	private boolean checkIndirizzo(Indirizzo i, Cliente c)
	{
		Iterator<Indirizzo> iterI=c.getIndirizzi().iterator();
		while(iterI.hasNext())
		{
			Indirizzo temp = iterI.next();
			if(temp.equals(i))
				return true;
		}
		return false;
	}

}
