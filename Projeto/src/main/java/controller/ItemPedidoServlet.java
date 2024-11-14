package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.IngredienteDAO;
import model.dao.ItemPedidoDAO;
import model.dao.PedidoDAO;
import model.dao.ProdutoDAO;
import model.dto.IngredienteDTO;
import model.dto.ItemPedidoDTO;
import model.dto.ProdutoDTO;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ItemPedidoServlet")
public class ItemPedidoServlet extends HttpServlet {

    private PedidoDAO pedidoDAO;
    private ItemPedidoDAO itemPedidoDAO;
    private ProdutoDAO produtoDAO;

    @Override
    public void init() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            pedidoDAO = new PedidoDAO(conn);
            itemPedidoDAO = new ItemPedidoDAO(conn);
            produtoDAO = new ProdutoDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String pedidoIdStr = request.getParameter("pedidoId");
        if (pedidoIdStr == null || pedidoIdStr.isEmpty()) {
            throw new IllegalArgumentException("Pedido ID não encontrado.");
        }
        int pedidoId = Integer.parseInt(pedidoIdStr);
        

        try {
            if ("edit".equals(action)) {
                mostrarFormularioEditarItens(request, response, pedidoId);
            } else if ("delete".equals(action)) {
                excluirItem(request, response, pedidoId);
            } else {
                listarItensPedido(request, response, pedidoId);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        // Verifica a ação para tratar a atualização da quantidade
        if ("update".equals(action)) {
            try {
                editarItemPedido(request, response); // Atualiza a quantidade do item
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Erro ao atualizar item", e);
            }
        }
    }

    // Listar os itens do pedido
    private void listarItensPedido(HttpServletRequest request, HttpServletResponse response, int pedidoId)
            throws SQLException, ServletException, IOException {
    	List<ItemPedidoDTO> itensPedido = itemPedidoDAO.listarItensPorPedido(pedidoId);
    	
    	// Carrega a lista de produtos para cada item
        for (ItemPedidoDTO item : itensPedido) {
            ProdutoDTO produto = produtoDAO.buscarProduto(item.getProdutoId());
            item.setProduto(produto);  // Define o produto no item (se você tiver um campo para isso)
        }
        
        request.setAttribute("itensPedido", itensPedido);
        request.getRequestDispatcher("/View/ItemPedido/itens-pedido.jsp").forward(request, response);
    }

    // Exibir formulário de edição de itens
    private void mostrarFormularioEditarItens(HttpServletRequest request, HttpServletResponse response, int pedidoId)
            throws SQLException, ServletException, IOException {
    	 List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos(); // Lista de produtos
    	    List<ItemPedidoDTO> itensPedido = itemPedidoDAO.listarItensPorPedido(pedidoId); // Itens do pedido

    	    // Passa os itens e produtos para a JSP
    	    request.setAttribute("itensPedido", itensPedido);
    	    request.setAttribute("listaProdutos", listaProdutos);
    	    request.getRequestDispatcher("/View/ItemPedido/item-pedido-form.jsp").forward(request, response);
    }

    // Excluir um item do pedido
    private void excluirItem(HttpServletRequest request, HttpServletResponse response, int pedidoId)
            throws SQLException, IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        itemPedidoDAO.excluirItem(itemId);

        // Redireciona após excluir o item
        response.sendRedirect("ItemPedidoServlet?action=list&pedidoId=" + pedidoId);
    }

    // Editar um item do pedido
    private void editarItemPedido(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	// Obtém o ID do pedido
        int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));
        
        // Itera sobre os parâmetros para encontrar cada item atualizado
        for (ItemPedidoDTO item : itemPedidoDAO.listarItensPorPedido(pedidoId)) {
            String quantidadeParam = request.getParameter("quantidade_" + item.getId());
            if (quantidadeParam != null) {
                int quantidade = Integer.parseInt(quantidadeParam);
                item.setQuantidade(quantidade);
                itemPedidoDAO.atualizarItemPedido(item); // Atualiza o item no banco
            }
        }

        // Redireciona para a tela de itens após a atualização
        response.sendRedirect("ItemPedidoServlet?action=edit&pedidoId=" + pedidoId);
    }
}

