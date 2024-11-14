package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dto.IngredienteDTO;

public class IngredienteDAO {
    private Connection conn;

    public IngredienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserirIngrediente(IngredienteDTO ingrediente) throws SQLException {
        String sql = "INSERT INTO Ingredientes (nome, quantidade_estoque, unidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ingrediente.getNome());
            stmt.setDouble(2, ingrediente.getQuantidadeEstoque());
            stmt.setString(3, ingrediente.getUnidade());
            stmt.executeUpdate();
        }
    }

    public List<IngredienteDTO> listarIngredientes() throws SQLException {
        List<IngredienteDTO> ingredientes = new ArrayList<>();
        String sql = "SELECT * FROM Ingredientes";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                IngredienteDTO ingrediente = new IngredienteDTO(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("quantidade_estoque"),
                    rs.getString("unidade")
                );
                ingredientes.add(ingrediente);
            }
        }
        return ingredientes;
    }

    public void atualizarIngrediente(IngredienteDTO ingrediente) throws SQLException {
        String sql = "UPDATE Ingredientes SET nome = ?, quantidade_estoque = ?, unidade = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ingrediente.getNome());
            stmt.setDouble(2, ingrediente.getQuantidadeEstoque());
            stmt.setString(3, ingrediente.getUnidade());
            stmt.setInt(4, ingrediente.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirIngrediente(int id) throws SQLException {
        String sql = "DELETE FROM Ingredientes WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public IngredienteDTO buscarIngrediente(int id) throws SQLException {
        String sql = "SELECT * FROM Ingredientes WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Retorna o ingrediente com base no ID encontrado
                    return new IngredienteDTO(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("quantidade_estoque"),
                        rs.getString("unidade")
                    );
                }
            }
        }
        return null; // Retorna null caso o ingrediente não seja encontrado
    }

}
