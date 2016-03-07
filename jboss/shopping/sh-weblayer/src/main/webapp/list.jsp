<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Set" %>  
<%@ page import="java.util.List" %>
<%@ page import="hu.qwaevisz.shopping.report.domain.Transaction" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>:: Shopping ::</title>
</head>
<body>
    <h1>List of transactions</h1>
    <table class="bookstable">
        <thead>
            <tr>
            	<th>ID</th>
            	<th>Date</th>
                <th>Comment</th>
                <th>Number of items</th>
                <th>Total price</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.transactions}" var="transaction">
                <tr>
                	<td><a href="Report?id=<c:out value="${transaction.id}"/>" target="_BLANK"><c:out value="${transaction.id}" /></a></td>
                	<td><c:out value="${transaction.date}" /></td>
                    <td><c:out value="${transaction.comment}" /></td>
                    <td><c:out value="${transaction.numberOfItems}" /></td>
                    <td><c:out value="${transaction.totalPrice}" /> Ft</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>