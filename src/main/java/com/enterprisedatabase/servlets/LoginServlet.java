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
import com.enterprisedatabase.model.Customer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * Accepts the city to be updated and updated the customer record.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String customerNumber = request.getParameter("customerid");
		String customerPasswordName = request.getParameter("custpwd");   
	    PrintWriter printWriter= response.getWriter();   
		try
		{		
			CustomerDAO customerDAO = new CustomerDAO();
		    boolean isValid = customerDAO.isIdValidCustomer(Integer.parseInt(customerNumber));
		    if(isValid)
			{
		    	boolean loginPass = customerDAO.customerLogin(Integer.parseInt(customerNumber), customerPasswordName);
		    	if(loginPass == true)
		    	{
		    		String htmlRespone = "<html>";
				    htmlRespone += "<h2>Login is successful</h2>";
				    htmlRespone += "</html>";		     
				    printWriter.println(htmlRespone);
				    response.sendRedirect("index.html"); 
		    	}
		    	
		    	else
		    	{
		    		String htmlRespone = "<html>";
				    htmlRespone += "<h2>Login failed. Please try again!</h2>";
				    htmlRespone += "</html>";		     
				    printWriter.println(htmlRespone);
				    //response.sendRedirect("/index.html"); 
				    
		    	}
		    	
//				List<Customer> customerdetails = customerDAO.findCustomer(Integer.parseInt(customerNumber));
//					request.setAttribute("customerdetails", customerdetails);
//					request.getRequestDispatcher("/WEB-INF/JSPs/updatedcustomerslist.jsp").forward(request, response);
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
