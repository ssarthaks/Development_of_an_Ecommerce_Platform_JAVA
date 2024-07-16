<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Results</title>
<style>
      *,
      body {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
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
<c:if test="${empty searchResult}">
    <h2 class="subHeading">Keyboard Not Found</h2>
</c:if>
<c:if test="${not empty searchResult}">
    <h2 class="subHeading">Search Results for "${param.search}"</h2>
    <div class="main_container">
        <c:forEach var="product" items="${searchResult}">
            <div class="card">
                <p class="New">New</p>
                <h1 class="main_heading" style="color:white"><c:out value="${product.productname}" /></h1>
                <h3 class="sub_heading"><c:out value="${product.productswitch}" /></h3>
                
                <c:forEach var="image" items="${imageList}">
                    <c:if test="${image.image_name eq product.productmodelnumber}">
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
        </c:forEach>
    </div>
</c:if>
<%@ include file="footer.jsp" %>
</body>
</html>
