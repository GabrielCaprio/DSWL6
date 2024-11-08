package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dto.ItemPedidoDTO;
import model.dto.PedidoDTO;

public class PedidoDAO {
    private Connection conn;

    public PedidoDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserirPedido(PedidoDTO pedido) throws SQLException {
    	
    	
    	if (conn == null) {
            throw new SQLException("Conexão com o banco de dados não inicializada.");
        }
        if (pedido == null || pedido.getStatus() == null || pedido.getTotal() == 0) {
            throw new IllegalArgumentException("Pedido, status e total são obrigatórios.");
        }
    	 String sql = "INSERT INTO Pedidos (data, status, total) VALUES (CURRENT_TIMESTAMP, ?, ?)";
    	    try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
    	        stmt.setString(1, pedido.getStatus());
    	        stmt.setDouble(2, pedido.getTotal());
    	        stmt.executeUpdate();

    	        ResultSet rs = stmt.getGeneratedKeys();
    	        if (rs.next()) {
    	            pedido.setId(rs.getInt(1));
    	        }

    	     // Inserir itens do pedido
    	        if (pedido.getItens() != null && !pedido.getItens().isEmpty()) {
    	            ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO(conn);
    	            for (ItemPedidoDTO item : pedido.getItens()) {
    	                if (item != null && item.getProdutoId() != 0 && item.getQuantidade() > 0 && item.getPreco() > 0) {
    	                    item.setPedidoId(pedido.getId()); // Associar o ID do pedido ao item
    	                    itemPedidoDAO.inserirItemPedido(item); // Inserir o item no banco
    	                } else {
    	                    throw new IllegalArgumentException("Item inválido no pedido.");
    	                }
    	            }
    	        } else {
    	            throw new IllegalArgumentException("Não há itens no pedido.");
    	        }
        }
    }

    public List<PedidoDTO> listarPedidos() throws SQLException {
        List<PedidoDTO> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedidos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PedidoDTO pedido = new PedidoDTO(
                    rs.getInt("id"),
                    rs.getDate("data"),
                    rs.getString("status"),
                    rs.getDouble("total"),
                    null // Itens do pedido serão carregados separadamente
                );
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    public void atualizarPedido(PedidoDTO pedido) throws SQLException {
        String sql = "UPDATE Pedidos SET data = ?, status = ?, total = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new Date(pedido.getData().getTime()));
            stmt.setString(2, pedido.getStatus());
            stmt.setDouble(3, pedido.getTotal());
            stmt.setInt(4, pedido.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirPedido(int id) throws SQLException {
        String sql = "DELETE FROM Pedidos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
 // Método para buscar um pedido específico
    public PedidoDTO buscarPedido(int id) throws SQLException {
        String sql = "SELECT * FROM Pedidos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PedidoDTO(
                        rs.getInt("id"),
                        rs.getDate("data"),
                        rs.getString("status"),
                        rs.getDouble("total"),
                        null // Itens do pedido podem ser carregados separadamente
                    );
                }
            }
        }
        return null;
    }
}
