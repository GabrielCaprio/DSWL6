package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dto.ProdutoDTO;

public class ProdutoDAO { 
    private Connection conn;

    // Construtor que recebe a conexão com o banco
    public ProdutoDAO(Connection conn) {
       
        if (conn == null) {
            throw new IllegalArgumentException("A conexão com o banco de dados não pode ser nula.");
        }
        this.conn = conn;
    }

    // Método para inserir um novo produto
    public void inserirProduto(ProdutoDTO produto) throws SQLException {
        String sql = "INSERT INTO Produtos (nome, preco, descricao, categoria) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getDescricao());
            stmt.setString(4, produto.getCategoria());
            stmt.executeUpdate();
        }
    }

    // Método para listar todos os produtos
    public List<ProdutoDTO> listarProdutos() throws SQLException {
        List<ProdutoDTO> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produtos";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ProdutoDTO produto = new ProdutoDTO(
                    rs.getInt("id"), // Certifique-se de que a coluna "id" existe na tabela
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getString("descricao"),
                    rs.getString("categoria")
                );
                produtos.add(produto);
            }
        }
        return produtos;
    }

    // Método para atualizar um produto existente
    public void atualizarProduto(ProdutoDTO produto) throws SQLException {
        String sql = "UPDATE Produtos SET nome = ?, preco = ?, descricao = ?, categoria = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getDescricao());
            stmt.setString(4, produto.getCategoria());
            stmt.setInt(5, produto.getId()); // Ajuste para utilizar o ID do produto
            stmt.executeUpdate();
        }
    }
    
    public ProdutoDTO buscarProduto(int id) throws SQLException {
        ProdutoDTO produto = null;
        String sql = "SELECT id, nome, preco, descricao, categoria FROM produtos WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id); // Definindo o valor do parâmetro ID na consulta
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Criando o objeto ProdutoDTO a partir dos dados retornados do banco
                    produto = new ProdutoDTO(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getString("descricao"),
                        rs.getString("categoria")
                    );
                }
            }
        }
        
        return produto; // Retorna o produto encontrado ou null se não encontrado
    }

    // Método para excluir um produto
    public void excluirProduto(int id) throws SQLException {
        String sql = "DELETE FROM Produtos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}