package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.IngredienteDAO;
import model.dao.ProdutoDAO;
import model.dto.IngredienteDTO;
import model.dto.ProdutoDTO;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
    private ProdutoDAO produtoDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection conn = DatabaseConnection.getConnection();
            produtoDAO = new ProdutoDAO(conn);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Carrega todos os produtos para exibição no cardápio
            List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();
            request.setAttribute("listaProdutos", listaProdutos);
            request.getRequestDispatcher("/View/menu.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}