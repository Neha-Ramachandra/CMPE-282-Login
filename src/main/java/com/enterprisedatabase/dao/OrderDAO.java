package com.enterprisedatabase.dao;



import com.enterprisedatabase.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO
{
    //list all orders
    public List<Order> findAllOrders()
    {
        List<Order> allOrders = new ArrayList<Order>();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            PreparedStatement preparedStatement = con.prepareStatement("select * from orders order by orderNumber asc");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order orders = new Order();
                orders.setOrderNumber(resultSet.getInt(1));
                orders.setOrderDate(resultSet.getDate(2));
                orders.setRequiredDate(resultSet.getDate(3));
                orders.setShippedDate(resultSet.getDate(4));
                orders.setStatus(resultSet.getString(5));
                orders.setComments(resultSet.getString(6));
                allOrders.add(orders);
            }
        }
        catch (Exception  e)
        {
            e.printStackTrace();
        }
        return allOrders;
    }
    
    //check whether a particular customer has placed orders
    public boolean isIdValidCustomer(Integer customerNumberToBeModified) throws Exception,IllegalAccessException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

        Statement st = con.createStatement();
		 
		ResultSet rs= st.executeQuery("select * from customers where customerNumber ="+customerNumberToBeModified+"");
		//checking for existance of user entered pin
		return rs.isBeforeFirst();
		 
			         
	}
    
  //check whether a particular order is present given the order id.
    public boolean isIdValidOrder(Integer orderNumberToBeUpdated) throws Exception,IllegalAccessException
  	{
  		Class.forName("com.mysql.jdbc.Driver").newInstance();

          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

          Statement st = con.createStatement();
  		 
          ResultSet rs= st.executeQuery("select * from orders where orderNumber =" + orderNumberToBeUpdated + "");
  		//checking for existance of user entered pin
  		return rs.isBeforeFirst();
  		 
  			         
  	}

    //list the details of order of a customer given the customer id
    public List<Order> findOrderDetailsOfACustomer(Integer customerId) throws Exception,IllegalAccessException
    {
        PreparedStatement preparedStatement;
       

        List<Order> allOrders = new ArrayList<Order>();
       
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            Statement st = con.createStatement();

            ResultSet rs= st.executeQuery("select * from orders where customerNumber="+customerId+" ");

            if(rs.next())
            {
            	while(rs.next())
            	{
            		 preparedStatement = con.prepareStatement("select * from orders where customerNumber="+customerId+" ");
                     preparedStatement.execute();
                     Order orders = new Order();
                     orders.setOrderNumber(rs.getInt(1));
                     orders.setOrderDate(rs.getDate(2));
                     orders.setRequiredDate(rs.getDate(3));
                     orders.setShippedDate(rs.getDate(4));
                     orders.setStatus(rs.getString(5));

                     allOrders.add(orders);
            	}
            }
            
            else
            {
                System.out.println("Customer you entered has not placed any order!");
            }
            return allOrders;
        }

  //list the details of order given the order id
    public List<Order> findOrder(Integer orderId) throws Exception,IllegalAccessException
	{
		List<Order> allOrders = new ArrayList<Order>();
		
		
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

		
			PreparedStatement preparedStatement = con.prepareStatement("select * from orders where orderNumber ="+orderId+"");

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next())
        	{
        		 preparedStatement = con.prepareStatement("select * from orders where orderNumber="+orderId+" ");
                 preparedStatement.execute();
                 Order orders = new Order();
                 orders.setOrderNumber(rs.getInt(1));
                 orders.setOrderDate(rs.getDate(2));
                 orders.setRequiredDate(rs.getDate(3));
                 orders.setShippedDate(rs.getDate(4));
                 orders.setStatus(rs.getString(5));

                 allOrders.add(orders);
        	}
			
		
		return allOrders;
	}
    
    //returns the statuses of the order given the order id
    public List<Order> findOrderStatus(Integer orderId) throws Exception,IllegalAccessException
   	{
   		
    	List<Order> allOrders = new ArrayList<Order>();
		
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();

	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

	
		PreparedStatement preparedStatement = con.prepareStatement("select * from orders where orderNumber ="+orderId+"");

		ResultSet rs = preparedStatement.executeQuery();
   			if(rs.next())
        	{
   			 preparedStatement = con.prepareStatement("select * from orders where orderNumber="+orderId+" ");
             preparedStatement.execute();
                Order orders = new Order();
                orders.setStatus(rs.getString(5));

                 allOrders.add(orders);
        	}
 		
   		return allOrders;
   	}
		

    //update the status of the order
    public void updateOrderDetails(Integer orderNumberToBeUpdated,String orderStatusToBeChanged) throws Exception,IllegalAccessException
    {    
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            Statement st = con.createStatement();

            ResultSet rs= st.executeQuery("select * from orders where orderNumber =" + orderNumberToBeUpdated + "");
           
            String updateQuery = "UPDATE orders SET status = ?"
                    + " WHERE orderNumber = ? ";
            if(rs.next()){
                PreparedStatement ps = con.prepareStatement(updateQuery);
                ps.setString(1, orderStatusToBeChanged);
                ps.setInt(2, orderNumberToBeUpdated);

                ps.executeUpdate();

                System.out.println("Record is updated!");
            }
            else
            {
                System.out.println("Customer Id you entered does not match the records!");
            }    
    }

}
