<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <script src="https://kit.fontawesome.com/bc80280643.js" crossorigin="anonymous"></script> 
  <style>
    body {
      font-family: Arial, sans-serif;
      font-size: 14px;
    }
    *, *::before, *::after {
      box-sizing: content-box !important;
    }
    .login-container {
      background-color: #fff;
      border-radius: 8px;
      padding: 40px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      margin: auto; /* Center horizontally */
      width: 300px; /* Adjust the width as desired */
    }
    h2 {
      margin: 0 !important;
      text-align: center;
      margin-bottom: 20px !important;
    }
    .input-container {
      position: relative;
      margin-bottom: 20px;
      padding-right: 20px;
    }
    .input-label {
      position: absolute;
      top: 10px;
      left: 10px;
      color: #999;
      transition: all 0.3s ease-in-out;
      pointer-events: none;
    }
    .input-field {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    .input-field:focus + .input-label, .input-field:not(:placeholder-shown) + .input-label {
      top: -12px;
      left: 5px;
      font-size: 12px;
      color: #333;
      background-color: #fff;
      padding: 0 5px;
    }
    input[type="submit"] {
      padding: 10px 30px;
      background-color: black;
      color: #fff;
      font-weight: bold;
      border: none;
      border-radius: 50px;
      cursor: pointer;
      transition: opacity 0.3s;
    }
    input[type="submit"]:hover {
      opacity: 0.8;
    }
    .register-link {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-top: 40px;
      text-align: left;
    }
    .register-link a {
      color: black;
      padding: 10px;
      font-weight: bold;
      text-decoration: none;
      border-radius: 50px;
      transition: background-color 0.3s;
    }
    .register-link a:hover {
      background-color: rgba(0, 0, 0, 0.1);
    }
    .login{
    height: 65vh;
    	display: flex;
    	
    	justify-content: center;
    	align-items: center;
    }
  </style>
</head>
<body>
<%@ include file="nav.jsp" %>
<div class="login">
    <div class="login-container">
        <h2>Login</h2>
        <% String errorMessage = (String) request.getAttribute("error"); %>
    	<% if (errorMessage != null && !errorMessage.isEmpty()) { %>
        <div id="error-message" style="color: red; margin-bottom: 10px;">
            <%= errorMessage %>
        </div>
    	<% } %>
        <form action="<%=request.getContextPath() %>/login" method="post">
          <div class="input-container">
            <input type="text" id="email" class="input-field" placeholder="" name="email">
            <label for="email" class="input-label">Email</label>
          </div>
          <div class="input-container">
            <input type="password" id="password" class="input-field" placeholder="" name="password">
            <label for="password" class="input-label">Password</label>
          </div>
          <div class="register-link">
              <a href="<%=request.getContextPath()%>/register">Create Account</a>
              <input type="submit" name="submit" value="Sign in">
          </div>
        </form>
      </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>