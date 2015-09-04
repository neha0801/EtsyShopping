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
<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-brand">Etsy Web Store</div>
	<div>
<%-- 		<ul class="nav navbar-nav">
			<li><a href="Welcome.jsp" style="color: white"><b>Home</b></a></li>
			<% Etsyuser user = (Etsyuser)session.getAttribute("user");
			if(user!=null){  request.setAttribute("userName", user.getName()); %>
				<li><a href="Cart" style="color: white"><b>${userName}'s Cart</b></a></li>
				<li><a href="ExploreProducts?logout=y" style="color: white"><b>Logout</b></a></li>
			<%}	%>
		</ul> --%>
	</div>
</div>
</nav>
<body>
<a href="ExploreProducts?goto=y" class="btn pull-right btn-primary btn-lg"  >Go Back</a><br><br>
		<h1 align=center>
				<b>Your Cart 
				<%-- ${count} 
				<% int a = Integer.parseInt(request.getAttribute("count").toString());
				if(a>1) {%>
					items 
					<%} else {%>
					item<% }%></b><br><br> --%>
			
			</h1>	
		${cartData}			
	<div>${buttons}</div>
</body>
</html>