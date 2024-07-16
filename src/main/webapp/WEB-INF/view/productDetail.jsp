<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"
    />
    <style>
      @import url("https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,500;1,400&display=swap");
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
      .productMain {
      	margin-top: 20px;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 70vh;
      }
      .productImage {
        width: 50%;
      }
      .productText {
        width: 50%;
        display: flex;
        justify-content: center;
        align-items: center;
      }
      .productImage img {
        width: 100%;
        padding: 100px;
      }
      .productTextContainer {
        display: flex;
        flex-direction: column;
        width: 25vw;
        gap: 5px;
      }
      .addToCart {
        padding: 1px 14px;
        border-radius: 10px;
      }
      .accordion {
        background-color: white !important;
        color: #444;
        cursor: pointer;
        padding: 18px;
        width: 100%;
        border: 1px black;
        border-style: solid none none none;
        text-align: left;
        outline: none;
        font-size: 15px;
        transition: 0.4s;
      }

      .lastaccordion{
        border: 1px black;
        border-style: solid none solid none;
      }

      .active,
      .accordion:hover {
        background-color: #ccc;
      }

.panel {
  padding: 0 18px;
  display: none;
  background-color: white;
  overflow: hidden;
  /* Add transition for smooth visibility change */
  transition: max-height 0.4s ease-out, padding 0.4s ease-out;
  max-height: 0; /* Initially hide the panel */
}

.active + .panel {
  display: block;
  /* Set max-height to a large value when panel is visible */
  max-height: 500px; /* Adjust to a suitable height */
  padding: 18px; /* Restore padding when panel is visible */
}
      

      @media only screen and (max-width: 768px) {
        
        .productMain{
          flex-direction: column;
        }
        .productImage {
          width: 100%;
        }
        .productText {
          width: 100%;
          display: flex;
          justify-content: center;
          align-items: center;
        }
        .productImage img {
          width: 100%;
          padding: 10px 50px;
        }
        .productTextContainer {
          display: flex;
          flex-direction: column;
          width: 100%;
          gap: 5px;
          padding: 10px 50px;
        }
      }
      @media only screen and (max-width: 1024px){
        .productImage img{
          padding: 10px 30px;
        }
        .accordion{
          padding: 10px;
        }
        .productMain{
          margin: 30px 0px;
        }
      }
    </style>
    <script src="util.js"></script>
  </head>
  <body>
  <%@ include file="nav.jsp" %>
<div class="productMain">
        <!-- Product Image -->
        <c:forEach var="image" items="${imageList}">
            <c:if test="${image.image_name eq product.productmodelnumber}">
                <img src="data:image/jpeg;base64,${image.base64ImageData}" width="500px" alt="Product Image">
            </c:if>
        </c:forEach>
        
        <!-- Product Details -->
        <div class="productText">
            <div class="productTextContainer">
                <h1>${product.productname}</h1>
                <h3>Model no: ${product.productmodelnumber}</h3>
                <h2>$${product.produtprice}</h2>
                <h3>In stock ${product.productstock}</h3>
                <p>${product.productdescription}</p>
                
                <!-- Quantity input and Add to Cart button -->
                <form id="addToCartForm" action="addToCart" method="POST">
                    <input type="hidden" name="productmodelnumber" value="${product.productmodelnumber}">
                    <input type="hidden" name="productprice" value="${product.produtprice}">
                    <div class="qty-input">
                        <input id="productQtyInput" class="product-qty" type="number" name="quantity" value="1" min="1">
                        <button type="submit" class="addToCart">Add to Cart</button>
                    </div>
                </form>
                
                <!-- Accordion buttons -->
                <button class="accordion">Features</button>
                <div class="panel">
                    <p>${product.productfeature}</p>
                </div>
                <button class="accordion">Inside the box</button>
                <div class="panel">
                    <p>${product.productbox}</p>
                </div>
                <button class="accordion lastaccordion">Downloads</button>
                <div class="panel">
                    <p>${product.productdownload}</p>
                </div>
            </div>
        </div>
    </div>
 <script>
   var acc = document.getElementsByClassName("accordion");
   var i;
   var addToCartButtons = document.querySelectorAll('.addToCart');
   for (i = 0; i < acc.length; i++) {
     acc[i].addEventListener("click", function () {
       this.classList.toggle("active");
       var panel = this.nextElementSibling;
       if (panel.style.display === "block") {
         panel.style.display = "none";
       } else {
         panel.style.display = "block";
       }
     });
   }
   
   document.addEventListener("DOMContentLoaded", function() {// Add event listener to update the hidden input value when the quantity input changes
var productQtyInput = document.getElementById("productQtyInput");
var productQty = document.getElementById("productQty");

// Add event listener to update the hidden input value when the quantity input changes
var addToCartForm = document.getElementById("addToCartForm");
var productQtyInput = document.getElementById("productQtyInput");
var productQty = document.getElementById("productQty");

// Add event listener to the form submission
addToCartForm.addEventListener("submit", function(event) {
    // Prevent the form from submitting immediately
    event.preventDefault();

    // Update the value of the hidden input with the current quantity
    productQty.value = productQtyInput.value;
	console.log(productQty.value);
	console.log(productQtyInput.value);
    // Submit the form programmatically
    this.submit();
})
   };

 </script>
<%@ include file="footer.jsp" %>
  </body>
</html>