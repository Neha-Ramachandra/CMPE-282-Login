package com.enterprisedatabase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeDeletionServlet
 */
@WebServlet("/employeedelete")
public class EmployeeDeletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeDeletionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String employeeId = request.getParameter("employeenum");
		PrintWriter printWriter= response.getWriter();
		try
		{
		boolean isValid = employeeDAO.isIdValidEmployee(Integer.parseInt(employeeId));
			if(isValid)
			{
				employeeDAO.deleteAEmployee(Integer.parseInt(employeeId));
				String htmlRespone = "<html>";
				htmlRespone += "<h2>Your order is successfully deleted with employeeId=: " + employeeId + "</h2>";
				htmlRespone += "</html>";
				printWriter.println(htmlRespone);
			}
			else
			{
				String htmlRespone1 = "<html>";
				htmlRespone1 += "<h2><b>There are no employees under the</b> </br> employeeId=: " + employeeId + "</h2>";
				htmlRespone1 += "</html>";
				printWriter.println(htmlRespone1);
			}	
		}
	
		catch(Exception e)
		{
			String htmlRespone = "<html>";
			htmlRespone += "<h2>The employee details with employeeId: " + employeeId + " cannot be deleted.</h2>";
			htmlRespone += "</html>";

			printWriter.println(htmlRespone);
		}	      
	}
}
