package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/LanchoneteDB"; // URL do seu banco de dados
    private static final String USER = "root"; // Usuário do banco de dados
    private static final String PASSWORD = "123456"; // Senha do banco de dados

    // Método para obter uma conexão
    public static Connection getConnection() throws SQLException {
    	 try {
             // A classe DriverManager gerencia as conexões JDBC e retorna uma conexão válida
             return DriverManager.getConnection(URL, USER, PASSWORD);
         } catch (SQLException e) {
             // Em caso de falha, o erro é lançado para que o código chamador o trate
             throw new SQLException("Erro ao conectar ao banco de dados: " + e.getMessage());
         }
     }
    
    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
