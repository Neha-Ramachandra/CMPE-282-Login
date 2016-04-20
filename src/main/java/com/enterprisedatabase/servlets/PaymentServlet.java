package com.enterprisedatabase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/payments")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PaymentDAO paymentDAO = new PaymentDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Payment> paymentdetails = paymentDAO.listAllPayment();
		request.setAttribute("paymentdetails", paymentdetails);
		request.getRequestDispatcher("/WEB-INF/JSPs/paymentlist.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String customerId = request.getParameter("customernum");
	    PrintWriter printWriter = response.getWriter();
	    
	    
	   try
	   
	   { 
	   	boolean isValid = paymentDAO.isIDValidCustomer(Interger.parseInt(customerId));
	   	if (isValid)
	   	
	   	{ 
	   		List<Payment> paymentinfo = PaymentDAO.findPaymentDetailsOfACustomer(Integer.parseInt(customerId));
	   		request.setAttribute("paymentinfo",paymentinfo);
	   		request.getRequestDispatcher("/WEB-INF/JSPs/paymentdetailsofcustomerbyid.jsp").forward(request,response);
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
 		    htmlRespone += "<h2>There are no payment details available for the customer with customerId=: " + customerId + "</h2>"; 
 		    htmlRespone += "</html>"; 
 		      
 		    printWriter.println(htmlRespone); 
 		} 

	   
	   
	            }
	}


