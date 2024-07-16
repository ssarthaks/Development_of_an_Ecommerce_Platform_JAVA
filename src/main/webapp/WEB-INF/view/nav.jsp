<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
   <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"
    />

<link rel= "stylesheet" type = "text/css" href ="<%= request.getContextPath() %>/css/util.css">
<script src="<%= request.getContextPath() %>/js/util.js" ></script>
<style>
	.btn {
    border-radius: 50px;
    border: none;
    outline: 0;
    padding: 5px 5px;
    color: black;
    background-color: white;
    text-align: center;
    cursor: pointer;
    font-size: 10px;
    border: solid black 1px;
} 

.btn:hover {
    background-color: rgba(0, 0, 0, 0.3);
}
	
</style>
</head>

<body>
	    <header class="header">
		<a href="<%= request.getContextPath() %>/ViewIndex" class="nav-logo">
		    <img src="<%= request.getContextPath() %>/assets/logo.png" alt="logo" width="75px" class="nav-logo" />
		</a>
      <div class="navItems">
		<form action="<%= request.getContextPath() %>/Search" method="GET">
		    <input
		        type="search"
		        class="search searchStyle"
		        placeholder="Search"
		        name="search"
		        onkeypress="return submitOnEnter(event)"
		    />
		</form>
		<div class="navSubItems">
          <a href="<%= request.getContextPath() %>/myorder">
				    <span class="material-symbols-outlined">
		order_approve
		</span>
		</a>
        <div class="navSubItems">
          <a href="<%= request.getContextPath() %>/Cart">
		    <span class="material-symbols-outlined"> shopping_cart </span>
		</a>

        </div>
		<div class="navSubItems">
		    
		    <% 
		        // Check if a user is logged in
		        if (session.getAttribute("email") != null) {
		            // If logged in, display the username and logout button
		    %>
			<a href="Profile" style="display:flex; justify-content:center; align-items:center">
				<span class="material-symbols-outlined"> person </span>
			  <p class="navText"><%= session.getAttribute("username") %></p>
			</a>

		            <form action="logout" method="post">
		                <button type="submit" class= "btn">Logout</button>
		            </form>
		    <% 
		        } else {
		            // If not logged in, display a login button
		    %>
		            <a href="login" class="navText">Login</a>
		    <% 
		        }
		    %>
		</div>

      </div>
    </header>
    <nav class="navbar">
      <ul class="nav-menu">
        <li class="nav-item">
          <a href="<%= request.getContextPath() %>/ViewIndex" class="nav-link">Home</a>
        </li>
        <li class="nav-item">
          <a href="<%= request.getContextPath() %>/ViewProduct" class="nav-link">Products</a>
        </li>
        <li class="nav-item">
          <a href="<%= request.getContextPath() %>/AboutUs" class="nav-link">About Us</a>
        </li>
        <li class="nav-item">
          <a href="<%= request.getContextPath() %>/ContactUs"  class="nav-link">Contact Us</a>
        </li>
      </ul>

      <div class="hamburger">
        <span class="bar"></span>
        <span class="bar"></span>
        <span class="bar"></span>
      </div>
      <input
        type="text"
        class="searchHamburger searchStyle"
        placeholder="Search"
        name="search"
      />
    </nav>
</body>
</html>