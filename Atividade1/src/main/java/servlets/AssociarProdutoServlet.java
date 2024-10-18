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

@WebServlet("/associarProduto")
public class AssociarProdutoServlet extends HttpServlet {
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ArrayList<Cliente> clientes = (ArrayList<Cliente>) session.getAttribute("clientes");
        ArrayList<Produto> produtos = (ArrayList<Produto>) session.getAttribute("produtos");
        
        request.setAttribute("clientes", clientes);
        request.setAttribute("produtos", produtos);
        
        request.getRequestDispatcher("adicionarCompra.jsp").forward(request, response);
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    int idCliente = Integer.parseInt(request.getParameter("clienteId"));
	    int idProduto = Integer.parseInt(request.getParameter("produtoId"));

	    HttpSession session = request.getSession();
	    ArrayList<Cliente> clientes = (ArrayList<Cliente>) session.getAttribute("clientes");
	    ArrayList<Produto> produtos = (ArrayList<Produto>) session.getAttribute("produtos");

	    Cliente cliente = null;
	    for (Cliente c : clientes) {
	        if (c.getId() == idCliente) {
	            cliente = c;
	            break;
	        }
	    }

	    if (cliente != null) {
	        System.out.println("Cliente encontrado: " + cliente.getNome());
	    } else {
	        System.out.println("Cliente não encontrado com ID: " + idCliente);
	    }

	    Produto produto = null;
	    ArrayList<Produto> produtosarray = (ArrayList<Produto>) session.getAttribute("produtos");
	    for (Produto p : produtosarray) {
	        if (p.getId() == idProduto) {
	            produto = p;
	            break;
	        }
	    }

	    if (produto != null) {
	        System.out.println("Produto encontrado: " + produto.getNome());
	    } else {
	        System.out.println("Produto não encontrado com ID: " + idProduto);
	    }

	    //Adiciona o produto
	    if (cliente != null && produto != null) {
	        ArrayList<Produto> produtosComprados = cliente.getProdutosComprados();
	        if (produtosComprados == null) {
	            produtosComprados = new ArrayList<>();
	        }

	        System.out.println("Adicionando produto: " + produto.getNome() + " à lista de compras do cliente: " + cliente.getNome());
	        produtosComprados.add(produto);
	        cliente.setProdutosComprados(produtosComprados);
	        System.out.println("Produto adicionado com sucesso.");
	    }

	    response.sendRedirect("listarCompras");
	    System.out.println("Redirecionando para listarCompras.");
	}
}
