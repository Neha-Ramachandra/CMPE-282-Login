<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee details by Code</title>
</head>
<body>
<h2>Here are the Employee details By Code:</h2>
<table>
<tr>
		<th>Employee Number</th>
		<th>First Name</th>
		<th>Last Name</th>	
	</tr>
	
	
    <c:forEach items="${employeeInfo}" var="employeeModel">
        <tr>
            <td><c:out value="${employeeModel.getEmployeeNumber()}" /></td>
             <td><c:out value="${employeeModel.getFirstName()}" /></td>
              <td><c:out value="${employeeModel.getLastName()}" /></td>
           
        </tr>
    </c:forEach>
</table>
</body>
</html>
