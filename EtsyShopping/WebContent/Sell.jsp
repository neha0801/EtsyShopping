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
<style>
h1 {
    text-align: center;
}

</style>
<script type="text/javascript">
function validateForm() {
    var a = document.forms["itemForm"]["name"].value;
    var b = document.forms["itemForm"]["picture"].value;
    var c = document.forms["itemForm"]["description"].value;
    var d = document.forms["itemForm"]["price"].value;
    var e = document.forms["itemForm"]["shipping"].value;
    
    if (a == null || a == "" || b == null || b == "" || c == null || c == "" || d == null || d == "" || e == null || e == "") {
        alert("All fields must be filled out.");
        return false;
    }
}
</script>
<!-- 
<script>
function success() {
	<div class="alert alert-success">
		<strong>Success!</strong> This alert box could indicate a successful or positive action.
	</div>
}
</script>
 -->
</head>
<body>
<nav class="navbar navbar-default">
<div class="container-fluid">
<div class="navbar-header">
<a class="navbar-brand" href="EtsyProduct">EtsyShopping</a>
</div>
<div>
<ul class="nav navbar-nav navbar-right">
<% if (session.getAttribute("name") == null) { %>
<li><a href="Signup.jsp">Signup</a></li>
<li><a href="Login.jsp">Login</a></li>
<% } else { %>
<li><a href="UserItemServlet">View My Products</a>
<li><a href="ShowHistOrders">View Historical Orders</a></li>
<li><a href="ShoppingCart.jsp">View Cart</a></li>
<li><a href="Login?logout=true">Logout</a></li>
<% } %>
</ul>
</div>
</div>
</nav>
<div class="container">
<h1>Submit your item</h1>
<br />
<form class="form-horizontal" role="form" name="itemForm" id="Form" onsubmit="return validateForm()" action="Sell" method="post">
<div class="form-group">
<label class="control-label col-sm-2" for="name">Enter Item Name:</label>
<div class="col-sm-10">
<input type="text" class="form-control" name="name" id="name" placeholder="Enter item name">
</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2" for="picture">Insert picture url:</label>
<div class="col-sm-10">
<input type="text" class="form-control" name="picture" id="picture" placeholder="Enter url">
</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2" for="description">Enter item description:</label>
<div class="col-sm-10">
<textarea class="form-control" name="description" id="description" placeholder="Enter description"></textarea>
</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2" for="price">Enter item price:</label>
<div class="col-sm-10">
<input type="text" class="form-control" name="price" id="price" placeholder="Enter price">
</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2" for="shipping">Enter shipping cost:</label>
<div class="col-sm-10">
<input type="text" class="form-control" name="shipping" id="shipping" placeholder="Enter shipping cost">
</div>
</div>
<div class="form-group">
<div class="col-sm-offset-2 col-sm-10">
<button type="submit" class="btn btn-default" name="submit" id="submit">Submit</button>
</div>
</div>
</form>
</div>

</body>
</html>