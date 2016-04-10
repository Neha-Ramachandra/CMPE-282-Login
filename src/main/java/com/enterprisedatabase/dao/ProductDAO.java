package com.enterprisedatabase.dao;

import java.lang.String;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.enterprisedatabase.model.Product;


public class ProductDAO {

	//list all products
	public List<Product> findAllProducts()
	{
		List<Product> allProducts = new ArrayList<Product>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection cont = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			PreparedStatement preparedStatement = cont.prepareStatement("select * from products order by productCode asc");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Product products = new Product();
				products.setProductCode(resultSet.getString(1));
				products.setProductName(resultSet.getString(2));
                products.setProductDescription(resultSet.getString(6));
                products.setProductVendor(resultSet.getString(5));
                products.setProductScale(resultSet.getString(4));
                products.setQuantityInStock(resultSet.getShort(7));
                products.setBuyPrice(resultSet.getDouble(8));
                products.setMsrp(resultSet.getDouble(9));
                
				allProducts.add(products);
			}
		} 
		catch (Exception  e) 
		{
			 e.printStackTrace();
		}
		return allProducts;
	}
	
	//find a particular product using product id.
	public List<Product> findProduct(String productId)
	{
		List<Product> allProducts = new ArrayList<Product>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			PreparedStatement preparedStatement = con.prepareStatement("select * from products where productCode ="+productId+"");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Product products = new Product();
				products.setProductCode(resultSet.getString(1));
				products.setProductName(resultSet.getString(2));
                products.setProductDescription(resultSet.getString(6));
                products.setProductVendor(resultSet.getString(5));
                products.setProductScale(resultSet.getString(4));
                products.setQuantityInStock(resultSet.getShort(7));
                products.setBuyPrice(resultSet.getDouble(8));
                products.setMsrp(resultSet.getDouble(9));
				allProducts.add(products);
			}
		} 
		catch (Exception  e) 
		{
			 e.printStackTrace();
		}
		return allProducts;
	}
	
	
	
	//delete product details
		public void deleteAProduct(String productCodeToBeDeleted)throws Exception,IllegalAccessException
		{
			PreparedStatement preparedStatement;
				Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			 Statement st = con.createStatement();
			 
			ResultSet rs= st.executeQuery("select * from products where productCode ="+productCodeToBeDeleted+"");
					
				if(rs.next()){
				preparedStatement = con.prepareStatement("delete from products where productCode=? ");
	preparedStatement.setString(1, productCodeToBeDeleted);
					preparedStatement.executeUpdate();
					
				System.out.println("Record is deleted! ");}
				 else
		            {
		            	System.out.println("Product Code is not valid! ");
		            }						
		}
	
	

	

	//updates the products buyPrice
	public void updateProductPrice(String productIdToBeUpdated, Double productPriceToBeUpdated) throws Exception,IllegalAccessException
    {     
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            Statement st = con.createStatement();
   		 
    		ResultSet rs= st.executeQuery("select * from products where productCode ="+productIdToBeUpdated+"");
    		
    		 String updateQuery = "UPDATE products SET buyPrice = ?"
                     + " WHERE productCode = ? ";
    			if(rs.next()){
    				PreparedStatement ps = con.prepareStatement(updateQuery);
    				ps.setDouble(1, productPriceToBeUpdated);
    	            ps.setString(2, productIdToBeUpdated);
    	           
    	            ps.executeUpdate();
    				
    				System.out.println("Record is updated!");
    			}
    		
    }
	
	
}
