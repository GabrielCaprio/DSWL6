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

@WebServlet("/removerProdutoCompra")
public class RemoverProdutoCompraServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        int idProduto = Integer.parseInt(request.getParameter("idProduto"));

        HttpSession session = request.getSession();
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) session.getAttribute("clientes");

        Cliente cliente = null;
        for (Cliente c : clientes) {
            if (c.getId() == idCliente) {
                cliente = c;
                break;
            }
        }

        if (cliente != null) {
            cliente.getProdutosComprados().removeIf(produto -> produto.getId() == idProduto);
        }

        response.sendRedirect("listarCompras");
    }
}
