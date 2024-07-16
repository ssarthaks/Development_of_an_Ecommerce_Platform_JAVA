<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Update Profile</title>
    <style>
        /* Define your CSS styles here */
        @charset "ISO-8859-1";
	
        body {

            font-size: 14px;
        }

        /* Override Bootstrap's box-sizing property */
        *,
        *::before,
        *::after {
            box-sizing: content-box !important;
        }

        .register-container {
            background-color: #fff;
            border-radius: 8px;
            padding: 40px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: auto; /* Center horizontally */
            width: 30%;
        }

        h1 {
            margin: 0;
            text-align: center;
            margin-bottom: 20px;
        }

        .input-container {
            width: 100%;
            position: relative;
            margin-bottom: 20px;
        }

        

        .input-field {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        

        input[type="submit"] {
            padding: 10px 30px;
            background-color: black;
            color: #fff;
            font-weight: bold;
            border: none;
            border-radius: 50px;
            cursor: pointer;
            transition: opacity 0.3s;
        }

        input[type="submit"]:hover {
            opacity: 0.8;
        }

        .register-link {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 40px;
            text-align: left;
        }

        .register-link a {
            color: black;
            padding: 10px;
            font-weight: bold;
            text-decoration: none;
            border-radius: 50px;
            transition: background-color 0.3s;
        }

        .register-link a:hover {
            background-color: rgba(0, 0, 0, 0.1);
        }

        .double {
            width: 100%;
            display: flex;
            justify-content: space-between;
            gap: 3rem;
        }
		.updateButton{
			padding: 10px 30px;
			background-color: black;
			color: white;
			border-radius: 10px;
			
		}
		.updateMainDiv{
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		}
		
        @media (max-width: 320px) {
            /* smartphones, iPhone, portrait 480x320 phones */
            .register-link {
                flex-wrap: wrap;
                justify-content: center;
            }
        }

        @media (max-width: 481px) {
            /* portrait e-readers (Nook/Kindle), smaller tablets @ 600 or @ 640 wide. */
            .register-container {
                width: 100vw;
            }
        }

        @media (max-width: 641px) {
            /* portrait tablets, portrait iPad, landscape e-readers, landscape 800x480 or 854x480 phones */
            .register-container {
                flex-wrap: wrap;
                gap: 2px;
            }
        }

        @media (max-width: 961px) {
            /* tablet, landscape iPad, lo-res laptops ands desktops */
            .register-container {
                width: 75vw;
            }

            .double {
                flex-wrap: wrap;
                gap: 0;
            }
        }

        @media (max-width: 1281px) {
            .register-container {
                width: 50vw;
                flex-wrap: wrap;
                gap: 2px;
            }
        }
    </style>
</head>
<body>
<%@ include file="nav.jsp" %>
    <h1>Update Profile</h1>
    <div class="updateMainDiv">
    <form action="UpdateProfile" method="post" class="register-container">
        <div class="input-container">
            <label class="input-label" for="firstname">First Name</label>
            <input type="text" id="firstname" name="firstname" value="${user.firstname}" required class="input-field">
        </div>
        <div class="input-container">
            <label class="input-label" for="lastname">Last Name</label>
            <input type="text" id="lastname" name="lastname" value="${user.lastname}" required class="input-field">
        </div>
        <div class="input-container">
            <label class="input-label" for="username">Username</label>
            <input type="text" id="username" name="username" value="${user.username}" required class="input-field">
        </div>
        <div class="input-container">
            <label class="input-label" for="email">Email</label>
            <input type="email" id="email" name="email" value="${user.email}" required class="input-field">
        </div>
        <div class="input-container">
            <label class="input-label" for="phone">Phone Number</label>
            <input type="text" id="phone" name="phone" value="${user.phone}" required class="input-field">
        </div>
        <div class="input-container">
            <label class="input-label" for="address">Address</label>
            <textarea id="address" name="address" required class="input-field">${user.address}</textarea>
        </div>
        <button type="submit" class="updateButton">Update</button>
    </form>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>