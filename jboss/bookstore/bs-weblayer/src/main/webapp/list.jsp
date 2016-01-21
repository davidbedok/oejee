<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Set" %>  
<%@ page import="java.util.List" %>
<%@ page import="hu.qwaevisz.bookstore.ejbservice.domain.BookStub" %> 
<% List<BookStub> books = (List<BookStub>) request.getAttribute("books"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>:: Books ::</title>
</head>
<body>
	<h1>List of Books</h1>
	<table class="bookstable">
		<thead>
			<tr>
				<th>Author</th>
				<th>Title</th>
				<th>Category</th>
				<th>Price</th>
				<th>Number of pages</th>
			</tr>
		</thead>
		<tbody>
			<% for ( BookStub book : books) { %>
				<tr>
					<td><% out.print(book.getAuthor()); %></td>
					<td><a href="Book?isbn=<% out.print(book.getIsbn()); %>"><% out.print(book.getTitle()); %></a></td>
					<td><% out.print(book.getCategory()); %></td>
					<td><% out.print(book.getPrice()); %> Ft</td>
					<td><% out.print(book.getNumberOfPages()); %></td>
				</tr>
			<% } %>
		</tbody>
	</table>
</body>
</html>