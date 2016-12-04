<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="style/page.css" />
	<title>:: GSP ::</title>
</head>
<body>
    <h1>List of Inventory (books)</h1>
    <% if ( request.getAttribute('inventories').isEmpty() ) { %>
    	<span>Inventory list is <strong>empty</strong>.</span>
    <% } else { %>   
	    <table class="inventorytable">
	        <thead>
	            <tr>
	                <th>Reference</th>
	                <th>Name</th>
	                <th>Type</th>
	                <th>Value</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<% request.getAttribute('inventories').each { %>
	                <tr>
	                    <td>${it.reference}</td>
	                    <td>${it.name}</td>
	                    <td>${it.type}</td>
	                    <td>${it.value}</td>
	                </tr>
	            <% } %>
	        </tbody>
	    </table>
    <% } %>
</body>
</html>