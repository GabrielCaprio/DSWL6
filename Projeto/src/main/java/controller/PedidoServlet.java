package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ItemPedidoDAO;
import model.dao.PedidoDAO;
import model.dao.ProdutoDAO;
import model.dto.ItemPedidoDTO;
import model.dto.PedidoDTO;
import model.dto.ProdutoDTO;
import util.DatabaseConnection;

@WebServlet("/PedidoServlet")
public class PedidoServlet extends HttpServlet {

    private PedidoDAO pedidoDAO;
    private ItemPedidoDAO itemPedidoDAO;
    private ProdutoDAO produtoDAO;

    @Override
    public void init() {
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            pedidoDAO = new PedidoDAO(conn); 
            itemPedidoDAO = new ItemPedidoDAO(conn); // lida com os itens de pedidos
            produtoDAO = new ProdutoDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list"; 
        }

        try {
            switch (action) {
                case "list":
                    listarPedidos(request, response);
                    break;
                case "create":
                    mostrarFormularioNovoPedido(request, response);
                    break;
                case "edit":
                    mostrarFormularioEditarPedido(request, response);
                    break;
                case "delete":
                    excluirPedido(request, response);
                    break;
                default:
                    listarPedidos(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "create":
                    inserirPedido(request, response);
                    break;
                case "edit":
                    atualizarPedido(request, response);
                    break;
                default:
                    listarPedidos(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarPedidos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<PedidoDTO> listaPedidos = pedidoDAO.listarPedidos();
        System.out.println("Pedidoss listados: " + listaPedidos.size());
       
        request.setAttribute("listaPedidos", listaPedidos);
        request.getRequestDispatcher("/View/Pedidos/pedido-lista.jsp").forward(request, response);
    }

    private void mostrarFormularioNovoPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    	 List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
         request.setAttribute("listaProdutos", listaProdutos);
        
        request.setAttribute("acao", "create");
        request.getRequestDispatcher("/View/Pedidos/pedido-form.jsp").forward(request, response);
    }

    private void mostrarFormularioEditarPedido(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	PedidoDTO pedido = pedidoDAO.buscarPedido(id);
         if (pedido != null) {
             request.setAttribute("pedido", pedido);
             request.setAttribute("acao", "edit");
             request.getRequestDispatcher("/View/Pedidos/pedido-form.jsp").forward(request, response);
         } else {
             request.setAttribute("mensagemErro", "Pedido não encontrado.");
             listarPedidos(request, response);
         }
     }

    private void inserirPedido(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	 //o total do pedido
        double total = Double.parseDouble(request.getParameter("total"));
        
        // Cria u=PedidoDTO com status "Em preparo"
        PedidoDTO novoPedido = new PedidoDTO(total, "Em preparo");

        // 3. Recupera as quantidades dos produtos que compõem o pedido
        Map<Integer, Integer> quantidades = new HashMap<>();
        for (String paramName : request.getParameterMap().keySet()) {
            if (paramName.startsWith("quantidades[")) {
                // Extrai o id do produto do parâmetro 'quantidades[idProduto]'
                String produtoIdStr = paramName.substring("quantidades[".length(), paramName.length() - 1);
                int produtoId = Integer.parseInt(produtoIdStr);
                int quantidade = Integer.parseInt(request.getParameter(paramName));
                quantidades.put(produtoId, quantidade);
            }
        }

        // Monta os itens do pedido com base nas quantidades e produtos disponíveis
        List<ItemPedidoDTO> itens = new ArrayList<>();
        double totalCalculado = 0.0;
        for (Map.Entry<Integer, Integer> entry : quantidades.entrySet()) {
            int produtoId = entry.getKey();
            int quantidade = entry.getValue();
            if (quantidade > 0) {
                // Recupera o produto com base no ID
                ProdutoDTO produto = produtoDAO.buscarProduto(produtoId);
                if (produto != null) {
                    // Calcula o preço total do item (preço unitário * quantidade)
                    double preco = produto.getPreco();
                    double precoItem = preco * quantidade;
                    totalCalculado += precoItem;
                    
                    // Cria o ItemPedidoDTO com o pedido, produto, quantidade e preço
                    ItemPedidoDTO item = new ItemPedidoDTO(novoPedido.getId(), produtoId, quantidade, preco);
                    itens.add(item);
                }
            }
        }

        // Atualiza o total do pedido após calcular todos os itens
        novoPedido.setTotal(totalCalculado);

        // Adiciona os itens ao pedido
        novoPedido.setItens(itens);

        //  Insere o pedido no banco de dados
        pedidoDAO.inserirPedido(novoPedido);

        // insere cada item do pedido na tabela de itens de pedido
        for (ItemPedidoDTO item : itens) {
            itemPedidoDAO.inserirItemPedido(item);
        }

        // Retorna a resposta ao cliente com a mensagem de sucesso
        request.setAttribute("mensagemSucesso", "Pedido criado com sucesso.");
        listarPedidos(request, response);
    }

    private void atualizarPedido(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        double total = Double.parseDouble(request.getParameter("total"));
        String status = request.getParameter("status");

        PedidoDTO pedidoAtualizado = new PedidoDTO(id, total, status);
        pedidoDAO.atualizarPedido(pedidoAtualizado);
        
        // Recuperar as quantidades dos itens editados
        Map<Integer, Integer> quantidades = new HashMap<>();
        for (String paramName : request.getParameterMap().keySet()) {
            if (paramName.startsWith("quantidades[")) {
                // Extrair o id do produto
                String produtoIdStr = paramName.substring("quantidades[".length(), paramName.length() - 1);
                int produtoId = Integer.parseInt(produtoIdStr);
                int quantidade = Integer.parseInt(request.getParameter(paramName));
                quantidades.put(produtoId, quantidade);
            }
        }
		
        for (Map.Entry<Integer, Integer> entry : quantidades.entrySet()) {
            int produtoId = entry.getKey();
            int quantidade = entry.getValue();
            
            if (quantidade > 0) {
                // Atualizar a quantidade do item no banco de dados
                ItemPedidoDTO item = new ItemPedidoDTO(pedidoAtualizado.getId(), produtoId, quantidade, 0); // Preço será atualizado ou mantido
                itemPedidoDAO.atualizarItemPedido(item);  // Adicione esse método no ItemPedidoDAO
            }
        }

        request.setAttribute("mensagemSucesso", "Pedido atualizado com sucesso.");
        listarPedidos(request, response);
    }

    private void excluirPedido(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        pedidoDAO.excluirPedido(id);
        
        request.setAttribute("mensagemSucesso", "Pedido excluído com sucesso.");
        listarPedidos(request, response);
    }
}