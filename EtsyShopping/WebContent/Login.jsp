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
<li><a href="Signup.jsp">Signup</a></li>
</ul>
</div>
</div>
</nav>
<script type="text/javascript">
function validateForm() {
    var a = document.forms["LoginForm"]["name"].value;
    var b = document.forms["LoginForm"]["password"].value;
    if (a == null || a == "" || b == null || b == "") {
        alert("All fields must be filled out.");
        return false;
    }
}
function validateName() {
	alert("User does not exist.");
}
function validatePassword() {
	alert("Password is incorrect.");
}
</script>

<div class="container">
<h3>Login</h3>
<br />
<form class="form-horizontal" role="form" name="LoginForm" id="LoginForm" onsubmit="return validateForm()" action="Login" method="post">
<div class="form-group">
<label class="control-label col-sm-2" for="name">User Name:</label>
<div class="col-sm-10">
<input type="text" class="form-control" name="name" id="name" placeholder="Enter user name">
</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2" for="password">Password:</label>
<div class="col-sm-10">
<input type="password" class="form-control" name="password" id="password" placeholder="Enter password">
</div>
</div>
<div class="form-group">
<div class="col-sm-offset-2 col-sm-10">
<button type="submit" class="btn btn-default" name="LoginSub" id="LoginSub">Log in</button>
</div>
</div>
</form>
</div>
${loginErr}

</body>
</html>