package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.RequestDispatcher;
    import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-task")
public class CreateTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;
    private Priority priority;

    private String title;
    private String priorityIncome = Priority.LOW.toString();

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
        priorityIncome = Priority.LOW.toString();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title_content", title == null ? "" : this.title);
        request.setAttribute("priority", Priority.values());
        request.setAttribute("priorityIncome", priorityIncome);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/create-task.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("Content-Type", "text/html;charset=UTF-8");

        this.title = request.getParameter("title");
        this.priorityIncome = request.getParameter("priority");

        request.setAttribute("title_content", title);
        request.setAttribute("priority", priority);
        request.setAttribute("priorityIncome", priorityIncome);

        Task newTask = new Task(title, Priority.valueOf(priorityIncome));

        if (title == null || title.isEmpty()) {
            request.setAttribute("error", "Expected value in input field but it was empty!");
            doGet(request, response);
            return;
        }

        if (priorityIncome == null || priorityIncome.isEmpty()) {
            request.setAttribute("error", "Expected value in drop-down list but it was empty!");
            doGet(request, response);
            return;
        }

        if (taskRepository.create(newTask)) {
            response.sendRedirect("/tasks-list");
        } else {
            request.setAttribute("error", "Task with a given name already exists!");
            doGet(request, response);
        }
    }
}
