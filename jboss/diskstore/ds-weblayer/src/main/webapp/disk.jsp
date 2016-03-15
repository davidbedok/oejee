<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>:: Disk ::</title>
</head>
<body>
    <jsp:useBean id="disk" class="hu.qwaevisz.diskstore.ejbserviceclient.domain.DiskStub" scope="request" />
    <h1><jsp:getProperty name="disk" property="author" />: <jsp:getProperty name="disk" property="title" /></h1>
    <div><label>Reference: </label><span><jsp:getProperty name="disk" property="reference" /></span></div>
    <div><label>Category: </label><span><jsp:getProperty name="disk" property="category" /></span></div>
    <div><label>Price: </label><span><jsp:getProperty name="disk" property="price" /> Ft</span></div>
    <div><label>Number of songs: </label><span><jsp:getProperty name="disk" property="numberOfSongs" /></span></div>
    <br/><br/>
    <div>
        <a href="DiskList">back</a>
        <a href="Disk?reference=<% out.print(disk.getReference()); %>&edit=1">edit</a>
    </div>
</body>
</html>