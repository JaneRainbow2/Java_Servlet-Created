<%@ page import="com.softserve.itacademy.model.Priority" %>
<%@ page import="com.softserve.itacademy.controller.UpdateTaskServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit existing Task</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>

</head>
<body>
<%@include file="header.html" %>
<br>
<h2>Edit existing Task</h2>
<form action="/edit-task" method="post">
    <tr>
        <td><label for="id">Id: </label></td>
        <td><input type="text" id="id" name="id" value="<%=UpdateTaskServlet.taskId%>"></td>
    </tr>
    <br>
    <tr>
        <td>
            <label for="title">Name: </label>
        </td>
        <td>
            <input type="text" id="title" name="title" value="<%=request.getAttribute("title")%>">
        </td>
    </tr>
    <br>
    <tr>
        <td>
            <label for="priority">Priority: </label>
        </td>
        <td>
            <select id="priority" name="priority">

                <option value="<%=request.getAttribute("priority")%>" selected><%=request.getAttribute("priority")%>
                </option>

                <option value<%=Priority.LOW%>>Low</option>
                <option value<%=Priority.MEDIUM%>>Medium</option>
                <option value<%=Priority.HIGH%>>High</option>
            </select>
        </td>
    </tr>
    <br><br>
    <tr>
        <td>
            <input type="submit" value="Update">
        </td>
    </tr>
</form>
<% String message = (String) request.getAttribute("messenger"); %>
<% if (message != null) { %>
<div><%= message %>
</div>
<% } %>
</body>
</html>
