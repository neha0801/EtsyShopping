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
<jsp:include page="Navbar.jsp"/>
<script type="text/javascript">
function validateForm() {
	var a = document.forms["SignupForm"]["name"].value;
    var b = document.forms["SignupForm"]["email"].value;
    var c = document.forms["SignupForm"]["password"].value;
    if (a == null || a == "" || b == null || b == "" || c == null || c == "") {
        alert("All fields must be filled out.");
        return false;
    }
}
function validateEmail() {
	alert("User already exists.");
}
</script>

<div class="container">
<h3>Signup</h3>
<br />
<form class="form-horizontal" role="form" name="SignupForm" id="SignupForm" onsubmit="return validateForm()" action="Login" method="post">
<div class="form-group">
<label class="control-label col-sm-2" for="name">User Name:</label>
<div class="col-sm-10">
<input type="text" class="form-control" name="name" id="name" placeholder="Enter user name">
</div>
</div>
<div class="form-group">
<label class="control-label col-sm-2" for="email">Email:</label>
<div class="col-sm-10">
<input type="text" class="form-control" name="email" id="email" placeholder="Enter email">
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
<button type="submit" class="btn btn-default" name="SignupSub" id="SignupSub">Sign up</button>
</div>
</div>
</form>
</div>
${signupErr}

</body>
</html>