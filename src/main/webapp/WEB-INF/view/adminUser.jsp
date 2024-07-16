<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            font-family: Arial, sans-serif;
        }
        .container {
            display: flex;
            height: 100%;
        }
        
        .content {
            flex: 1;
            padding: 20px;
            height: 100%;
        }
        li{
            list-style: none;
            padding: 10px 0px;
        }
        a{
            color: white;
            text-decoration: none;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            font-size:16px;
        }
        th {
            background-color: #f2f2f2;
        }
        button {
            background-color: #ff4d4d; 
            color: white;
            padding: 6px 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 5px;
        }
        button:hover {
            background-color: #e60000; 
        }

    </style>
</head>
<body>
    <div class="container">
        <%@ include file="sidebar.jsp" %>
        <div class="content">
            <table border="1">
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
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
                    <td style="display:flex; align-items:center;justify-content:center;">
                    	<form action="DeleteUser" method="post">
		                    <input type="hidden" name="action" value="delete" />
		                    <input type="hidden" name="email" value="${user.email}" />
		                    <button type="submit">Delete</button>
                		</form>
                		<br>
			            <br>
			            <br>
                    </td>
                    
                </tr>
            </c:forEach>
            
            <c:forEach var="image" items="${imageList}">
   		    <img src="data:image/jpeg;base64,${image.base64ImageData}" height="400px" width="400px"  alt="Uploaded Image">
		    <c:out value="${image.image_name }"></c:out>
		    </c:forEach>
        </tbody>
    </table>
        </div>
    </div>
</body>
</html>
