<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Profile</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,500;1,400&display=swap">
<style>
  .mainDiv {
    margin: 50px 0px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .logo {
    width: 120px;
    padding: 10px;
  }
  .profileCard {
    padding: 50px;
    border: black 1px solid;
    text-align: center;
  }
  .editButton {
    width: fit-content;
    padding: 15px 25px;
    border-radius: 10px;
    position: relative;
    top: 20px;
    background-color: black;
    color: white;
  }
  .editButton:hover {
    background-color: grey;
  }
  span {
    font-weight: 400;
  }
  h2 {
    padding-top: 15px;
    font-size: 25px;
  }
</style>
</head>
<body>
    <%@ include file="nav.jsp" %>
<div class="mainDiv">
  <div class="profileCard">
    <img src="<%= request.getContextPath() %>/assets/account.jpg" alt="user profile" class="logo" />
    <h2>First Name: <span>${user.firstname}</span></h2>
    <h2>Last Name: ${user.lastname}</h2>
    <h2>Username: <span>${user.username}</span></h2>
    <h2>Email: <span>${user.email}</span></h2>
    <h2>Phone Number: <span>${user.phone}</span></h2>
    <h2>Address: <span>${user.address}</span></h2>
    <a href="UpdateProfile" class="editButton" style="text-decoration:none">Edit Profile</a>
  </div>
</div>
    <%@ include file="footer.jsp" %>
</body>
</html>