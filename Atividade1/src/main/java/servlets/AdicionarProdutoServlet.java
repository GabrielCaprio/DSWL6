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

@WebServlet("/adicionarProduto")
public class AdicionarProdutoServlet extends HttpServlet {
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
        request.getRequestDispatcher("adicionarProduto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));
        HttpSession session = request.getSession();
        ArrayList<Produto> produtos = (ArrayList<Produto>) session.getAttribute("produtos");
        if (produtos == null) {
            produtos = new ArrayList<>();
        }
        produtos.add(new Produto(nome, preco));
        session.setAttribute("produtos", produtos);
        response.sendRedirect("listarProdutos");
    }
}
