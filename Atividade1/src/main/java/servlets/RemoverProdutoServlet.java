package servlets;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Cliente;
import models.Produto;

@WebServlet("/removerProduto")
public class RemoverProdutoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        ArrayList<Produto> produtos = (ArrayList<Produto>) session.getAttribute("produtos");

        produtos.removeIf(produto -> produto.getId() == id);
        response.sendRedirect("listarProdutos");
    }
}
