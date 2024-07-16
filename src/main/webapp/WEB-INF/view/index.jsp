<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home</title>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"
    />
    <style>
      @import url("https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,500;1,400&display=swap");

      *,
      body {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }

      ul {
        margin: 0;
      }
      html {
        font-size: 62.5%;
        font-family: "Roboto", sans-serif;
        overflow-x: hidden;
      }

      li {
        list-style: none;
      }

      a {
        text-decoration: none;
      }

      .header {
        border-bottom: 1px solid #e2e8f0;
      }

      .navbar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 1.5rem 1.5rem;
        color: white;
        background-color: black;
        margin-bottom: 0;
      }
      a.nav-link {
        color: white;
      }

      .hero {
        display: flex;
        justify-content: space-around;
        align-items: center;
        width: 100vw;
        height: 400px;
        gap: 30px;
      }
      .heroText {
        gap: 10px;
        font-size: 14px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: start;
       
      }
      
      .heroPrice {
        color: black;
        font-size: 16px;
        font-weight: 900;
      }
      .heroButton {
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
      .heroButton:hover {
        background-color: rgba(0, 0, 0, 0.3);
      }
      .photo img {
        display: flex;
        width: 100%;
      }
      p.New {
        color: #e77143;
        font-weight: 600;
        margin: 0px 0px 16px 0px;
      }
      h1.main_heading {
        font-size: 28px;
        line-height: 100%;
        font-weight: 700;
        margin: 0;
        color: white;
      }
      h3.sub_heading {
        font-size: 14px;
        font-weight: 500;
        margin: 3px 0px 16px 0px;
        color: #d7d7d7;
      }
      .card {
        box-shadow: 0 8px 12px 0 rgba(0, 0, 0, 0.2);
        max-width: 300px;
        margin-top: 2vh;
        font-family: sans-serif;
        padding: 1.5rem;
        background: linear-gradient(to bottom, black, #222831);
        border-radius: 10px;
      }

      .price {
        color: #d7d7d7;
        font-size: 14px;
        font-weight: 800;
      }

      .card button {
        border-radius: 50px;
        border: none;
        outline: 0;
        padding: 10px 14px;
        color: black;
        background-color: white;
        text-align: center;
        cursor: pointer;
        font-size: 10px;
      }

      .card button:hover {
        opacity: 0.7;
      }

      .card_bottom {
        margin: 36px 16px 15px 16px;
        justify-content: space-between;
        align-items: center;
        display: flex;
      }
      .main_container {
        justify-content: space-around;
        align-items: center;
        display: flex;
        flex-wrap: wrap;
      }
      .subHeading {
        margin-top: 20px;
        margin-bottom: 20px;
        text-align: center;
      }
      .subHeading-2 {
        margin-top: 36px;
        margin-bottom: 20px;
        text-align: center;
      }
      footer {
        margin-top: 16px;
        text-align: center;
        padding: 10px;
        background-color: black;
        color: white;
      }
      .navItems {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 15px;
      }
      .navSubItems {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 5px;
      }
      header {
        margin: 0 50px;
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
      .searchStyle {
        border-radius: 10px;
        padding: 4px;
      }
      .searchHamburger{
        display: none;
      }
      @media only screen and (max-width: 700px) {
        .hero {
          flex-wrap: wrap;
          gap: 0px;
        }
        .heroText {
          text-align: center;
          align-items: center;
        }
        header {
          margin: 10px;
        }
      }

      @media only screen and (max-width: 768px) {
        .nav-menu {
          position: fixed;
          top: -100%; 
          left: 0;
          flex-direction: column;
          background-color: black;
          width: 100%;
          text-align: center;
          transition: top 0.3s; 
          box-shadow: 0 10px 27px rgba(0, 0, 0, 0.05);
        }
        .card {
          margin-top: 2vh;
        }
        .nav-menu.active {
          top: 55px; 
        }

        .nav-item {
          margin: 2.5rem 0;
        }

        .hamburger {
          display: block;
          cursor: pointer;
        }

        .hamburger.active .bar:nth-child(2) {
          opacity: 0;
        }

        .hamburger.active .bar:nth-child(1) {
          transform: translateY(8px) rotate(45deg);
        }

        .hamburger.active .bar:nth-child(3) {
          transform: translateY(-8px) rotate(-45deg);
        }
        .bar {
          background-color: white;
        }
        .search{
          display: none;
        }
        .searchHamburger{
          display: block;
        }
              h1.main_heading {
        font-size: 28px;
        line-height: 100%;
        font-weight: 700;
        margin: 0;
        color: white;
      }
      h3.sub_heading {
        font-size: 14px;
        font-weight: 500;
        margin: 3px 0px 16px 0px;
        color: #d7d7d7;
      }
      .card {
        box-shadow: 0 8px 12px 0 rgba(0, 0, 0, 0.2);
        max-width: 300px;
        margin: 2vh;
        font-family: sans-serif;
        padding: 1.5rem;
        background: linear-gradient(to bottom, black, #222831);
        border-radius: 10px;
      }

      .price {
        color: #d7d7d7;
        font-size: 14px;
        font-weight: 800;
      }

      .card button {
        border-radius: 50px;
        border: none;
        outline: 0;
        padding: 10px 14px;
        color: black;
        background-color: white;
        text-align: center;
        cursor: pointer;
        font-size: 10px;
      }

      .card button:hover {
        opacity: 0.7;
      }

      .card_bottom {
        margin: 36px 16px 15px 16px;
        justify-content: space-between;
        align-items: center;
        display: flex;
      }
      .main_container {
        justify-content: space-around;
        align-items: center;
        display: flex;
        flex-wrap: wrap;
      }
      .subHeading {
        margin-top: 20px;
        margin-bottom: 20px;
        text-align: center;
      }
      .subHeading-2 {
        margin-top: 36px;
        margin-bottom: 20px;
        text-align: center;
      }
      }
    </style>
  </head>
  <body>
	<%@ include file="nav.jsp" %>
    <div class="hero">
      <div class="photo">
        <img src="<%= request.getContextPath() %>/assets/heroImage.png" alt="" />
      </div>
      <div class="heroText">
        <h1>HyperX Alloy Rise</h1>
        <p>Elevate your gaming experience to new level</p>
        <p class="heroPrice">$160</p>
        <a href="<%= request.getContextPath() %>/ViewProduct" class="heroButton">Shop Now</a>
      </div>
    </div>
<div class="main_container">
    <c:forEach var="product" items="${productList}" varStatus="loop">
        <c:if test="${loop.index < 3}">
        <a href="Detail?productModelNumber=${product.productmodelnumber}" style="text-decoration: none; color: inherit;">
            <div class="card">
                <p class="New">New</p>
                <h1 class="main_heading"><c:out value="${product.productname}" /></h1>
                <h3 class="sub_heading"><c:out value="${product.productswitch}" /></h3>
                
                <c:forEach var="image" items="${imageList}">
                    <c:if test="${image.image_name == product.productmodelnumber}">
                        <img src="data:image/jpeg;base64,${image.base64ImageData}" width="100%" alt="Product Image">
                    </c:if>
                </c:forEach>
                
                <div class="card_bottom">
                    <p class="price"><c:out value="${product.produtprice}" /></p>
                    <form action="addToCart" method="post">
                        <input type="hidden" value="${product.productmodelnumber}" name="productmodelnumber">
                        <input type="hidden" value="${product.produtprice}" name="productprice">
                    </form>
                </div>
            </div>
        </c:if>
        </a>
    </c:forEach>
</div>




	<%@ include file="footer.jsp" %>
  </body>
</html>
    