package com.enterprisedatabase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductLineServlet
 */
@WebServlet("/productlines")
public class ProductLineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductLineDAO productlineDAO = new ProductLineDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductLineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	 // List all the product lines
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      List<ProductLine> productlines = productlineDAO.findAllProductLines();
	      request.setAttribute("productlines", productlines); 
		request.getRequestDispatcher("/WEB-INF/JSPs/allproductlines.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 // Update Product Line Description
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String productLine = request.getParameter("productLineName")
	String prodLineDescription = request.getParameter("htmlDescription");
	PrintWriter printWriter= response.getWriter();
	
	try 
		{		 
 			ProductLineDAO productlineDAO = new ProductLineDAO(); 
 		    boolean isValid = productlineDAO.isIdValidProductLine(String.split(productLine)); 
 		    if(isValid) 
 			{ 
 		    	productlineDAO.updateProductLineDesc(String.split(productLine), prodLineDescription); 
 		    	String htmlRespone = "<html>"; 
 			    htmlRespone += "<h2>Description is successfully updated for product liine </br> : " + productLine + "</h2>"; 
 			    htmlRespone += "</html>";		      
 			    printWriter.println(htmlRespone); 
 				List<ProductLine> productlines = productlineDAO.findProductLine(String.split(productLine)); 
					request.setAttribute("productlines", productlines); 
					request.getRequestDispatcher("/WEB-INF/JSPs/updatedproductlinelist.jsp").forward(request, response); 
			} 

	
                  	else 
 		    { 
 		    	String htmlRespone1 = "<html>"; 
 			    htmlRespone1 += "<h2><b>There are no product lines under the</b> </br> name=: " + productLine + "</h2>"; 
 			    htmlRespone1 += "</html>"; 
 			    printWriter.println(htmlRespone1); 
 		    }    

		} 
		catch(Exception e) 
 		{ 
 			String htmlRespone = "<html>"; 
 		    htmlRespone += "<h2>There are no products with product line=: " + productLine + "</h2>"; 
 		    htmlRespone += "</html>"; 
 		      
 		    printWriter.println(htmlRespone); 
 		}	  
	
	}

}
