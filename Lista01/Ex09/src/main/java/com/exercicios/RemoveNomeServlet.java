package com.exercicios;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/removeNome")
public class RemoveNomeServlet extends HttpServlet {
    private static ArrayList<String> nomes = new ArrayList<>();

    static {
        nomes.add("Maria");
        nomes.add("Joao");
        nomes.add("Ana");
        nomes.add("Carlos");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String nome = request.getParameter("nome");

        if (nome != null && !nome.isEmpty()) {
            if (nomes.contains(nome)) {
                nomes.remove(nome);
                out.println("<h2>Nome '" + nome + "' removido com sucesso!</h2>");
            } else {
                out.println("<h2>Nome '" + nome + "' não encontrado na lista.</h2>");
            }
        } else {
            out.println("<h2>Por favor, forneça um nome válido como parâmetro.</h2>");
        }

        out.println("<h3>Nomes restantes no ArrayList:</h3>");
        out.println("<ul>");
        for (String n : nomes) {
            out.println("<li>" + n + "</li>");
        }
        out.println("</ul>");

        out.println("<p><a href='/Lista01/addNome?nome=NovoNome'>Adicionar um novo nome</a></p>");
    }
}
