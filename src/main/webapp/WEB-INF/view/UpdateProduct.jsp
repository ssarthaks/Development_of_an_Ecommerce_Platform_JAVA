<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
     <style>
        /* Define your CSS styles here */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h2 {
            color: #333;
            text-align:center;
        }
        form {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        textarea {
            height: 100px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Update Product</h2>
    <form action="UpdateProductdetail" method="post">
        <input type="hidden" name="productModelNumber" value="${productModelNumber}" />

        <!-- You can include other input fields for product details here -->
        <label for="productName">Product Name:</label>
        <input type="text" id="productName" name="productName" required><br>

        <label for="productSize">Product Size:</label>
        <input type="text" id="productSize" name="productSize" required><br>

        <label for="productSwitch">Product Switch:</label>
        <input type="text" id="productSwitch" name="productSwitch" required><br>

        <label for="productStock">Product Stock:</label>
        <input type="number" id="productStock" name="productStock" required><br>

        <label for="productPrice">Product Price:</label>
        <input type="number" id="productPrice" name="productPrice" required><br>

        <label for="productDescription">Product Description:</label>
        <textarea id="productDescription" name="productDescription" required></textarea><br>

        <label for="productFeature">Product Feature:</label>
        <textarea id="productFeature" name="productFeature" required></textarea><br>

        <label for="productBox">Product Box:</label>
        <input type="text" id="productBox" name="productBox" required><br>

        <label for="productDownload">Product Download:</label>
        <input type="text" id="productDownload" name="productDownload" required><br>

        <!-- Include a hidden input field to hold the product model number -->
        
        <input type="submit" value="Update">
    </form>
</body>
</html>