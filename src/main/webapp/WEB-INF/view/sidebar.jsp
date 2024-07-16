<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel= "stylesheet" type = "text/css" href ="<%= request.getContextPath() %>/css/util.css">
<style>
.sidebar {
    width: 200px;
    background-color: black;
    color: #fff;
    padding: 20px;
    flex: 0 0 auto; /* Sidebar should not grow or shrink */
    height: 100vh; /* Fill the full height */
}

.content {
    flex: 1;
    padding: 20px;
    height: 100%; /* Fill the full height */
}

li {
    list-style: none;
    padding: 10px 0px;
}

a {
    color: white;
    text-decoration: none;
    font-size: 18px; /* Adjust the font size as needed */
}
</style>
</head>
<body>
	<div class="sidebar">
            <!-- Sidebar content here -->
            <ul>
                <li><a href="AdminDashboard">Dashboard</a></li>
                <li><a href="AdminUser">Users</a></li>
                <li><a href="AdminProduct">Products</a></li>
                <li><a href="AdminOrder">Orders</a></li>
                <li><a href="Allfeedback">Feedbacks</a></li>
                <li><a href="logout">Logout</a></li>
            </ul>
        </div>
</body>
</html>