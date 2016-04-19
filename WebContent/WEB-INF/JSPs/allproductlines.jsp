<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<!DOCTYPE HTML>
<html>
<head>
<title> Product line details</title>
</head>
<body>
<table>
<tr>
		<th>Product Line</th>
		<th>Text Description</th>
		<th>HTML Description</th>
		<th>Image</th>
		<th></th>
	</tr>
	
	
    <c:forEach items="${productlines}" var="productLineModel">
        <tr>
            <td><c:out value="${productLineModel.getProductLine()}" /></td>
             <td><c:out value="${productLineModel.getHtmlDescription()}" /></td>
             <td><c:out value="${productLineModel.getTextDescription()}" /></td>
              <td><c:out value="${productLineModel.getImage()}" /></td>
             
           
        </tr>
    </c:forEach>
</table>
</body>
</html>


