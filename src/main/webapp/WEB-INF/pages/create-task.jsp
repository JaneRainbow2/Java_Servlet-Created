<%@ page import="com.softserve.itacademy.model.Priority" %>
<%@ page import="com.softserve.itacademy.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new Task</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>
    
</head>
<body>
    <%@include file="header.html"%>

    <h1>Create new Task</h1>

    <form action="/create-task" method="post">
        <label for="title">Name:</label>
        <input type="text" id="title" name="title" value="<%= request.getAttribute("title_content")%>"><br>
        <label for="priority">Priority:</label>
        <%
            String selectedValue = (String) request.getAttribute("priorityIncome");
        %>

        <select name="priority" id="priority">
            <%
                for (Priority priority : (Priority[]) request.getAttribute("priority")) {
                    if (priority.toString().equals(selectedValue)) {
            %>
                            <option value="<%= priority %>" selected><%= priority %></option>
            <%
                    } else {
            %>
                            <option value="<%= priority %>"><%= priority %></option>
            <%
                    }
                }
            %>
        </select>
        <br>
        <input type="submit" value="Create">
    </form>
    <p>
        <%= request.getAttribute("error") == null ? "" : request.getAttribute("error") %>
    </p>

</body>
</html>
