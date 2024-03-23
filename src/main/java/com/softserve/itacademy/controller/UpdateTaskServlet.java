package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-task")
public class UpdateTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;
    private Task task;
    public static int taskId;

    @Override
    public void init() throws ServletException {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        taskId = Integer.parseInt(request.getParameter("id"));
        task = taskRepository.read(taskId);
        if (task == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("url", "/edit-task");
            request.getRequestDispatcher("/WEB-INF/pages/error-update.jsp").forward(request, response);
            return;
        }
        request.setAttribute("task", task);
        request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (task != null) {
            task.setTitle(request.getParameter("taskname"));
            String priorityString = request.getParameter("priority");
            Priority priority = Priority.valueOf(priorityString.toUpperCase());
            task.setPriority(priority);
            taskRepository.update(task);

            response.sendRedirect("/tasks-list");

        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            response.getWriter().write("Task with a given name already exists!");
            response.getWriter().write("value=\"Task #2\"");
            response.getWriter().write("value=\"MEDIUM\" selected");
            response.getWriter().flush();
        }
    }
}
