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


public class EmployeeDAO {

	//list all employees
	public List<Employee> findAllEmployees()
	{
		List<Employee> allEmployees = new ArrayList<Employee>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			PreparedStatement preparedStatement = con.prepareStatement("select * from employees order by firstName asc");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Employee employees = new Employee();
				Office office = new Office();
				employees.setEmployeeNumber(resultSet.getInt(1));
				employees.setLastName(resultSet.getString(2));
				employees.setFirstName(resultSet.getString(3));
				employees.setExtension(resultSet.getString(4));
				employees.setEmail(resultSet.getString(5));
				employees.setOfficeCode(resultSet.getInt(6));
				employees.setReportsToEmployeeNumber(resultSet.getInt(7));
				employees.setJobTitle(resultSet.getString(8));
				allEmployees.add(employees);
			}
		} 
		catch (Exception  e) 
		{
			e.printStackTrace();
		}
		return allEmployees;
	}

	//check whether a particular customer has placed orders
		public boolean isIdValidEmployee(Integer employeeNumberToBeDeleted) throws Exception,IllegalAccessException
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();

	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

	        Statement st = con.createStatement();
			 
			ResultSet rs= st.executeQuery("select * from employees where employeeNumber ="+employeeNumberToBeDeleted+"");
			//checking for existance of user entered pin
			return rs.isBeforeFirst();
			 
				         
		}
		
	public boolean isValidOfficeCode(Integer employeesByOfficeCode) throws Exception,IllegalAccessException
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

		Statement st = con.createStatement();

		ResultSet rs= st.executeQuery("select * from employees where officeCode ="+employeesByOfficeCode+"");
		//checking for existance of user entered pin
		return rs.isBeforeFirst();


	}


	//List employees by office code

	public List<Employee> findEmployeesByOfficeCode(Integer officeCode) throws Exception,IllegalAccessException
	{
		PreparedStatement preparedStatement;


		List<Employee> allEmployees = new ArrayList<Employee>();

		
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			Statement st = con.createStatement();
			ResultSet rs= st.executeQuery("select * from employees where officeCode ="+officeCode+" ");

			if(rs.next()){
				while(rs.next())
				{
					preparedStatement = con.prepareStatement("select * from employees where officeCode="+officeCode+" ");
					preparedStatement.execute();
					Employee employees = new Employee();
					employees.setEmployeeNumber(rs.getInt(1));
					employees.setFirstName(rs.getString(3));
					employees.setExtension(rs.getString(4));
					employees.setEmail(rs.getString(5));
					allEmployees.add(employees);
				}
			}
			else
			{
				System.out.println("Can't find any employee with given office code");
			}
		
		return allEmployees;
	}

	//delete employee details

	public void deleteEmployee(Integer employeeNumber)throws Exception,IllegalAccessException
	{
		PreparedStatement preparedStatement;
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

		Statement st = con.createStatement();

		ResultSet rs= st.executeQuery("select * from employees where employeeNumber ="+employeeNumber+"");

		if(rs.next()){
			preparedStatement = con.prepareStatement("delete from employees where employeeNumber="+employeeNumber+" ");
			preparedStatement.execute();
			preparedStatement.executeUpdate();
			preparedStatement.setInt(1, employeeNumber);
			System.out.println("Record is deleted.");
		} 
		else 
		{
			System.out.println("Employee Id you entered does not exist.");   
		}			

	}
}
