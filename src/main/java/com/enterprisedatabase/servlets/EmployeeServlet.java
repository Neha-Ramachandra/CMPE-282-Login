package com.enterprisedatabase.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprisedatabase.dao.*;
import com.enterprisedatabase.model.*;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeDAO employeeDAO = new EmployeeDAO();
	PrintWriter printWriter;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> employeedetails = employeeDAO.findAllEmployees();
		request.setAttribute("employeedetails", employeedetails);
		request.getRequestDispatcher("/WEB-INF/JSPs/allemployeeslist.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String employeeName = request.getParameter("FirstName");
		String officeCode = request.getParameter("officeCodeNum");
		printWriter= response.getWriter();   
		try
		{		
			boolean isValid = employeeDAO.isValidOfficeCode(Integer.parseInt(officeCode));
			if(isValid)
			{
				//employeeDAO.updateemployeeName(Integer.parseInt(officeCode), employeeName);
				String htmlRespone = "<html>";
				htmlRespone += "</html>";
				htmlRespone += "<h2>Here are the employees who belong to "+officeCode+": </h2>";
				printWriter.println(htmlRespone);
				List<Employee> employeedetails = employeeDAO.findEmployeesByOfficeCode(Integer.parseInt(officeCode));
				request.setAttribute("employeedetails", employeedetails);
				request.getRequestDispatcher("/WEB-INF/JSPs/employeesbyofficecode.jsp").forward(request, response);
				
			}
			else
			{
				String htmlRespone1 = "<html>";
				htmlRespone1 += "<h2><b>There are no employee under the</b> </br> officeCode=: " + officeCode + "</h2>";
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
