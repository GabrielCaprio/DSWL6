package com.exemplo;

import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/comentar")
public class ComentarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ArrayList<String> listaComentarios = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comentario = request.getParameter("comentario");

        if (comentario != null && !comentario.isEmpty()) {
            listaComentarios.add(comentario);
        }

        response.setContentType("text/html");

        //resposta HTML
        StringBuilder htmlResponse = new StringBuilder();
        htmlResponse.append("<html><body>")
                    .append("<h1>Comentários Recebidos</h1>")
                    .append("<p>Comentários submetidos até agora:</p>")
                    .append("<ul>");

        for (String c : listaComentarios) {
            htmlResponse.append("<li>").append(c).append("</li>");
        }

        htmlResponse.append("</ul>")
                    .append("<br><a href='index.html'>Voltar para adicionar mais comentários</a>")
                    .append("</body></html>");

        response.getWriter().write(htmlResponse.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.html");
    }
}
