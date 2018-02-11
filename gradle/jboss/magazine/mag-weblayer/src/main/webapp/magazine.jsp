<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="hu.qwaevisz.magazine.ejbservice.domain.MagazineStub" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>:: Magazine ::</title>
</head>
<body>
    <jsp:useBean id="magazine" class="hu.qwaevisz.magazine.ejbservice.domain.MagazineStub" scope="request" />
    <h1><jsp:getProperty name="magazine" property="publisher" />: <jsp:getProperty name="magazine" property="title" /></h1>
    <div><label>Reference: </label><span><jsp:getProperty name="magazine" property="reference" /></span></div>
    <div><label>Number of pages: </label><span><jsp:getProperty name="magazine" property="numberOfPages" /></span></div>
    <div><label>Price: </label><span><jsp:getProperty name="magazine" property="price" /></span></div>
    <div><label>Category: </label><span>${requestScope.magazine.category.label}</span></div>
    <br/><br/>
    <div>
        <a href="MagazineList">back</a>
        <% if (request.isUserInRole("magadmin")) { %>
            <a href="Magazine?reference=<% out.print(magazine.getReference()); %>&edit=1">edit</a>
        <% } %>
    </div>
</body>
</html>