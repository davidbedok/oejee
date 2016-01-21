<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Set" %>  
<%@ page import="hu.qwaevisz.bookstore.ejbservice.domain.BookStub" %> 
<% BookStub book = (BookStub) request.getAttribute("book"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>:: <% out.print(book.getTitle()); %> ::</title>
</head>
<body>
	<h1><% out.print(book.getAuthor()); %>: <% out.print(book.getTitle()); %></h1>
	<div><label>ISBN: </label><span><% out.print(book.getIsbn()); %></span></div>
	<div><label>Number of pages: </label><span><% out.print(book.getNumberOfPages()); %></span></div>
	<div><label>Price: </label><span><% out.print(book.getPrice()); %> Ft</span></div>
	<div><label>Category: </label><span><% out.print(book.getCategory()); %></span></div>	
	<br/><br/>
	<div><a href="BookList">back</a></div>
</body>
</html>