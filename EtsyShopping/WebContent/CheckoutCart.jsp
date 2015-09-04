<%-- <%@page import="model.Userprofile"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Store</title>
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
	font-family: "Time New Roman";
	color: Black;
	background-color: url('image.jpg');
	background-size: cover;
	font-size: 20px;
}
</style>

</head>

<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-brand">Etsy Web Store</div>
		<div>
			<ul class="nav navbar-nav">
				<%-- <li><a href="Welcome.jsp" style="color: white"><b>Home</b></a></li>
				<%
					Userprofile user = (Userprofile) session.getAttribute("user");
					if (user != null) {
						request.setAttribute("userName", user.getUserName());
				%>
				<li><a href="Cart" style="color: white"><b>${userName}'s
							Cart</b></a></li>
				<li><a href="ExploreProducts?logout=y" style="color: white"><b>Logout</b></a></li>
				<%
					}
				%> --%>
			</ul>
		</div>
	</div>
	</nav>
<!-- 	<a href="ExploreProducts?goto=y"
		class="btn pull-right btn-primary btn-lg">Go Back</a>
	<br> -->
	<br>
	<h1 align=center>
		<b>Confirm your order</b><br> <br>
	</h1>
		<div>${message}</div>


</body>
</html>