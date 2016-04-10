package com.enterprisedatabase.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.enterprisedatabase.model.Order;

public class OrderDetailDAO {

	// list all the orders
	public List<Order> findAllOrders() {
		List<Order> allOrders = new ArrayList<Order>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection cont = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			PreparedStatement preparedStatement = cont
					.prepareStatement("select * from orderdetails order by orderNumber asc");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Order orders = new Order();
				orders.setOrderNumber(resultSet.getInt(1));
				orders.setProductCode(resultSet.getString(2));
				orders.setQuantityOrdered(resultSet.getInt(3));
				orders.setPriceOfEach(resultSet.getDouble(4));
				orders.setOrderLineNumber(resultSet.getShort(5));

				allOrders.add(orders);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allOrders;
	}

	// find a particular order detail using order number

	public List<Order> findOrder(Integer orderNum) {
		List<Order> allOrders = new ArrayList<Order>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

			PreparedStatement preparedStatement = con
					.prepareStatement("select * from orderdetails where orderNumber =" + orderNum + "");

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Order orders = new Order();
				orders.setOrderNumber(resultSet.getInt(1));
				orders.setProductCode(resultSet.getString(2));
				orders.setQuantityOrdered(resultSet.getInt(3));
				orders.setPriceOfEach(resultSet.getDouble(4));
				orders.setOrderLineNumber(resultSet.getShort(5));
				allOrders.add(orders);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allOrders;
	}

	// delete order details

	public void deleteAnOrder(Integer orderNumberToBeDeleted) throws Exception, IllegalAccessException {
		PreparedStatement preparedStatement;
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery("select * from orderdetails where orderNumber =" + orderNumberToBeDeleted + "");

		if (rs.next()) {
			preparedStatement = con.prepareStatement("delete from orderdetails where orderNumber=? ");
			preparedStatement.setInt(1, orderNumberToBeDeleted);
			preparedStatement.executeUpdate();

			System.out.println("Record is deleted! ");
		} else {
			System.out.println("Order Number is not valid! ");
		}
	}
}
