<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="hu.qwaevisz.bookstore.ejbservice.domain.BookStub" %>
<%@ page import="hu.qwaevisz.bookstore.ejbservice.domain.BookCategoryStub" %>
<%@ page import="hu.qwaevisz.bookstore.weblayer.common.BookAttribute" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% BookStub book = (BookStub) request.getAttribute(BookAttribute.ATTR_BOOK); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>:: Book ::</title>
</head>
<body>
	<div class="frame">
		<form method="post" action="Book">
			<div>
				<input class="inputheader" type="text" name="author" value="<% out.print(book.getAuthor()); %>" />:
				<input class="inputheader" type="text" name="title" value="<% out.print(book.getTitle()); %>" />
				<br/><br/>
			</div>
			<div>
				<label>ISBN: </label>
				<c:choose>
                     <c:when test="${requestScope.isnew}">
                        <input type="text" name="isbn" value="" />
                     </c:when>
                     <c:otherwise>
                        <span><% out.print(book.getIsbn()); %></span>
                        <input type="hidden" name="isbn" value="<%= book.getIsbn() %>" />
                     </c:otherwise>
                </c:choose>
			</div>
			<div>
				<label for="numberofpages">Number of pages: </label>
				<input type="number" name="numberofpages" id="numberofpages" value="<% out.print(book.getNumberOfPages()); %>" />
			</div>
			<div>
				<label for="price">Price: </label>
				<input type="number" name="price" id="price" value="<% out.print(book.getPrice()); %>" /> Ft
			</div>
			<div>
				<label for="category">Category: </label>
				<select name="category" id="category">
					<% for ( BookCategoryStub category : BookCategoryStub.values()) { %>
						<option value="<% out.print(category.name()); %>" <% out.print( category == book.getCategory() ? "selected=\"selected\"" : "" ); %> ><% out.print(category.getLabel()); %></option>
					<% } %>
				</select>
			</div>
			<br/><br/>
			<div>
				<input type="submit" value="Save" />&nbsp;
				<a href="Book?isbn=<% out.print(book.getIsbn()); %>">cancel</a>
			</div>
		</form>
	</div>
</body>
</html>