<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub" %>
<%@ page import="hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskCategoryStub" %>
<%@ page import="hu.qwaevisz.diskstore.weblayer.common.DiskAttribute" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>:: Disk ::</title>
</head>
<body>
	<div class="frame">
		<form method="post" action="Disk">
			<div>
				<input class="inputheader" type="text" name="author" value="${requestScope.disk.author}" />:
				<input class="inputheader" type="text" name="title" value="${requestScope.disk.title}" />
				<br/><br/>
			</div>
			<div>
				<label>Reference: </label>
				<c:choose>
                     <c:when test="${requestScope.isnew}">
                        <input type="text" name="reference" value="" />
                     </c:when>
                     <c:otherwise>
                        <span>${requestScope.disk.reference}</span>
                        <input type="hidden" name="reference" value="${requestScope.disk.reference}" />
                     </c:otherwise>
                </c:choose>
			</div>
			<div>
				<label for="category">Category: </label>
				<select name="category" id="category">
					<c:forEach items="<%= DiskCategoryStub.values() %>" var="category">
						<option value="${category.name}" ${category eq requestScope.disk.category ? "selected=\"selected\"" : ""}>${category.name}</option>
					</c:forEach>				
				</select>
			</div>
			<div>
				<label for="price">Price: </label>
				<input type="number" name="price" id="price" value="${requestScope.disk.price}" /> Ft
			</div>
			<div>
				<label for="numberofsongs">Number of songs: </label>
				<input type="number" name="numberofsongs" id="numberofsongs" value="${requestScope.disk.numberOfSongs}" />
			</div>
			<br/><br/>
			<div>
				<input type="submit" value="Save" />&nbsp;
				<a href="Disk?reference=${requestScope.disk.reference}">cancel</a>
			</div>
		</form>
	</div>
</body>
</html>