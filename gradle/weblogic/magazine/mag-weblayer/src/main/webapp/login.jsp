<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="hu.qwaevisz.magazine.weblayer.common.LoginAttribute" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/page.css" />
<title>:: Login ::</title>
</head>
<body>
	<% 
		String userName = (String) request.getAttribute(LoginAttribute.ATTR_USERNAME); 
		String errorMessage = (String) request.getAttribute(LoginAttribute.ATTR_ERROR); 
	%>
	<form action="j_security_check" method="post">
		<fieldset>
			<legend>Login</legend>
			<div class="error"><%= errorMessage %></div>
			<div>
				<label for="username">Username: </label>
				<input type="text" name="j_username" id="username" value="<%= userName %>" />
			</div>
			<div>
				<label for="password">Password: </label>
				<input type="password" name="j_password" id="password" />
			</div>
			<input type="submit" name="submit" value="Login" />
		</fieldset>
	</form>
</body>
</html>