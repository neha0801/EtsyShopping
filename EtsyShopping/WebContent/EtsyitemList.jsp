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
<div class="container">
<!-- <form class="form-inline" role="form" name="search" id="search" action="EtsyProduct" method="post">
  <div class="form-group">
    <label for="keyword">Keyword:</label>
    <input type="text" class="form-control" name="keyword" id="keyword">
  </div>
  <button type="submit" class="btn btn-default" name="keywordSearch" id="keywordSearch">Submit</button>
</form> -->
</div>
<div class="container">
<br />
<table class="table table-striped">
<thead>
<tr>
<th width="60%">Product Name</th>
<th width="40%">Price</th>
</tr>
</thead>
<tbody>
${productListMsg}
</tbody>
</table>
<a href="Sell" class="btn btn-info" role="button">Sell</a>
</div>

</body>
</html>