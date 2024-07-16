<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/* CSS styles */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}

form {
    max-width: 600px;
    margin: 20px auto;
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

form label {
    display: block;
    margin-bottom: 5px;
}

form input[type="text"],
form textarea {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

form input[type="file"] {
    margin-top: 10px;
}

form input[type="submit"] {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

form input[type="submit"]:hover {
    background-color: #45a049;
}

</style>
</head>
<body>
<form action="addProduct" method="post" enctype="multipart/form-data">
    <label for="productName">Product Name:</label>
    <input type="text" id="productName" name="productName"><br><br>

    <label for="productModelNumber">Product Model Number:</label>
    <input type="text" id="productModelNumber" name="productModelNumber"><br><br>

    <label for="productSize">Product Size:</label>
    <input type="text" id="productSize" name="productSize"><br><br>

    <label for="productSwitch">Product Switch:</label>
    <input type="text" id="productSwitch" name="productSwitch"><br><br>

    <label for="productStock">Product Stock:</label>
    <input type="text" id="productStock" name="productStock"><br><br>

    <label for="productPrice">Product Price:</label>
    <input type="text" id="productPrice" name="productPrice"><br><br>

    <label for="productDescription">Product Description:</label><br>
    <textarea id="productDescription" name="productDescription" rows="4" cols="50"></textarea><br><br>

    <label for="productFeature">Product Feature:</label><br>
    <textarea id="productFeature" name="productFeature" rows="4" cols="50"></textarea><br><br>

    <label for="productBox">Product Box:</label><br>
    <textarea id="productBox" name="productBox" rows="4" cols="50"></textarea><br><br>

    <label for="productDownload">Product Download:</label><br>
    <input type="text" id="productDownload" name="productDownload"><br><br>
	
	<input type="file" name="image" accept="image/jpeg, image/png">
	
    <input type="submit" value="Submit">
</form>
</body>
</html>
