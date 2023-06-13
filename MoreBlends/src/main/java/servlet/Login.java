package servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

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

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static IBeanDAO<Cliente> clienteDao = new ClienteControl();
	
    public Login() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int flag = 0;
		String hashPassword = toHash(password);

		Collection<Cliente> clienti;
		try {
			clienti = clienteDao.doRetrieveAll("");
			Iterator<Cliente> iter = clienti.iterator();
			while (iter.hasNext()) {
				Cliente c = iter.next();
				if (email.compareTo(c.getEmail()) == 0 && hashPassword.compareTo(c.getPassword()) == 0) {
					c.setNumeriTelefono(TelefonoControl.getAllTelefono(c.getId()));
					c.setIndirizzi(IndirizzoControl.getAllIndirizzi(c.getId()));
					request.getSession().setAttribute("isAdmin", c.isAdmin());
					request.getSession().setAttribute("idCliente", c.getId());
					request.getSession().setAttribute("currentUtente", c);
					flag = 1;
					response.sendRedirect("/MoreBlends");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (flag == 0) {
			String s = "Impossibile accedere riprova con un'altra email o password";
			request.getSession().setAttribute("popupE", 1);
			request.getSession().setAttribute("error", s);
			response.sendRedirect("./pages/accedi.jsp");
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
