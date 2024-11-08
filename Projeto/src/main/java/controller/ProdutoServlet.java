package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.ProdutoDAO;
import model.dto.ProdutoDTO;
import util.DatabaseConnection;

@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {

    private ProdutoDAO produtoDAO;

    @Override
    public void init() {
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            produtoDAO = new ProdutoDAO(conn); // A classe ProdutoDAO lida com o banco de dados
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Default action
        }

        try {
            switch (action) {
                case "list":
                    listarProdutos(request, response);
                    break;
                case "create":
                    mostrarFormularioNovoProduto(request, response);
                    break;
                case "edit":
                    mostrarFormEditar(request, response);
                    break;
                case "delete":
                    excluirProduto(request, response);
                    break;
                default:
                    listarProdutos(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Action: " + action);

        try {
            switch (action) {
                case "create":
                    inserirProduto(request, response);
                    break;
                case "edit":
                    atualizarProduto(request, response);
                    break;
                default:
                    listarProdutos(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarProdutos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<ProdutoDTO> listaProdutos = produtoDAO.listarProdutos();

        System.out.println("Produtos listados: " + listaProdutos.size());

        request.setAttribute("listaProdutos", listaProdutos);
        request.getRequestDispatcher("/View/Produtos/produto-lista.jsp").forward(request, response);
    }

    private void mostrarFormEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); // Obtém o id 
        ProdutoDTO produto = produtoDAO.buscarProduto(id); 
        if (produto != null) {
            request.setAttribute("produto", produto); // Adiciona o produto
            request.setAttribute("acao", "editar");  // Define a ação como 'editar'
            request.getRequestDispatcher("/View/Produtos/produto-form.jsp").forward(request, response); // Redireciona para o formulário de edição
        } else {
            // Produto não encontrado, redireciona para a lista de produtos com mensagem de erro
            request.setAttribute("mensagemErro", "Produto não encontrado.");
            listarProdutos(request, response);
        }
    }

    private void mostrarFormularioNovoProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("acao", "create"); // Adiciona o atributo 'acao' create
        request.getRequestDispatcher("/View/Produtos/produto-form.jsp").forward(request, response); // Redireciona para o JSP do formulário
    }

    private void inserirProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));
        String descricao = request.getParameter("descricao");
        String categoria = request.getParameter("categoria");

        if (nome.isEmpty() || descricao.isEmpty() || categoria.isEmpty()) {
            request.setAttribute("mensagemErro", "Campos obrigatórios não preenchidos.");
            listarProdutos(request, response);
        } else {
            ProdutoDTO novoProduto = new ProdutoDTO(nome, preco, descricao, categoria);
            produtoDAO.inserirProduto(novoProduto);
            request.setAttribute("mensagemSucesso", "Produto adicionado com sucesso.");
            listarProdutos(request, response);
        }
    }

    private void atualizarProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        double preco = Double.parseDouble(request.getParameter("preco"));
        String descricao = request.getParameter("descricao");
        String categoria = request.getParameter("categoria");

        if (nome.isEmpty() || descricao.isEmpty() || categoria.isEmpty()) {
            request.setAttribute("mensagemErro", "Campos obrigatórios não preenchidos.");
            listarProdutos(request, response);
        } else {
            ProdutoDTO produtoAtualizado = new ProdutoDTO(id, nome, preco, descricao, categoria);
            produtoDAO.atualizarProduto(produtoAtualizado);
            request.setAttribute("mensagemSucesso", "Produto atualizado com sucesso.");
            listarProdutos(request, response);
        }
    }

    private void excluirProduto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        produtoDAO.excluirProduto(id);
        request.setAttribute("mensagemSucesso", "Produto excluído com sucesso.");
        listarProdutos(request, response);
    }
}