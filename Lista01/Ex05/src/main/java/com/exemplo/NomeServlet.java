package com.exemplo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/addNome")
public class NomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ArrayList<String> listaNomes = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
        String nome = request.getParameter("nome");

        if (nome != null && !nome.isEmpty()) {
            listaNomes.add(nome);
        }

        response.setContentType("text/html");

        StringBuilder htmlResponse = new StringBuilder();
        htmlResponse.append("<html><body>")
                    .append("<h1>Armazenamento de Nomes</h1>")
                    .append("<p>Nomes armazenados até agora:</p>")
                    .append("<ul>");

        for (String n : listaNomes) {
            htmlResponse.append("<li>").append(n).append("</li>");
        }

        htmlResponse.append("</ul>")
                    .append("<br><a href='index.html'>Adicionar mais nomes</a>")
                    .append("</body></html>");

        response.getWriter().write(htmlResponse.toString());
    }
}
