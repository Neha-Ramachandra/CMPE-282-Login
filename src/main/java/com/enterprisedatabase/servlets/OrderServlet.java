package com.enterprisedatabase.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.enterprisedatabase.dao.OrderDAO;
import com.enterprisedatabase.model.Order;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderDAO orderDAO = new OrderDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * lists the order details
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	List<Order> orderdetails = orderDAO.findAllOrders();
			request.setAttribute("orderdetails", orderdetails);
			request.getRequestDispatcher("/WEB-INF/JSPs/orderdetails.jsp").forward(request, response);
	}

	/**
	 * Accepts the customer id and lists the related order details for the customer
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String customerNumber = request.getParameter("customerid");   
	    PrintWriter printWriter= response.getWriter();
	    try
	    {
		    boolean isValid = orderDAO.isIdValidCustomer(Integer.parseInt(customerNumber));
		    if(isValid)
			{
		    	List<Order> orderInfo  = orderDAO.findOrderDetailsOfACustomer(Integer.parseInt(customerNumber));	    	
				request.setAttribute("orderInfo", orderInfo);
				request.getRequestDispatcher("/WEB-INF/JSPs/ordersofcustomer.jsp").forward(request, response);
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
		    htmlRespone += "<h2>There are no order placed by customer with customerId=: " + customerNumber + "</h2>";
		    htmlRespone += "</html>";
		     
		    printWriter.println(htmlRespone);
		}
		   
	}
}
