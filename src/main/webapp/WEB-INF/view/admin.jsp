<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <head>
    <meta charset="UTF-8">
    <title>User List</title>
    <style>
        /* Define your CSS styles here */
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 6px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
    
</head>
<body>
<h1>Users</h1>
    <table border="1">
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
            	<th>Update</th>
            	<th>Delete</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${listofUser}">
                <tr>
                    <td><c:out value="${user.firstname}" /></td>
                    <td><c:out value="${user.lastname}" /></td>
                    <td><c:out value="${user.username}" /></td>
                    <td><c:out value="${user.email}" /></td>
                    <td><c:out value="${user.phone}" /></td>
                    <td><c:out value="${user.address}" /></td>
                    <td>
                    	<form action="UpdateUser" method="post">
		                    <input type="hidden" name="action" value="delete" />
		                    <input type="hidden" name="email" value="${user.email}" />
		                    <button type="submit">Update</button>
                		</form>
                    </td>
                    <td>
                    	<form action="DeleteUser" method="post">
		                    <input type="hidden" name="action" value="delete" />
		                    <input type="hidden" name="email" value="${user.email}" />
		                    <button type="submit">Delete</button>
                		</form>
                    </td>
                    
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <br>
    <h1>Products</h1>
    <a href="addProduct">Add Product</a>
   <table border="1">
    <thead>
        <tr>
            <th>Product Name</th>
            <th>Model Number</th>
            <th>Size</th>
            <th>Switch</th>
            <th>Stock</th>
            <th>Price</th>
            <th>Description</th>
            <th>Feature</th>
            <th>Box</th>
            <th>Download</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="product" items="${productList}">
            <tr>
                <td><c:out value="${product.productname}" /></td>
                <td><c:out value="${product.productmodelnumber}" /></td>
                <td><c:out value="${product.productsize}" /></td>
                <td><c:out value="${product.productswitch}" /></td>
                <td><c:out value="${product.productstock}" /></td>
                <td><c:out value="${product.produtprice}" /></td>
                <td><c:out value="${product.productdescription}" /></td>
                <td><c:out value="${product.productfeature}" /></td>
                <td><c:out value="${product.productbox}" /></td>
                <td><c:out value="${product.productdownload}" /></td>
                <td>
                    <form action="${pageContext.request.contextPath}/updateProduct" method="post">
                        <input type="hidden" name="productModelNumber" value="${product.productmodelnumber}" />
                        <button type="submit">Update</button>
                    </form>
                </td>
                <td>
                    <form action="DeleteProduct" method="post">
                        <input type="hidden" name="productModelNumber" value="${product.productmodelnumber}" />
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
   
    
</body>
</html>
