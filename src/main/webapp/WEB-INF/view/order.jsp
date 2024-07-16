<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Orders</title>
        <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        h1 {
            margin-top: 20px;
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

        table td table th, table td table td {
            font-size: 16px; 
        }
    </style>
</head>
<body>
<%@ include file="nav.jsp" %>
    <h1>Your Orders</h1>
    <table border="1">
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Order Date</th>
            <th>Order Details</th>
            <th>Total Amount</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="order" items="${userOrders}" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
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
                    </tr>
                </c:forEach>
        </tbody>
    </table>
    <%@ include file="footer.jsp" %>
</body>
</html>
