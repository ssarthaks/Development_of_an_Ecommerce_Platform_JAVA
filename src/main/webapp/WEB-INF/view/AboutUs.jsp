<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="util.css">
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"
    />
    <style>
      @import url("https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,500;1,400&display=swap");

      .mainDiv{
        margin: 70px 0px;
        height: 53vh;
        gap: 30px;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-wrap: wrap;
      }
      .logo {
        width: 120px;
        padding: 10px;
        border-radius: 100px;
      }
      .profileCard{
        box-shadow: 0 8px 12px 0 rgba(0, 0, 0, 0.2);
        padding: 25px;
        text-align: center;
      }
      .editButton{
        width: fit-content;
        padding: 15px 25px;
        border-radius: 10px;
        margin-top: 10px;
        background-color: black;
        color: white;
      }
      .editButton:hover{
        background-color: grey;
      }
      span{
        font-weight: 400;
      }
      h2{
        padding-top: 15px;
        font-size: 25px;
      }
      
      @media only screen and (max-width: 500px){
        .profileCard{
          padding: 5px;
        }
      }
    </style>
    
    <script src="util.js"></script>
  </head>
  <body>
    
    <%@ include file="nav.jsp" %>
    <div class="mainDiv">
        <div class="profileCard">
            <img src="<%= request.getContextPath() %>/assets/praneet.jpg" alt="" class="logo" />
            <h2>First Name: <span>Praneet</span></h2>
            <h2>Last Name: <span>Tuladhar</span></h2>
            <h2>Instagram: <span><a href="https://www.instagram.com/praneet.yt">praneet.yt</a></span></h2>
            <h2>Email: <span>praneet.biq@gmail.com</span></h2>
            <h2>Phone Number: <span>9861219679</span></h2>
        </div>
        <div class="profileCard">
            <img src="<%= request.getContextPath() %>/assets/sarthak.jpg" alt="" class="logo" />
            <h2>First Name: <span>Sarthak</span></h2>
            <h2>Last Name: <span>Sharma</span></h2>
            <h2>Instagram: <span><a href="https://www.instagram.com/ssarthak.s/">ssarthak.s</a></span></h2>
            <h2>Email: <span>ssarthak414@gmail.com</span></h2>
            <h2>Phone Number: <span>9860688334</span></h2>
        </div>
        <div class="profileCard">
            <img src="<%= request.getContextPath() %>/assets/srijan.jpg" alt="" class="logo" />
            <h2>First Name: <span>Srijan</span></h2>
            <h2>Last Name: <span>Maharjan</span></h2>
            <h2>Instagram: <span><a href="https://www.instagram.com/srijanmahrzn/">srijanmahrzn</a></span></h2>
            <h2>Email: <span>srijanm20@gmail.com</span></h2>
            <h2>Phone Number: <span>9828038615</span></h2>
        </div>
    </div>
<%@ include file="footer.jsp" %>
  </body>
</html>
