<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read existing Task</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>

<%@include file="header.html"%>
<H1>Read existing Task</H1>

<%
    Task task = (Task) request.getAttribute("task");
%>
<p>Id: <%= task.getId()%></p>
<p>Title: <%= task.getTitle() %></p>
<p>Priority: <%= task.getPriority() %></p>

</body>
</html>
