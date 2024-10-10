package com.exemplo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/contador")
public class ContadorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private int contadorAcessos = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        contadorAcessos++;

        response.setContentType("text/html");

        response.getWriter().append("<html><body>")
                            .append("<h1>Contador de Acessos</h1>")
                            .append("<p>Este servlet foi acessado ").append(String.valueOf(contadorAcessos)).append(" vezes.</p>")
                            .append("</body></html>");
    }
}