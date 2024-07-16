<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>404 Not Found</title>
<style>
	*{
		margin: 0px;
		padding: 0px;
	}

	h1, p{
	color: black
	}
	.large{
		font-size: 200px;
	}
	.flex{
	height:66vh;
		display: flex;
		justify-content:center;
		align-items:center;
		flex-direction:column;
	}
</style>
</head>
<body>
	<%@ include file="nav.jsp" %>
	<div class= "flex">
		<p class = 'large'>404</p>
		<h1>Page not found</h1>
		<p>The requested page was not found in this server</p>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>