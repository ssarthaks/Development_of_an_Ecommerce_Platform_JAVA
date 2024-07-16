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

</head>
<body>
       <footer>
      <div class="footerContainer">
        <div class="footerNav">
          <ul>
            <li><a href="<%= request.getContextPath() %>/ViewIndex">Home</a></li>
            <li><a href="<%= request.getContextPath() %>/ViewProduct">Products</a></li>
            <li><a href="<%= request.getContextPath() %>/AboutUs">About Us</a></li>
            <li><a href="<%= request.getContextPath() %>/ContactUs">Contact Us</a></li>

          </ul>
        </div>
      </div>
      <div class="footerBottom">
        <p>
          Copyright | Designed by
          <span class="designer">Praneet Srijan Sarthak</span>
        </p>
      </div>
    </footer>
</body>
</html>