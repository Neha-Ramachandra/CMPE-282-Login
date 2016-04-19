<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"  %>

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
		<th>Employee-Number</th>
		<th>First-Name</th>
		<th>Employee-Extension</th>
		<th>Employee e-mail id</th>	
	</tr>
	
	
    <c:forEach items="${employeedetails}" var="employeeModel">
        <tr>
            <td><c:out value="${employeeModel.getEmployeeNumber()}" /></td>
             <td><c:out value="${employeeModel.getFirstName()}" /></td>
              <td><c:out value="${employeeModel.getExtension()}" /></td>
               <td><c:out value="${employeeModel.getEmail()}" /></td>
           
        </tr>
    </c:forEach>
</table>
</body>
</html>
