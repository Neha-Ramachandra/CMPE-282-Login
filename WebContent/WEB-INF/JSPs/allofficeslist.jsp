<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Office details</title>
</head>
<body>
<h2>Here are the office details:</h2>
<table>
<tr>
		<th>Office Code</th>
		<th>City</th>
		<th>Phone</th>
		<th>Address</th>
		<th>State</th>
		<th>Country</th>
		<th>Postal Code</th>
		<th>Territory</th>
	</tr>
	
	
    <c:forEach items="${oficedetails}" var="officeModel">
        <tr>
            <td><c:out value="${officeModel.getOfficeCode()}" /></td>
             <td><c:out value="${officeModel.getOfficeCity()}" /></td>
              <td><c:out value="${officeModel.getPhone()}" /></td>
              <td><c:out value="${officeModel.getAddressLine1()}" /></td>
               <td><c:out value="${officeModel.getState()}" /></td>
               <td><c:out value="${officeModel.getCountry()}" /></td>
	        <td><c:out value="${officeModel.getPostalCode()}" /></td>
		<td><c:out value="${officeModel.getTerritory()}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
