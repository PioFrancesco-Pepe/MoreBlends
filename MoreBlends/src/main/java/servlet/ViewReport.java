package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.ProdottoControl;
import model.Report;

/**
 * Servlet implementation class ViewReport
 */
@WebServlet("/viewReportS")
public class ViewReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ViewReport() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int code=Integer.parseInt(request.getParameter("report"));
		if(code==1)
		{
			try {
				Report r = ProdottoControl.getReport(code, request.getParameter("dateG"));
				request.getSession().setAttribute("reports", r);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(code==2)
		{
			try {
				Report r = ProdottoControl.getReport(code, request.getParameter("mese"));
				request.getSession().setAttribute("reports", r);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(code==3)
		{
			try {
				Report r = ProdottoControl.getReport(code, request.getParameter("anno"));
				request.getSession().setAttribute("reports", r);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		response.sendRedirect("./admin/report.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
