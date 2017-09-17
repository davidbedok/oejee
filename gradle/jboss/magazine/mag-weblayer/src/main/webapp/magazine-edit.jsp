<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="hu.qwaevisz.magazine.ejbservice.domain.MagazineStub" %>
<%@ page import="hu.qwaevisz.magazine.ejbservice.domain.MagazineCategoryStub" %>
<%@ page import="hu.qwaevisz.magazine.weblayer.common.MagazineAttribute" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% MagazineStub magazine = (MagazineStub) request.getAttribute(MagazineAttribute.ATTR_MAGAZINE); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>:: Magazine ::</title>
</head>
<body>
	<div class="frame">
		<form method="post" action="Magazine">
			<div>
				<input class="inputheader" type="text" name="publisher" value="<% out.print(magazine.getPublisher()); %>" />:
				<input class="inputheader" type="text" name="title" value="<% out.print(magazine.getTitle()); %>" />
				<br/><br/>
			</div>
			<div>
				<label>Reference: </label>
				<c:choose>
                     <c:when test="${requestScope.isnew}">
                        <input type="text" name="reference" value="" />
                     </c:when>
                     <c:otherwise>
                        <span><% out.print(magazine.getReference()); %></span>
                        <input type="hidden" name="reference" value="<%= magazine.getReference() %>" />
                     </c:otherwise>
                </c:choose>
			</div>
			<div>
				<label for="numberofpages">Number of pages: </label>
				<input type="number" name="numberofpages" id="numberofpages" value="<% out.print(magazine.getNumberOfPages()); %>" />
			</div>
			<div>
				<label for="price">Price: </label>
				<input type="number" name="price" id="price" value="<% out.print(magazine.getPrice()); %>" /> Ft
			</div>
			<div>
				<label for="category">Category: </label>
				<select name="category" id="category">
					<% for ( MagazineCategoryStub category : MagazineCategoryStub.values()) { %>
						<option value="<% out.print(category.name()); %>" <% out.print( category == magazine.getCategory() ? "selected=\"selected\"" : "" ); %> ><% out.print(category.getLabel()); %></option>
					<% } %>
				</select>
			</div>
			<br/><br/>
			<div>
				<input type="submit" value="Save" />&nbsp;
				<a href="Magazine?reference=<% out.print(magazine.getReference()); %>">cancel</a>
			</div>
		</form>
	</div>
</body>
</html>