package com.exemplo;

import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/acesso")
public class ContadorUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private HashMap<String, Integer> contadorUsuarios = new HashMap<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nomeUsuario = request.getParameter("nome");

        if (nomeUsuario != null && !nomeUsuario.isEmpty()) {

            contadorUsuarios.put(nomeUsuario, contadorUsuarios.getOrDefault(nomeUsuario, 0) + 1);
        }

        response.setContentType("text/html");

        StringBuilder htmlResponse = new StringBuilder();
        htmlResponse.append("<html><body>")
                    .append("<h1>Contador de Acessos por Usuário</h1>")
                    .append("<p>Acessos de cada usuário:</p>")
                    .append("<ul>");

        for (String usuario : contadorUsuarios.keySet()) {
            htmlResponse.append("<li>")
                        .append(usuario)
                        .append(": ")
                        .append(contadorUsuarios.get(usuario))
                        .append(" acessos</li>");
        }

        htmlResponse.append("</ul>")
                    .append("<br><a href='index.html'>Voltar para adicionar mais acessos</a>")
                    .append("</body></html>");

        response.getWriter().write(htmlResponse.toString());
    }
}
