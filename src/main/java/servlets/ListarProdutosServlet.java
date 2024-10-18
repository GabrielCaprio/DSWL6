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

@WebServlet("/listarProdutos")
public class ListarProdutosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        ArrayList<Produto> produtos = (ArrayList<Produto>) session.getAttribute("produtos");

        if (produtos == null || produtos.isEmpty()) {
            request.setAttribute("message", "Nenhum produto cadastrado.");
            request.getRequestDispatcher("mensagem.jsp").forward(request, response);
            return;
        }

        request.setAttribute("produtos", produtos);
        request.getRequestDispatcher("listarProdutos.jsp").forward(request, response);
    }
}