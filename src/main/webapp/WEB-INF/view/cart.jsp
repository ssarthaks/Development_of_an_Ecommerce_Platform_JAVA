<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Cart</title>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"
    />
<style>
      @import url("https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,500;1,400&display=swap");
      body{
        background-color: #f5f5f5;
        font-size: 16px
      }
      .cart {
        min-height: 80vh;
        display: flex;
        justify-content: space-around;
        background-color: #f5f5f5;
        padding: 50px;
      }
      .cartSummary {
        background-color: white;
        padding: 30px;
        width: fit-content;
        height: fit-content;
        
      }
      .cartSummaryLine {
        display: flex;
        justify-content: space-between;
      }
      .cartProduct {
        background-color: white;
        padding: 30px;
        display: flex;
        gap: 10px;
        align-items: center;
        margin: 0 auto;
        width: 60vw;
      }
      .cartProduct img {
        width: 200px;
      }
      .cartTitle {
        margin: 40px;
      }
      .cartProductText{
        gap: 10px;
        display: flex;
        width: 100%;
        justify-content: space-between;
        align-items: center;
      }
      .cartProductsOnly{
        display: flex;
        flex-direction: column;
        gap: 10px;
        overflow: hidden;
      }
      .productName{
        width: 10vw;
      }
      .cartSummary h1{
        padding-bottom: 10px;
      }
      .product-qty{
        width: 30px;
      }
      .Delete {
        padding: 5px 16px;
        background-color: black;
        color: white;
        border-radius: 5px;
      }
      .checkout{
        width: 100%;
        margin-top: 20px;
        padding: 8px;
        background-color: black;
        color: white;
        border-radius: 5px;
      }
      @media only screen and (max-width: 600px){
        .cartProduct{
          flex-direction: column;
        }
        .cartProductText{
          align-self: start;
          text-align: left;
          flex-direction: column;
        }
        .productName{
          width: 100%;
          margin-top: 10px;
          text-align: center;
        }
        .cartProduct img{
          padding: 15px ;
        }
      }
      @media only screen and (max-width: 1000px) {
        .cartSummary{
          width: 90% ;
          margin: 20px auto;
        }
        .cart{
          flex-direction: column;
          
          width: 100vw;
        }
        .cartProduct{
          width: 90%;
        }
        
      }      
    </style>
  </head>
  <body>
	<%@ include file="nav.jsp" %>
    <div class="cart">
       <div class="cartProductsOnly">
      <table>
        <thead>
          <tr>
          	<th>Product Image</th>
            <th>Product Model Number</th>
            <th>Quantity</th>
            <th>Product Price</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="cartItem" items="${cartItems}">
            <tr>
            <td>
            <c:forEach var="image" items="${imageList}">
                <c:if test="${image.image_name eq cartItem.productModelNumber}">
                    <img src="data:image/jpeg;base64,${image.base64ImageData}" width="100px" height="100px" alt="Product Image">
                </c:if>
            </c:forEach>
            </td>
              <td>${cartItem.productModelNumber}</td>
              <td>${cartItem.quantity}</td>
              <td>${cartItem.productPrice}</td>
              <td>
                <form action="RemoveCartItem" method="post">
                  <input type="hidden" name="userEmail" value="${userEmail}">
                  <input type="hidden" name="productModelNumber" value="${cartItem.productModelNumber}">
                  <button type="submit">Delete All from Cart</button>
                </form>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
      <div class="cartSummary">
        <h1>Cart Summary</h1>

        <div class="cartSummaryLine">
          <h3>Grand Total:</h3>
          <h3>$${grandTotal}</h3>
        </div>
<form action="Checkout" method="post">
    <button type="submit" class="checkout">Checkout</button>
</form>
      </div>
    </div>
	<%@ include file="footer.jsp" %>
  </body>
</html>
