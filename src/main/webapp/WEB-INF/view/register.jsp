<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel= "stylesheet" type = "text/css" href ="<%= request.getContextPath() %>/css/register.css">
</head>
<script src="https://kit.fontawesome.com/bc80280643.js" crossorigin="anonymous"></script>
<body>
	<%@ include file="nav.jsp" %>
	<div class="mainRegister">
    <div class="register-container">
      <h2>Register</h2>
      <%
		if (request.getAttribute("error1")!=null)
		{
			%>
			<p style="color:red"><%= request.getAttribute("error1") %></p>
			<%
		}
		%>
      <form action="<%=request.getContextPath()%>/register" method="post">
        <div class="double">
          <div class="input-container">
            <input
              type="text"
              id="firstname"
              class="input-field"
              placeholder=""
              name="firstname" required
            />
            <label for="firstname" class="input-label">First Name</label>
          </div>
          <div class="input-container">
            <input
              type="text"
              id="lastname"
              class="input-field"
              placeholder=""
              name="lastname" required
            />
            <label for="lastname" class="input-label">Last Name</label>
          </div>
        </div>
        <div class="double ">
          <div class="input-container">
            <input
              type="text"
              id="username"
              class="input-field"
              placeholder=""
              name="username" required
            />
            <label for="username" class="input-label">Username</label>
          </div>
          <div class="input-container">
            <input
              type="text"
              id="phonenumber"
              class="input-field"
              placeholder=""
              name="phone" required
            />
            <label for="phonenumber" class="input-label">Phone</label>
          </div>
        </div>
        <div class="input-container">
          <input type="email" id="email" name="email" class="input-field" placeholder="" required/>
          <label for="email" class="input-label">Email</label>
        </div>
        <div class="input-container">
          <input type="text" name="address" id="address" class="input-field" placeholder="" required />
          <label for="address" class="input-label">Address</label>
        </div>
        <div class="input-container">
          <input
            type="password"
            id="password"
            class="input-field"
            placeholder=""
            name="password" required
          />
          <label for="password" class="input-label password">Password</label>
        </div>
        <div class="input-container">
          <input
            type="password"
            id="password"
            class="input-field"
            placeholder=""
            name="retypepassword" required
          />
          <label for="confirm-password" class="input-label password"
            >Confirm Password</label
          >
          <%
			if (request.getAttribute("error")!=null)
			{
				%>
				<p style="color:red"><%= request.getAttribute("error") %></p>
				<%
			}
			%>
        </div>
        <div class="register-link">
          <a href="<%=request.getContextPath()%>/login">Have an account?</a>
          <input type="submit" value="Sign up" />
        </div>
      </form>
    </div>
    </div>
   <%@ include file="footer.jsp" %>
  </body>
</html>