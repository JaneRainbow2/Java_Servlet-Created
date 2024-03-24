package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = {"/read-task"})
public class ReadTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Task task = taskRepository.read(id);
        RequestDispatcher dispatcher;
        if (task == null) {
            dispatcher = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.setAttribute("errorMessage", "'Task with ID '" + id + "' not found in To-Do List!'");
            request.setAttribute("errorPath", "url: /read-task");
        } else {
            dispatcher = request.getRequestDispatcher("/WEB-INF/pages/read-task.jsp");
            request.setAttribute("task", task);
        }
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}