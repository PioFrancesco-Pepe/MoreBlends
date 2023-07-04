package servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Collection;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.ClienteControl;
import control.IBeanDAO;
import control.IndirizzoControl;
import control.TelefonoControl;
import model.Cliente;
import model.Indirizzo;
import model.Telefono;


@WebServlet("/Registration")
public class Registration extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static IBeanDAO<Cliente> clienteDao = new ClienteControl();
	private static IBeanDAO<Telefono> telefonoDao = new TelefonoControl();
	private static IBeanDAO<Indirizzo> indirizzoDao = new IndirizzoControl();
	
    public Registration() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if(ClienteControl.checkEmail(clienteDao.doRetrieveAll(""), request.getParameter("email")))
			{
				request.getSession().setAttribute("checkEmail","Email gi&agrave; usata, utilizzane un'altra.");
				response.sendRedirect("./pages/registrati.jsp");
			}
			else
			{	
				Cliente beanC= new Cliente();
				Telefono beanT=new Telefono();
				Indirizzo beanI= new Indirizzo();
			
				beanI.setVia(request.getParameter("via"));
				beanI.setCivico(request.getParameter("civico"));
				beanI.setCAP(request.getParameter("CAP"));
				beanI.setLocalita(request.getParameter("localita"));
				beanI.setSiglaProvincia(request.getParameter("sp"));
			
				beanC.setNome(request.getParameter("firstname"));
				beanC.setCognome(request.getParameter("lastname"));
				beanC.setEmail(request.getParameter("email").toLowerCase());
				beanC.setPassword(toHash(request.getParameter("password")));
			
				try {
					clienteDao.doSave(beanC);
					Collection<Cliente> clienti=clienteDao.doRetrieveAll("");
					int idCliente=ClienteControl.getidCliente(clienti,beanC);
					beanI.setIdCliente(idCliente);
					indirizzoDao.doSave(beanI);
				
					beanT.setNumTelefono(request.getParameter("phone"));
					beanT.setIdCliente(idCliente);
					telefonoDao.doSave(beanT);
				} catch (SQLException e) {
					response.sendRedirect(this.getServletContext().getContextPath());
				}
				request.getSession().setAttribute("popupR",1);
				response.sendRedirect(getServletContext().getContextPath()+"/pages/accedi.jsp");
			}
		} catch (SQLException | IOException e) {
			response.sendRedirect(this.getServletContext().getContextPath());
		}
	}

	private String toHash(String password) {
        String hashString = null;
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hashString = "";
            for (int i = 0; i < hash.length; i++) {
                hashString += Integer.toHexString( 
                                  (hash[i] & 0xFF) | 0x100 
                              ).toLowerCase().substring(1,3);
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return hashString;
    }
	
}
