<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Set" %>  
<%@ page import="java.util.List" %>
<%@ page import="hu.qwaevisz.inventory.webservice.common.ListAttribute" %>
<%@ page import="hu.qwaevisz.inventory.persistence.domain.InventoryItem" %>
<%@ page import="hu.qwaevisz.inventory.persistence.domain.InventoryType" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="style/page.css" />
	<title>:: JSP ::</title>
</head>
<body>
    <h1>List of Inventory (books)</h1>
	<c:choose>
	    <c:when test="${requestScope.inventories.isEmpty()}">
            <span>Inventory list is <strong>empty</strong>.</span>
        </c:when>
        <c:otherwise>
            <table class="inventorytable">
                <thead>
                    <tr>
                        <th>Reference</th>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Value</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.inventories}" var="item">
                        <tr>
                            <td><c:out value="${item.reference}" /></td>
                            <td><c:out value="${item.name}" /></td>
                            <td><c:out value="${item.type}" /></td>
                            <td><c:out value="${item.value}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
	</c:choose>
</body>
</html>