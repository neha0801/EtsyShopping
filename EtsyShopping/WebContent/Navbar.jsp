<%@page import="model.Etsyuser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Etsy Online Store</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
h1 {
	text-align: center;
	color: black;
	font-size: 50px;
	font-family: "Edwardian Script ITC";
}

body {
	font-family: "Times new Roman";
	color: black;
	font-size: 15px;
}

 
</style>
</head>

<body>
<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-brand">Etsy Web Store</div>
	<ul class="nav navbar-nav">
	<li><a href="EtsyProduct">Explore</a></li></ul>
	<div>
<ul class="nav navbar-nav navbar-right">

<% if (session.getAttribute("user") == null) { %>
<li><a href="Signup.jsp">Signup</a></li>
<li><a href="Login.jsp">Login</a></li>
<% } else {%>
<li><a href="UserItemServlet">View My Products</a>
<li><a href="OrderHistory">View Order History</a></li>
<li><a href="TempCart">View Cart</a></li>
<li><a href="Login?logout=true">Logout</a></li>
<% } %>
</ul>
	</div>
</div>
</nav>
</body>
</html>