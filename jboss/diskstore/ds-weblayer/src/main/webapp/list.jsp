<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Set" %>  
<%@ page import="java.util.List" %>
<%@ page import="hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>:: Books ::</title>
</head>
<body>
    <h1>List of Disks</h1>
	<c:choose>
	    <c:when test="${requestScope.disks.isEmpty()}">
            <span>Disk list is <strong>empty</strong>.</span>
        </c:when>
        <c:otherwise>
            <table class="bookstable">
                <thead>
                    <tr>
                        <th>Author</th>
                        <th>Title</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Number of songs</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.disks}" var="disk">
                        <tr>
                            <td><c:out value="${disk.author}" /></td>
                            <td><a href="Disk?reference=<c:out value="${disk.reference}" />"><c:out value="${disk.title}" /></a></td>
                            <td><c:out value="${disk.category}" /></td>
                            <td><c:out value="${disk.price}" /> Ft</td>
                            <td><c:out value="${disk.numberOfSongs}" /></td>
                            <td><a href="DiskDelete?reference=<c:out value="${disk.reference}" />">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
	</c:choose>
	<br/><br/>
	<div>
	    <a href="Disk?reference=-1&edit=1">Create</a> a brand new disk.
	</div>
</body>
</html>