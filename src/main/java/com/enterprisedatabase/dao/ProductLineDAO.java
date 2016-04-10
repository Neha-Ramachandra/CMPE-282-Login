package com.enterprisedatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.enterprisedatabase.model.ProductLine;

public class ProductLineDAO {

	//list all the product lines

	public List<ProductLine> findAllProductLines()
	{
		List<ProductLine> allProductLines = new ArrayList<ProductLine>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection cont = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			PreparedStatement preparedStatement = cont.prepareStatement("select * from productlines order by productLine asc");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ProductLine productLines = new ProductLine();
				productLines.setProductLine(resultSet.getString(1));
				productLines.setTextDescription(resultSet.getString(2));
				productLines.setHtmlDescription(resultSet.getString(3));
				productLines.setImage(resultSet.getBytes(4));


				allProductLines.add(productLines);
			}
		} 
		catch (Exception  e) 
		{
			e.printStackTrace();
		}
		return allProductLines;
	}


	//find a particular productLine using product line name

	public List<ProductLine> findProductLine(String prodLine)
	{
		List<ProductLine> allProductLines = new ArrayList<ProductLine>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			PreparedStatement preparedStatement = con.prepareStatement("select * from productlines where productLine ="+prodLine+"");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ProductLine productLines = new ProductLine();
				productLines.setProductLine(resultSet.getString(1));
				productLines.setTextDescription(resultSet.getString(2));
				productLines.setHtmlDescription(resultSet.getString(3));
				productLines.setImage(resultSet.getBytes(4));


				allProductLines.add(productLines);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return allProductLines;
	}



	//delete product lines

	public void deleteAproductLine(String productLineToBeDeleted)throws Exception,IllegalAccessException
	{
		PreparedStatement preparedStatement;
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

		Statement st = con.createStatement();

		ResultSet rs= st.executeQuery("select * from productlines where productLine ="+productLineToBeDeleted+"");

		if(rs.next()){
			preparedStatement = con.prepareStatement("delete from productlines where productLine=? ");
			preparedStatement.setString(1, productLineToBeDeleted);
			preparedStatement.executeUpdate();

			System.out.println("Record is deleted! ");}
		else
		{
			System.out.println("Product Line is not valid! ");
		}						
	}


	//update a product line's description

	public void updateProductLineDesc(String productLineToBeUpdated,String descriptionToBeUpdated) throws Exception,IllegalAccessException
	{     
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

		Statement st = con.createStatement();

		ResultSet rs= st.executeQuery("select * from productlines where productLine ="+productLineToBeUpdated+"");

		String updateQuery = "UPDATE productlines SET textDescription = ?"
				+ " WHERE productLine = ? ";
		if(rs.next()){
			PreparedStatement ps = con.prepareStatement(updateQuery);
			ps.setString(1, descriptionToBeUpdated);
			ps.setString(2, productLineToBeUpdated);

			ps.executeUpdate();

			System.out.println("Product Line description is updated!");
		}

	}

}

