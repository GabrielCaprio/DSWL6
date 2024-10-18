package com.exercicio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todolist")
public class ToDoServlet extends HttpServlet {
    private List<String> tasks;

    @Override
    public void init() throws ServletException {
        tasks = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head><title>Lista de Tarefas</title></head>");
        out.println("<body>");
        out.println("<h1>Lista de Tarefas</h1>");
        out.println("<form method='POST' action='/todolist'>");
        out.println("Tarefa: <input type='text' name='task' required>");
        out.println("<input type='submit' value='Adicionar'>");
        out.println("</form>");

        out.println("<h2>Tarefas Cadastradas:</h2>");
        out.println("<ul>");
        for (String task : tasks) {
            out.println("<li>" + task + "</li>");
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String task = req.getParameter("task");
        if (task != null && !task.isEmpty()) {
            tasks.add(task);
        }
        resp.sendRedirect("todolist");
    }
}
