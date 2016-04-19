package com.enterprisedatabase.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprisedatabase.dao.CustomerDAO;
import com.enterprisedatabase.dao.EmployeeDAO;
import com.enterprisedatabase.dao.OfficesDAO;
import com.enterprisedatabase.model.Employee;
import com.enterprisedatabase.model.Office;

/**
 * Servlet implementation class OfficeServlet
 */
@WebServlet("/offices")
public class OfficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OfficesDAO officeDAO = new OfficesDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OfficeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Office> officedetails = officeDAO.findAllOffices();
			request.setAttribute("officedetails", officedetails);
			request.getRequestDispatcher("/WEB-INF/JSPs/allofficeslist.jsp").forward(request, response);
		}
			

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String employeeName = request.getParameter("FirstName");
		String officeCode = request.getParameter("officeCode");
		 PrintWriter printWriter= response.getWriter();   
		try
		{		
			OfficesDAO officeDAO = new OfficesDAO();
			boolean isValid = officeDAO.isIdValidOffice(Integer.parseInt(officeCode));
			if(isValid)
			{
				//employeeDAO.updateemployeeName(Integer.parseInt(officeCode), employeeName);
				String htmlRespone = "<html>";
				htmlRespone += "</html>";
				htmlRespone += "<h2>Here are the office details which belong to "+officeCode+": </h2>";
				printWriter.println(htmlRespone);
				List<Office> officeDetails = officeDAO.findOfficeDetailsByCode(Integer.parseInt(officeCode));
				request.setAttribute("officeDetails", officeDetails);
				request.getRequestDispatcher("/WEB-INF/JSPs/officedetailsbycode.jsp").forward(request, response);
				
			}
			else
			{
				String htmlRespone1 = "<html>";
				htmlRespone1 += "<h2><b>There are no employee in the office with the</b> </br> officeCode=: " + officeCode + "</h2>";
				htmlRespone1 += "</html>";
				printWriter.println(htmlRespone1);
			}
		}
		catch(Exception e)
		{
			String htmlRespone = "<html>";
			htmlRespone += "<h2>There are no employees with officeCode=: " + officeCode + "</h2>";
			htmlRespone += "</html>";

			printWriter.println(htmlRespone);
		}	 
	}

}
