package servlets;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Produto;

@WebServlet("/editarProduto")
public class EditarProdutoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));
        HttpSession session = request.getSession();
        ArrayList<Produto> produtos = (ArrayList<Produto>) session.getAttribute("produtos");

        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                produto.setNome(nome);
                produto.setPreco(preco);
                break;
            }
        }
        response.sendRedirect("listarProdutos");
    }
}

