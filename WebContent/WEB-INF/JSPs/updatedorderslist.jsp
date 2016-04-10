<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"  %>



<!DOCTYPE HTML>
<html>
<head>
<title>Order details</title>
</head>
<body>
<h2>Here are the order details:</h2>
<table>
<tr>
		<th>Order Id</th>
		<th>Order Date</th>
		<th>Required Date</th>
		<th>Shipped Date</th>
		<th>Status</th>
	</tr>
	
	
    <c:forEach items="${orderdetails}" var="orderModel">
        <tr>
            <td><c:out value="${orderModel.getOrderNumber()}" /></td>
             <td><c:out value="${orderModel.getOrderDate()}" /></td>
              <td><c:out value="${orderModel.getRequiredDate()}" /></td>
              <td><c:out value="${orderModel.getShippedDate()}" /></td>
               <td><c:out value="${orderModel.getStatus()}" /></td>
               
           
        </tr>
    </c:forEach>
</table>
</body>
</html>
