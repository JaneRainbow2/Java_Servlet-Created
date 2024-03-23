package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-task")
public class DeleteTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init()  {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (taskRepository.delete(Integer.parseInt(request.getParameter("id")))){
            response.setStatus(HttpServletResponse.SC_SEE_OTHER);
            response.sendRedirect("/tasks-list");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("errorMessage", "'Task with ID '" + request.getParameter("id") + "' not found in To-Do List!'");
            request.setAttribute("errorPath", "url: /delete-task");

            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        }
    }
}
