package com.enterprisedatabase.dao;

import java.lang.String;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.enterprisedatabase.model.Customer;


public class CustomerDAO {

	//list all customers
	public List<Customer> findAllCustomers()
	{
		List<Customer> allCustomers = new ArrayList<Customer>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			PreparedStatement preparedStatement = con.prepareStatement("select * from customers order by customerName asc");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Customer customers = new Customer();
				customers.setCustomerNumber(resultSet.getInt(1));
				customers.setCity(resultSet.getString(8));
                customers.setCustomerName(resultSet.getString(2));
                customers.setPhone(resultSet.getString(5));
                customers.setAddressLine1(resultSet.getString(6));
                customers.setCity(resultSet.getString(8));
                customers.setCountry(resultSet.getString(11));
                customers.setPostalCode(resultSet.getString(10));
                customers.setPhone(resultSet.getString(5));
                customers.setCreditLimit(resultSet.getDouble(13));
				allCustomers.add(customers);
			}
		} 
		catch (Exception  e) 
		{
			 e.printStackTrace();
		}
		return allCustomers;
	}
	
//	public boolean customerLogin(Integer customerId,String customerNamePassword) throws Exception,IllegalAccessException
//	{
//		
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");
//
//				PreparedStatement preparedStatement = con.prepareStatement("select * from customers where customerNumber ="+customerId+" AND contactFirstName="+customerNamePassword+"");
//				ResultSet resultSet = preparedStatement.executeQuery();
//				return resultSet.isBeforeFirst();
//		
//		
//		
//	}
	
	public boolean customerLogin(Integer customerId,String customerNamePassword) throws Exception,IllegalAccessException
	{
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

				PreparedStatement preparedStatement = con.prepareStatement("select * from customers where customerNumber = "+customerId+" AND contactFirstName= \""+customerNamePassword+"\"");
				ResultSet resultSet = preparedStatement.executeQuery();
				return resultSet.isBeforeFirst();		
	}
		
	//find the details of a customer given the customer id.
	public List<Customer> findCustomer(Integer customerId)
	{
		List<Customer> allCustomers = new ArrayList<Customer>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			PreparedStatement preparedStatement = con.prepareStatement("select * from customers where customerNumber ="+customerId+"");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Customer customers = new Customer();
				customers.setCustomerNumber(resultSet.getInt(1));
				customers.setCity(resultSet.getString(8));
                customers.setCustomerName(resultSet.getString(2));
                customers.setPhone(resultSet.getString(5));
                customers.setAddressLine1(resultSet.getString(6));
                customers.setCity(resultSet.getString(8));
                customers.setCountry(resultSet.getString(11));
                customers.setPostalCode(resultSet.getString(10));
                customers.setPhone(resultSet.getString(5));
                customers.setCreditLimit(resultSet.getDouble(13));
				allCustomers.add(customers);
			}
		} 
		catch (Exception  e) 
		{
			 e.printStackTrace();
		}
		return allCustomers;
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
	
	//delete customer details
		public void deleteACustomer(Integer customerNumberToBeDeleted)throws Exception,IllegalAccessException
		{
			PreparedStatement preparedStatement;
				Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			 Statement st = con.createStatement();
			 
			ResultSet rs= st.executeQuery("select * from customers where customerNumber ="+customerNumberToBeDeleted+"");
					
				if(rs.next()){
				preparedStatement = con.prepareStatement("delete from customers where customerNumber=? ");
	preparedStatement.setInt(1, customerNumberToBeDeleted);
					preparedStatement.executeUpdate();
					
				System.out.println("Record is deleted! ");}
				 else
		            {
		            	System.out.println("Employee Id you entered has not served to any of our existing customer!");
		            }						
		}
	
	//checks whether employee id is valid
	public boolean isIdValidEmployee(Integer employeeNumber) throws Exception,IllegalAccessException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

        Statement st = con.createStatement();
		 
		ResultSet rs= st.executeQuery("select * from customers where salesRepEmployeeNumber ="+employeeNumber+"");
		//checking for existence of user entered pin
		
		return rs.isBeforeFirst();
	}

	public List<Customer> findCustomesUnderEmployee(Integer employeeId) throws Exception,IllegalAccessException
	{
		PreparedStatement preparedStatement;
        

		List<Customer> allCustomers = new ArrayList<Customer>();
		
	        
	            Class.forName("com.mysql.jdbc.Driver").newInstance();

	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

	            Statement st = con.createStatement();

				//checks whether the employee id given by the is valid
	            ResultSet rs= st.executeQuery("select * from customers where salesRepEmployeeNumber="+employeeId+" ");

	            if(rs.next()){
	            	while(rs.next())
	            	{
	            		preparedStatement = con.prepareStatement("select * from customers where salesRepEmployeeNumber="+employeeId+" ");
	            		preparedStatement.execute();
	            		Customer customers = new Customer();
	            		customers.setCustomerNumber(rs.getInt(1));
	            		customers.setCustomerName(rs.getString(2));
	            		customers.setPhone(rs.getString(5));
	            		customers.setCountry(rs.getString(11));
	            		allCustomers.add(customers);
	            	}
	            }
	            else
	            {
	            	System.out.println("Employee Id you entered has not served to any of our existing customer!");
	            }
	        
	       
		return allCustomers;
	}

	

	//updates the customer's city
	public void updateCustomerCity(Integer customerNumberToBeUpdated,String cityToBeUpdated) throws Exception,IllegalAccessException
    {     
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            Statement st = con.createStatement();
   		 
    		ResultSet rs= st.executeQuery("select * from customers where customerNumber ="+customerNumberToBeUpdated+"");
    		
    		 String updateQuery = "UPDATE customers SET city = ?"
                     + " WHERE customerNumber = ? ";
    			if(rs.next()){
    				PreparedStatement ps = con.prepareStatement(updateQuery);
    				ps.setString(1, cityToBeUpdated);
    	            ps.setInt(2, customerNumberToBeUpdated);
    	           
    	            ps.executeUpdate();
    				
    				System.out.println("Record is updated!");
    			}
    		
    }
	
	
}