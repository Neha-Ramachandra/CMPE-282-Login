<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<!DOCTYPE HTML>
<html>
<head>
<title>Here are the customer details</title>
</head>
<body>
<table>
<tr>
		<th>Customer Id</th>
		<th>Name</th>
		<th>Address</th>
		<th>City</th>
		<th>Country</th>
		<th>Postal Code</th>
		<th>Phone</th>
		<th>Credit-Limit</th>
		<th></th>
	</tr>
	
	
    <c:forEach items="${customerdetails}" var="customerModel">
        <tr>
            <td><c:out value="${customerModel.getCustomerNumber()}" /></td>
             <td><c:out value="${customerModel.getCustomerName()}" /></td>
              <td><c:out value="${customerModel.getAddressLine1()}" /></td>
              <td><c:out value="${customerModel.getCity()}" /></td>
               <td><c:out value="${customerModel.getCountry()}" /></td>
                <td><c:out value="${customerModel.getPostalCode()}" /></td>
                 <td><c:out value="${customerModel.getPhone()}" /></td>
                  <td><c:out value="${customerModel.getCreditLimit()}" /></td>
           
        </tr>
    </c:forEach>
</table>
</body>
</html>

