<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Here are the employee details</title>
</head>
<body>
<table>
<tr>
		<th>Employee Number</th>
		<th>Name</th>
		<th>Extension</th>
		<th>Email</th>
		<th>Office Code</th>
		<th>Reports To</th>
		<th>Job Title</th>
		<th></th>
	</tr>
	
	
    <c:forEach items="${}" var="employeeModel">
        <tr>
            <td><c:out value="${employeeModel.getEmployeeNumber()}" /></td>
             <td><c:out value="${employeerModel.getEmployeeName()}" /></td>
              <td><c:out value="${employeeModel.getExtension()}" /></td>
              <td><c:out value="${employeeModel.getEmail()}" /></td>
               <td><c:out value="${employeeModel.getOfficeCode()}" /></td>
                <td><c:out value="${employeeModel.getReportsTo()}" /></td>
                 <td><c:out value="${employeeModel.getJobTitle()}" /></td>
           
        </tr>
    </c:forEach>
</table>
</body>
</html>
