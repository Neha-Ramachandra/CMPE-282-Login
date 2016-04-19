package com.enterprisedatabase.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.enterprisedatabase.dao.ProductLineDAO;

/**
 * Servlet implementation class ProductLineDeletionServlet
 */
@WebServlet("/productlinesdelete")
public class ProductLineDeletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductLineDAO productlineDAO = new ProductLineDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductLineDeletionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//Accepts product line from the employee and deletes the product line record.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prodLine = request.getParameter("prodLine"); 
		PrintWriter printWriter= response.getWriter(); 
		try
		{	    
			boolean isValid = productlineDAO.isIdValidProductLine(prodLine);
			if(isValid)
			{
				productlineDAO.deleteAproductLine(prodLine);
				String htmlRespone = "<html>";
				htmlRespone += "<h2>Product line is successfully deleted with name=: " + prodLine + "</h2>";
				htmlRespone += "</html>";
				printWriter.println(htmlRespone);
			}
			else
			{
				String htmlRespone1 = "<html>";
				htmlRespone1 += "<h2><b>There is no product line under the</b> </br> name=: " + prodLine + "</h2>";
				htmlRespone1 += "</html>";
				printWriter.println(htmlRespone1);
			}	    

		}
		catch(Exception e)
		{
			String htmlRespone = "<html>";
			htmlRespone += "<h2>The product line with name: " + prodLine + " cannot be deleted.</h2>";
			htmlRespone += "</html>";

			printWriter.println(htmlRespone);
		}	      

	}

}
