<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Orders</title>
</head>
<style>
         body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex; 
        }
        .container{
        width:100%
        }
h1 {
    margin-bottom: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

table th, table td {
    padding: 8px;
    border: 1px solid #ddd;
    text-align: left;
    font-size: 16px;
}

table th {
    background-color: #f2f2f2;
}

.order-details-table {
    width: 100%;
    border-collapse: collapse;
}

.order-details-table th, .order-details-table td {
    padding: 8px;
    border: 1px solid #ddd;
    text-align: left;
}

.order-details-table th {
    background-color: #f2f2f2;
}
 .delete-button {
            background-color: #f44336;
            color: white;
            border: none;
            padding: 5px 10px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            margin-left: 5px;
            cursor: pointer;
        }
    </style>
<body>
<%@ include file="sidebar.jsp" %>
<div class= "container">
	<h1>All Orders</h1>

<table border="1">
    <thead>
        <tr>
            <th>Order ID</th>
            <th>User Email</th>
            <th>Order Date</th>
            <th>Order Details</th>
            <th>Total Amount</th>
            <th>Action</th> 
        </tr>
    </thead>
    <tbody>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.orderId}</td>
                <td>${order.userEmail}</td>
                <td>${order.orderDate}</td>
                <td>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Product Model Number</th>
                                <th>Quantity</th>
                                <th>Product Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="detail" items="${order.orderDetails}">
                                <tr>
                                    <td>${detail.productModelNumber}</td>
                                    <td>${detail.quantity}</td>
                                    <td>${detail.productPrice}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </td>
                <td>${order.totalAmount}</td>
                <td>	
                	<form action="DeleteOrder" method="post"> <!-- Form for delete action -->
                                <input type="hidden" name="orderId" value="${order.orderId}"> <!-- Hidden input field to pass orderId -->
                                <button type="submit" class="delete-button">Delete</button> <!-- Delete button -->
                   </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>

</body>
</html>
