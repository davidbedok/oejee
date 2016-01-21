<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Set" %>  
<%@ page import="java.util.List" %>
<%@ page import="hu.qwaevisz.bookstore.weblayer.common.ListAttribute" %>
<%@ page import="hu.qwaevisz.bookstore.weblayer.common.FormValue" %>
<%@ page import="hu.qwaevisz.bookstore.ejbservice.domain.BookStub" %>
<%@ page import="hu.qwaevisz.bookstore.ejbservice.domain.BookCategoryStub" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://qwaevisz.hu/jsp/tlds/booktag" prefix="bt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>:: Books ::</title>
</head>
<body>
    <bt:header>List of Books</bt:header>
	<form method="post" action="BookList">
		<div>
			<label for="category">Category: </label>
			<select name="category" id="category">
			    <% String categoryName = (String) request.getAttribute(ListAttribute.ATTR_CATEGORY); %>
				<option value="-1" <% out.print( FormValue.FILTER_ALL_CATEGORY.equals(categoryName) ? "selected=\"selected\"" : "" ); %>>-</option>
			    <c:set var="categoryValues" value="<%= BookCategoryStub.values() %>"/>
				<c:forEach items="${categoryValues}" var="category">
					<option value="${category.name}" ${category.name eq requestScope.category ? "selected=\"selected\"" : ""}>${category.label}</option>
				</c:forEach>
			</select>
			<input type="submit" value="Filter" />
		</div>
	</form>
	<br/><br/><br/>
	<c:choose>
	    <c:when test="${requestScope.books.isEmpty()}">
            <span>Book list is <strong>empty</strong>.</span>
        </c:when>
        <c:otherwise>
            <table class="bookstable">
                <thead>
                    <tr>
                        <th>Author</th>
                        <th>Title</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Number of pages</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.books}" var="book">
                        <tr>
                            <td><c:out value="${book.author}" /></td>
                            <td><a href="Book?isbn=<c:out value="${book.isbn}" />"><c:out value="${book.title}" /></a></td>
                            <td><c:out value="${book.category.label}" /></td>
                            <td><bt:formatPrice value="${book.price}"/></td>
                            <td><c:out value="${book.numberOfPages}" /></td>
                            <td><a href="BookDelete?isbn=<c:out value="${book.isbn}" />">delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br/>
            <i>Hint: Price of the first book is <bt:formatPrice value="${requestScope.books[0].price}"/>.</i>
        </c:otherwise>
	</c:choose>
	<br/><br/>
	<div>
	    <a href="Book?isbn=-1&edit=1">Create</a> a brand new book.
	</div>
</body>
</html>