<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: Roboto, sans-serif;
            font-size: 16px;
        }
        .container {
            display: flex;
            height: 100%;
        }

        .content {
            flex: 1;
            padding: 20px;
            height: 100%; 
            background-color: #f2f2f2; 
            display: flex;
            justify-content: space-around;
            align-items: center;
            flex-wrap: wrap; 
        }

        .box {
            width: 200px;
            height: 100px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 20px;
            display: flex;
            justify-content: center; 
            align-items: center; 
        }

        .box p {
            font-size: 20px; 
            font-weight: bold;
            margin: 0; 
        }
    </style>
</head>
<body>
    <div class="container">
        <%@ include file="sidebar.jsp" %>
        <div class="content">
            <div class="box">
                <p>User Count: </p>
                <p><%= request.getAttribute("userCount") %></p>
            </div>
            <div class="box">
                <p>Product Count: </p>
                <p><%= request.getAttribute("productCount") %></p>
            </div>
            <div class="box">
                <p>Order Count: </p>
                <p><%= request.getAttribute("orderCount") %></p>
            </div>

        </div>
    </div>
</body>
</html>
