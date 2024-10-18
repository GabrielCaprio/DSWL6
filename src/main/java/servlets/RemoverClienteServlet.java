package servlets;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Cliente;

@WebServlet("/removerCliente")
public class RemoverClienteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) session.getAttribute("clientes");

        clientes.removeIf(cliente -> cliente.getId() == id);
        response.sendRedirect("listarClientes");
    }
}

