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

@WebServlet("/adicionarCliente")
public class AdicionarClienteServlet extends HttpServlet {
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        request.getRequestDispatcher("adicionarCliente.jsp").forward(request, response);
    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        HttpSession session = request.getSession();
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) session.getAttribute("clientes");
        if (clientes == null) {
            clientes = new ArrayList<>();
        }
        clientes.add(new Cliente(nome));
        session.setAttribute("clientes", clientes);
        response.sendRedirect("listarClientes");
    }
}
