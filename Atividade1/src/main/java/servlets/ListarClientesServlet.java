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

@WebServlet("/listarClientes")
public class ListarClientesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 HttpSession session = request.getSession();
         ArrayList<Cliente> clientes = (ArrayList<Cliente>) session.getAttribute("clientes");

         if (clientes == null || clientes.isEmpty()) {
             request.setAttribute("message", "Nenhum cliente cadastrado.");
             request.getRequestDispatcher("mensagem.jsp").forward(request, response);
             return;
         }

         request.setAttribute("clientes", clientes);
         request.getRequestDispatcher("listarClientes.jsp").forward(request, response);
     }
    
}

