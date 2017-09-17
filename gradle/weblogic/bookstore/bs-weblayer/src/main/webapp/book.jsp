<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="hu.qwaevisz.bookstore.ejbservice.domain.BookStub" %>
<%@ taglib uri="http://qwaevisz.hu/jsp/tlds/booktag" prefix="bt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>:: Book ::</title>
</head>
<body>
    <jsp:useBean id="book" class="hu.qwaevisz.bookstore.ejbservice.domain.BookStub" scope="request" />
    <h1><jsp:getProperty name="book" property="author" />: <jsp:getProperty name="book" property="title" /></h1>
    <div><label>ISBN: </label><span><jsp:getProperty name="book" property="isbn" /></span></div>
    <div><label>Number of pages: </label><span><jsp:getProperty name="book" property="numberOfPages" /></span></div>
    <div><label>Price: </label><span><bt:formatPrice value="${requestScope.book.price}"/></span></div>
    <div><label>Category: </label><span>${requestScope.book.category.label}</span></div>
    <br/><br/>
    <div>
        <a href="BookList">back</a>
        <a href="Book?isbn=<% out.print(book.getIsbn()); %>&edit=1">edit</a>
    </div>
</body>
</html>