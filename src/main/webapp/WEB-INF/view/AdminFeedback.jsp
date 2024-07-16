<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Feedbacks</title>
    <style>
        /* Layout */
        .container {
            display: flex;
            justify-content: space-between;
        }

        .content {
            width: 75%;
            padding: 20px;
        }

        /* Table styling */
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
            font-size: 16px;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="div">
            <%@ include file="sidebar.jsp" %>
        </div>
        <div class="content">
            <h2>Feedbacks</h2>
            
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Message</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="feedback" items="${feedbackList}">
                        <tr>
                            <td>${feedback.name}</td>
                            <td>${feedback.email}</td>
                            <td>${feedback.message}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>
