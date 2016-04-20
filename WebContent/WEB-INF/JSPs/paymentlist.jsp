<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE HTML>
<html>
<head>
<title>Payment Details</title>
</head>
<body>
<h2>Here are the payment details:</h2>
<table>
<tr>
		<th>Payment ID</th>
		<th>Amount</th>
		<th>Payment Date</th>

	</tr>
	
	
    <c:forEach items="${paymentdetails}" var="paymentModel">
        <tr>
            <td><c:out value="${paymentinfo.getId()}" /></td>
             <td><c:out value="${paymentinfo.getAmount()}" /></td>
              <td><c:out value="${paymentinfo.getPaymentDate()}" /></td>
              
               
           
        </tr>
    </c:forEach>
</table>
</body>
</html>

