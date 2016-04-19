package com.enterprisedatabase.dao;

import java.lang.String;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.enterprisedatabase.model.Customer;
import com.enterprisedatabase.model.Employee;
import com.enterprisedatabase.model.Office;
import com.enterprisedatabase.model.Payment;

public class OfficesDAO {

	//list all Offices
	public List<Office> findAllOffices()
	{
		List<Office> allOffices = new ArrayList<Office>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			PreparedStatement preparedStatement = con.prepareStatement("select * from offices order by officeCode asc");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Office offices = new Office();
				offices.setCity(resultSet.getString(10));
                offices.setState(resultSet.getString(10));
                offices.setCountry(resultSet.getString(10));
                offices.setAddressLine1(resultSet.getString(6));
                offices.setTerritory(resultSet.getString(8));
                offices.setPhone(resultSet.getString(11));
                offices.setPostalCode(resultSet.getString(10));
               
				allOffices.add(offices);
			}
		}
			catch (Exception  e) 
			{
				 e.printStackTrace();
			}
			return allOffices;
	}
	
	public boolean isIdValidOffice(Integer officeCodeToShowData) throws Exception,IllegalAccessException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

        Statement st = con.createStatement();
		 
		ResultSet rs= st.executeQuery("select * from offices where officeCode ="+officeCodeToShowData+"");
		//checking for existance of user entered pin
		return rs.isBeforeFirst();
		 
			         
	}


//Give Office details by office code

public List<Office> findOfficeDetailsByCode(Integer officeCode)
{
    PreparedStatement preparedStatement;
    

    List<Office> allOfficeInfo = new ArrayList<Office>();
    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

        Statement st = con.createStatement();

        ResultSet rs= st.executeQuery("select * from offices where officeCode="+officeCode+" ");

        if(rs.next())
        {
        	 while(rs.next()){
                 preparedStatement = con.prepareStatement("select * from offices where officeCode="+officeCode+" ");
                 preparedStatement.execute();
                 Office offices = new Office();
                 offices.setOfficeCode(rs.getString(1));
                 offices.setPhone(rs.getString(3));
                 offices.setAddressLine1(rs.getString(4));
                 offices.setCity(rs.getString(2));            
                 offices.setState(rs.getString(6));
                 offices.setCountry(rs.getString(7));                
                 offices.setPostalCode(rs.getString(8));
                 allOfficeInfo.add(offices);
             }
        }  
        else
        {
            System.out.println("Office code you entered is not related to any office.");
        }

    }

    catch (Exception  e)
    {
        System.out.println("Invalid input");
    }
    return allOfficeInfo;
}
}
