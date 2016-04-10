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
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	CustomerDAO customerDAO = new CustomerDAO();
	//List<Customer> customerInfo ;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * lists all the customer details
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		List<Customer> customerdetails = customerDAO.findAllCustomers();
			request.setAttribute("customerdetails", customerdetails);
			request.getRequestDispatcher("/WEB-INF/JSPs/allcustomerslist.jsp").forward(request, response);
	}
			
	/**
	 * Accepts the city to be updated and updated the customer record.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String customerNumber = request.getParameter("customerid");
		String customercity = request.getParameter("city");   
	    PrintWriter printWriter= response.getWriter();   
		try
		{		
			CustomerDAO customerDAO = new CustomerDAO();
		    boolean isValid = customerDAO.isIdValidCustomer(Integer.parseInt(customerNumber));
		    if(isValid)
			{
		    	customerDAO.updateCustomerCity(Integer.parseInt(customerNumber), customercity);
		    	String htmlRespone = "<html>";
			    htmlRespone += "<h2>City id successfully updated for customer with id </br> with : " + customerNumber + "</h2>";
			    htmlRespone += "</html>";		     
			    printWriter.println(htmlRespone);
				List<Customer> customerdetails = customerDAO.findCustomer(Integer.parseInt(customerNumber));
					request.setAttribute("customerdetails", customerdetails);
					request.getRequestDispatcher("/WEB-INF/JSPs/updatedcustomerslist.jsp").forward(request, response);
			}
		    else
		    {
		    	String htmlRespone1 = "<html>";
			    htmlRespone1 += "<h2><b>There are no customers under the</b> </br> customerId=: " + customerNumber + "</h2>";
			    htmlRespone1 += "</html>";
			    printWriter.println(htmlRespone1);
		    }   
		}
		catch(Exception e)
		{
			String htmlRespone = "<html>";
		    htmlRespone += "<h2>There are no employees with customerId=: " + customerNumber + "</h2>";
		    htmlRespone += "</html>";
		     
		    printWriter.println(htmlRespone);
		}	 
	}
}