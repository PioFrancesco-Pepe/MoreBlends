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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		 String hashPassword = toHash(password);
		 
		 Collection<Cliente> clienti;
		try {
			clienti = clienteDao.doRetrieveAll("");
			Iterator<Cliente> iter = clienti.iterator();
			 while(iter.hasNext()) {
				 Cliente c=iter.next();
				 if(email.compareTo(c.getEmail()) == 0 && hashPassword.compareTo(c.getPassword()) == 0)
				 {
					 request.getSession().setAttribute("isAdmin", c.isAdmin());
					 request.getSession().setAttribute("idCliente", c.getId());
					 response.sendRedirect("index.jsp");
				 }
			 }
		} catch (SQLException e) {
			e.printStackTrace();
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
