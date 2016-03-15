<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub" %>
<%@ page import="hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskCategoryStub" %>
<%@ page import="hu.qwaevisz.diskstore.weblayer.common.DiskAttribute" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% DiskStub disk = (DiskStub) request.getAttribute(DiskAttribute.ATTR_DISK); %>
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
				<input class="inputheader" type="text" name="author" value="<% out.print(disk.getAuthor()); %>" />:
				<input class="inputheader" type="text" name="title" value="<% out.print(disk.getTitle()); %>" />
				<br/><br/>
			</div>
			<div>
				<label>Reference: </label>
				<c:choose>
                     <c:when test="${requestScope.isnew}">
                        <input type="text" name="reference" value="" />
                     </c:when>
                     <c:otherwise>
                        <span><% out.print(disk.getReference()); %></span>
                        <input type="hidden" name="reference" value="<%= disk.getReference() %>" />
                     </c:otherwise>
                </c:choose>
			</div>
			<div>
				<label for="category">Category: </label>
				<select name="category" id="category">
					<% for ( DiskCategoryStub category : DiskCategoryStub.values()) { %>
						<option value="<% out.print(category.name()); %>" <% out.print( category == disk.getCategory() ? "selected=\"selected\"" : "" ); %> ><% out.print(category.name()); %></option>
					<% } %>
				</select>
			</div>
			<div>
				<label for="price">Price: </label>
				<input type="number" name="price" id="price" value="<% out.print(disk.getPrice()); %>" /> Ft
			</div>
			<div>
				<label for="numberofsongs">Number of songs: </label>
				<input type="number" name="numberofsongs" id="numberofsongs" value="<% out.print(disk.getNumberOfSongs()); %>" />
			</div>
			<br/><br/>
			<div>
				<input type="submit" value="Save" />&nbsp;
				<a href="Disk?reference=<% out.print(disk.getReference()); %>">cancel</a>
			</div>
		</form>
	</div>
</body>
</html>