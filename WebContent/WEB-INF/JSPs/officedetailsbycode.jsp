<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Office details</title>
</head>
<body>
<h2>Here are the Office details:</h2>
<table>
<tr>
		<th>City</th>
		<th>State</th>
		<th>Country</th>
		<th>Address</th>
		<th>Territory</th>
		<th>Office Phone</th>
		<th>Office Postal Code</th>
	</tr>
	
	
    <c:forEach items="${officeInfo}" var="officeModel">
        <tr>
            <td><c:out value="${officeModel.getCity()}" /></td>
             <td><c:out value="${officeModel.getState()}" /></td>
              <td><c:out value="${officeModel.getCountry()}" /></td>
               <td><c:out value="${officeModel.getAddressLine1()}" /></td>
                <td><c:out value="${officeModel.getTerritory()}" /></td>
        	 <td><c:out value="${officeModel.getPhone()}" /></td>
        	  <td><c:out value="${officeModel.getPostalCode()}" /></td>
               
           
        </tr>
    </c:forEach>
</table>
</body>
</html>
