package com.enterprisedatabase.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprisedatabase.dao.CustomerDAO;

/**
 * Servlet implementation class CustomerDeletionServlet
 */
@WebServlet("/customersdelete")
public class CustomerDeletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerDAO customerDAO = new CustomerDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerDeletionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Accepts a customer id from the user and deletes the customers record.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerId = request.getParameter("customernum");
		PrintWriter printWriter= response.getWriter();
		try
		{	    
			boolean isValid = customerDAO.isIdValidCustomer(Integer.parseInt(customerId));
			if(isValid)
			{
				customerDAO.deleteACustomer(Integer.parseInt(customerId));
				String htmlRespone = "<html>";
				htmlRespone += "<h2>Your order is successfully deleted with customerId=: " + customerId + "</h2>";
				htmlRespone += "</html>";
				printWriter.println(htmlRespone);
			}
			else
			{
				String htmlRespone1 = "<html>";
				htmlRespone1 += "<h2><b>There are no customers under the</b> </br> customerId=: " + customerId + "</h2>";
				htmlRespone1 += "</html>";
				printWriter.println(htmlRespone1);
			}	    

		}
		catch(Exception e)
		{
			String htmlRespone = "<html>";
			htmlRespone += "<h2>The customer details with customerId: " + customerId + " cannot be deleted.</h2>";
			htmlRespone += "</html>";

			printWriter.println(htmlRespone);
		}	      
	}
}


