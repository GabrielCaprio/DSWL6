package com.exercicios;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/produtos")
public class ProdutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private List<Produto> produtos = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String precoStr = request.getParameter("preco");

        if (nome != null && precoStr != null) {
            double preco = Double.parseDouble(precoStr);

            Produto produto = new Produto(nome, preco);
            produtos.add(produto);
        }

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Cadastro de Produtos</h2>");
        out.println("<form method='post' action='/Lista01/produtos'>");
        out.println("Nome do Produto: <input type='text' name='nome'><br>");
        out.println("Preço: <input type='text' name='preco'><br>");
        out.println("<input type='submit' value='Cadastrar'><br>");
        out.println("</form>");

        out.println("<h2>Produtos Cadastrados</h2>");
        if (produtos.isEmpty()) {
            out.println("<p>Nenhum produto cadastrado ainda.</p>");
        } else {
            out.println("<ul>");
            for (Produto produto : produtos) {
                out.println("<li>" + produto.getNome() + " - R$ " + produto.getPreco() + "</li>");
            }
            out.println("</ul>");
        }
        out.println("</body></html>");
    }
}
