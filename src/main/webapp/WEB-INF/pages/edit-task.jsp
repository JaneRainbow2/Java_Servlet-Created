<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page import="com.softserve.itacademy.model.Priority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit existing Task</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>

</head>
<body>
    <%@include file="header.html"%>
    <br>
<h2>Edit existing Task</h2>
<form action="/edit-task" method="post">
    <%
        Task task = (Task) request.getAttribute("task");
    %>
    <table>
        <tr>
            <td><label for="id">Id: </label></td>
            <td><input type="text" id="id" name="id" value="<%=task.getId()%>" disabled></td>
        </tr>
        <tr>
            <td>
                <label for="taskname">Name: </label>
            </td>
            <td>
                <input type="text" id="taskname" name="taskname" value="<%=task.getTitle()%>">
            </td>
        </tr>
        <tr>
            <td>
                <label for="priority">Priority: </label>
            </td>
            <td>
                <select id="priority" name="priority">
                    <option value=<%=task.getPriority()%>><%=task.getPriority()%>
                    </option>
                    <option value<%=Priority.LOW%>>Low</option>
                    <option value<%=Priority.MEDIUM%>>Medium</option>
                    <option value<%=Priority.HIGH%>>High</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Update">
            </td>
            <td>
                <input type="submit" value="Clear">
            </td>
        </tr>
    </table>
</form>



</body>
</html>
