<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Here are all the employee details of the company:</title>
</head>
<body>
<table>
<tr>
		<th>Employee Number</th>
		<th>Last Name</th>
		<th>First Name</th>
		<th>Extension</th>
		<th>Email</th>
		<th>Office Code</th>
		<th>Reports To</th>
		<th>Job Title</th>
		<th></th>
	</tr>
	
    <c:forEach items="${employeedetails}" var="employeeModel">
        <tr>
            <td><c:out value="${employeeModel.getEmployeeNumber()}" /></td>
             <td><c:out value="${employeerModel.getLastName()}" /></td>
              <td><c:out value="${employeeModel.getFirstName()}" /></td>
               <td><c:out value="${employeeModel.getExtension()}" /></td>
              <td><c:out value="${employeeModel.getEmail()}" /></td>
               <td><c:out value="${employeeModel.getOfficeCode()}" /></td>
                <td><c:out value="${employeeModel.getReportsToEmployeeNumber()}" /></td>
                 <td><c:out value="${employeeModel.getJobTitle()}" /></td>
           
        </tr>
    </c:forEach>
</table>
</body>
</html>
