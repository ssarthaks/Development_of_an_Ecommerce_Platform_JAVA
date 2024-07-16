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
        .sidebar {
            width: 200px;
            background-color: black;
            color: #fff;
            padding: 20px;
            flex: 0 0 auto; /* Sidebar should not grow or shrink */
            height: 100%; /* Fill the full height */
        }
        .content {
            flex: 1;
            padding: 20px;
            height: 100%; /* Fill the full height */
        }
        li{
            list-style: none;
            padding: 10px 0px;
        }
        a{
            text-decoration: none;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
        	font-size: 16px;
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
        /* Add more styling as needed */
    </style>
</head>
<body>
    <div class="container">
        <%@ include file="sidebar.jsp" %>
        <div class="content">
       <button style="background-color:">
        <a href="addProduct" style="color:black">Add Product</a></button>
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
            <th>Trending</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="product" items="${productList}" varStatus="status">
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
		            <form id="trendingForm_${status.index}" action="${pageContext.request.contextPath}/Trending" method="post">
		                <input type="hidden" name="productModelNumber" value="${product.productmodelnumber}" />
		                <input type="checkbox" id="trendingCheckbox_${status.index}" name="isTrending" ${product.isTrending ? 'checked' : ''} />
		            </form>
		        </td>
                <td>
                    <form action="UpdateProductdetail" method="get">
                        <input type="hidden" name="productModelNumber" value="${product.productmodelnumber}" />
                        <button type="submit">Update</button>
                    </form>
                </td>
                <td>
                    <form action="DeleteProduct" method="post">
                        <input type="hidden" name="productModelNumber" value="${product.productmodelnumber}" />
                        <button type="submit" style="background-color: #f44336;">Delete</button>
                    </form>
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
<script>
    // Get all checkbox elements with IDs starting with 'trendingCheckbox_'
    var checkboxes = document.querySelectorAll('[id^="trendingCheckbox_"]');

    // Add event listeners for each checkbox
    checkboxes.forEach(function(checkbox) {
        checkbox.addEventListener('change', function() {
            // Get the index from the checkbox ID
            var index = checkbox.id.split('_')[1];
            
            // Construct the form ID using the index
            var formId = 'trendingForm_' + index;

            // Get the form element
            var form = document.getElementById(formId);
            
            // Submit the form
            form.submit();
        });
    });
</script>
</body>
</html>
