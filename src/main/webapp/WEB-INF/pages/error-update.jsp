<%@ page import="com.softserve.itacademy.controller.UpdateTaskServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>

</head>
<body>
<%@include file="header.html" %>
<br>
<form action="/error" method="post">
    <h2>Task with ID '<%=UpdateTaskServlet.taskId%>' not found in To-Do List!</h2>
    <h3>url: <%= request.getAttribute("url") %>
    </h3>
</form>
</body>
</html>
