<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>:: Disk ::</title>
<fmt:setLocale value="hu_HU"/>
</head>
<body>    
    <h1>${requestScope.disk.author}: ${requestScope.disk.title}</h1>
    <div><label>Reference: </label><span>${requestScope.disk.reference}</span></div>
    <div><label>Category: </label><span>${requestScope.disk.category}</span></div>
    <div><label>Price: </label><span><fmt:formatNumber type="currency" value="${requestScope.disk.price}" /></span></div>
    <div><label>Number of songs: </label><span>${requestScope.disk.numberOfSongs}</span></div>
    <br/><br/>
    <div>
        <a href="DiskList">back</a>
        <a href="Disk?reference=${requestScope.disk.reference}&edit=1">edit</a>
    </div>
</body>
</html>