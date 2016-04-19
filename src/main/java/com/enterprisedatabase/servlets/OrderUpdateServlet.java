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
 * Servlet implementation class OrderUpdate
 */
@WebServlet("/ordersupdate")
public class OrderUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String statusOfOrder;
	OrderDAO orderDAO = new OrderDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Updates the status of the order and displays the updated order
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//doGet(request, response);
		String orderNumber = request.getParameter("orderNum");
		String orderStatus = request.getParameter("status");
	    PrintWriter printWriter= response.getWriter();   
		try
		{		
			
		    boolean isValid = orderDAO.isIdValidOrder(Integer.parseInt(orderNumber));
		    if(isValid)
			{
		    	List<Order> status = orderDAO.findOrderStatus(Integer.parseInt(orderNumber));
				for(Order order:status)
				{
					statusOfOrder = order.getStatus();
				}
		    	if(!((statusOfOrder.equals( "Shipped"))|| (statusOfOrder.equals("delete"))))
		    	{
		    		orderDAO.updateOrderDetails(Integer.parseInt(orderNumber), orderStatus);
	    		    
					List<Order> orderdetails = orderDAO.findOrder(Integer.parseInt(orderNumber));
						request.setAttribute("orderdetails", orderdetails);
						request.getRequestDispatcher("/WEB-INF/JSPs/updatedorderslist.jsp").forward(request, response);
				    
		    	}
		    	else
		    	{
		    		String htmlRespone2 = "<html>";
				    htmlRespone2 += "<h2><b>The order with  orderId:  "+ orderNumber +"  is already "+statusOfOrder+".</br> It cannot be cancelled</h2>";
				    htmlRespone2 += "</html>";
				    printWriter.println(htmlRespone2);
		    	}
			}
		    else
		    {
		    	String htmlRespone1 = "<html>";
			    htmlRespone1 += "<h2><b>There are no orders under the</b> </br> orderId: " + orderNumber + "</h2>";
			    htmlRespone1 += "</html>";
			    printWriter.println(htmlRespone1);
		    }    
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String htmlRespone = "<html>";
			htmlRespone += "<h2><b>There are no orders under the</b> </br> orderId: " + orderNumber + "</h2>";
		    htmlRespone += "</html>";
		     
		    printWriter.println(htmlRespone);
		}
}
}
