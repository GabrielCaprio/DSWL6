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


@WebServlet("/listarCompras")
public class ListarComprasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 HttpSession session = request.getSession();
    	    ArrayList<Cliente> clientes = (ArrayList<Cliente>) session.getAttribute("clientes");
    	    
    	    if (clientes == null || clientes.isEmpty()) {
                request.setAttribute("message", "Nenhum cliente cadastrado.");
                request.getRequestDispatcher("mensagem.jsp").forward(request, response);
                return;
            }
    	    
    	    
    	    String clienteIdParam = request.getParameter("clienteId");
    	    Cliente clienteSelecionado = null;

    	    if (clienteIdParam != null) {
    	        int idCliente = Integer.parseInt(clienteIdParam);

    	        for (Cliente c : clientes) {
    	            if (c.getId() == idCliente) {
    	                clienteSelecionado = c;
    	                break;
    	            }
    	        }

    	        if (clienteSelecionado != null) {
    	            if (clienteSelecionado.getProdutosComprados().isEmpty()) {
    	                request.setAttribute("message", "Nenhuma compra para este cliente.");
    	                request.getRequestDispatcher("mensagem.jsp").forward(request, response);
    	                return; 
    	            }
    	            request.setAttribute("cliente", clienteSelecionado);
    	            request.setAttribute("produtosComprados", clienteSelecionado.getProdutosComprados());
    	        } else {
    	            request.setAttribute("message", "Nenhum cliente selecionado.");
    	        }
    	    } else {
    	        request.setAttribute("message", "ID do cliente não informado.");
    	    }

    	    request.setAttribute("clientes", clientes);

    	    // Redireciona para a página listarCompras.jsp
    	    request.getRequestDispatcher("listarCompras.jsp").forward(request, response);
    }

 }


