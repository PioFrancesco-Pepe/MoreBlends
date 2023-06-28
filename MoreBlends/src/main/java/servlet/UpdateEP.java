package servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.ClienteControl;
import model.Cliente;
/**
 * Servlet implementation class UpdateEP
 */
@WebServlet("/updateEP")
public class UpdateEP extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public UpdateEP() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email= request.getParameter("email");
		String password = request. getParameter("password");
		Cliente c = (Cliente)request.getSession().getAttribute("currentUtente");
		if(email!=null && !email.equals(c.getEmail()))
			c.setEmail(email);
		if(password!=null && !password.equals(" "))
			c.setPassword(toHash(password));
		try {
			ClienteControl.updateCliente(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("currentUtente", c);
		response.sendRedirect("./pages/Profilo.jsp");
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
