<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Etsy Shopping</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-default">
<div class="container-fluid">
<div class="navbar-header">
<a class="navbar-brand" href="EtsyProduct">Etsy Shopping</a>
</div>
<div>
<ul class="nav navbar-nav navbar-right">
<% if (session.getAttribute("userName") == null) { %>
<li><a href="Signup.jsp">Signup</a></li>
<li><a href="Login.jsp">Login</a></li>
<% } else {%>
<li><a href="UserItemServlet">View My Products</a>
<li><a href="ShowHistOrders">View Order History</a></li>
<li><a href="ShoppingCart.jsp">View Cart</a></li>
<li><a href="Login?logout=true">Logout</a></li>
<% } %>
</ul>
</div>
</div>
</nav>
${details}

</body>
</html>