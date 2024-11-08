package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.ItemPedidoDTO;

public class ItemPedidoDAO {
    private Connection conn;

    public ItemPedidoDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserirItemPedido(ItemPedidoDTO itemPedido) throws SQLException {
        String sql = "INSERT INTO Itens_Pedido (pedido_id, produto_id, quantidade, preco) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemPedido.getPedidoId());
            stmt.setInt(2, itemPedido.getProdutoId());
            stmt.setInt(3, itemPedido.getQuantidade());
            stmt.setDouble(4, itemPedido.getPreco());
            stmt.executeUpdate();
        }
    }

    public List<ItemPedidoDTO> listarItensPorPedido(int pedidoId) throws SQLException {
    	List<ItemPedidoDTO> itensPedido = new ArrayList<>();
        
        String sql = "SELECT * FROM Itens_Pedido WHERE pedido_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedidoId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ItemPedidoDTO item = new ItemPedidoDTO();
                item.setId(rs.getInt("id"));
                item.setPedidoId(rs.getInt("pedido_id"));
                item.setProdutoId(rs.getInt("produto_id"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPreco(rs.getDouble("preco"));
                itensPedido.add(item);
            }
        }
        
        return itensPedido;
    }
    
 // Atualizar um item do pedido
    public void atualizarItemPedido(ItemPedidoDTO item) throws SQLException {
        String sql = "UPDATE Itens_Pedido SET quantidade = ?, preco = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getPreco());
            stmt.setInt(3, item.getId());
            stmt.executeUpdate();
        }
    }

 // Excluir um item do pedido
    public void excluirItem(int itemId) throws SQLException {
        String sql = "DELETE FROM Itens_Pedido WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        }
    }
    
    public ItemPedidoDTO buscarItemPorId(int itemId) throws SQLException {
        String sql = "SELECT * FROM Itens_Pedido WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int pedidoId = rs.getInt("pedido_id");
                    int produtoId = rs.getInt("produto_id");
                    int quantidade = rs.getInt("quantidade");
                    double preco = rs.getDouble("preco");
                    return new ItemPedidoDTO(itemId, pedidoId, produtoId, quantidade, preco);
                }
            }
        }
        return null; // Caso o item não seja encontrado
    }
}
