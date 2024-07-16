<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Products</title>
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
    </style>
  </head>
  <body>
<%@ include file="nav.jsp" %>
<div class="main_container">
    <c:forEach var="product" items="${productList}">
        <a href="Detail?productModelNumber=${product.productmodelnumber}" style="text-decoration: none; color: inherit;">
            <div class="card">
                <p class="New">New</p>
                <h1 class="main_heading"><c:out value="${product.productname}" /></h1>
                <h3 class="sub_heading"><c:out value="${product.productswitch}" /></h3>
                
                <c:forEach var="image" items="${imageList}">
                    <c:if test="${image.image_name == product.productmodelnumber}">
                        <img src="data:image/jpeg;base64,${image.base64ImageData}" width="100%" style="background-repeat:no-repeat;" alt="Product Image">
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
        </a>
    </c:forEach>`
</div>

    <%@ include file="footer.jsp" %>
  </body>
</html>
