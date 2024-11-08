package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.IngredienteDAO;
import model.dto.IngredienteDTO;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/IngredienteServlet")
public class IngredienteServlet extends HttpServlet {

    private IngredienteDAO ingredienteDAO;

    @Override
    public void init() {
        Connection conn = null;
        
		try {
			conn = DatabaseConnection.getConnection();
			ingredienteDAO = new IngredienteDAO(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Utiliza a classe de conexão com o banco
        
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
                    listarIngredientes(request, response);
                    break;
                case "create":
                    mostrarFormularioNovoIngrediente(request, response); // Ação para exibir o formulário de novo ingrediente
                    break;
                case "edit":
                    mostrarFormEditar(request, response);
                    break;
                case "delete":
                    excluirIngrediente(request, response);
                    break;
                default:
                    listarIngredientes(request, response);
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
                    inserirIngrediente(request, response);
                    break;
                case "edit":
                    atualizarIngrediente(request, response);
                    break;
                default:
                    listarIngredientes(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listarIngredientes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<IngredienteDTO> listaIngredientes = ingredienteDAO.listarIngredientes();
        
        System.out.println("Ingredientes listados: " + listaIngredientes.size());
        
        request.setAttribute("listaIngredientes", listaIngredientes);
        request.getRequestDispatcher("/View/Ingredientes/ingredientes.jsp").forward(request, response);
    }

    private void mostrarFormEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        IngredienteDTO ingrediente = ingredienteDAO.buscarIngrediente(id);
        request.setAttribute("ingrediente", ingrediente);
        request.setAttribute("acao", "editar");  //'acao' é setado para "editar"
        request.getRequestDispatcher("/View/Ingredientes/ingrediente-form.jsp").forward(request, response);
    }
    
    private void mostrarFormularioNovoIngrediente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("acao", "create"); // Adiciona o atributo 'acao' create
        request.getRequestDispatcher("/View/Ingredientes/ingrediente-form.jsp").forward(request, response); // Redireciona para o JSP do formulário
    }

    private void inserirIngrediente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String nome = request.getParameter("nome");
        double quantidade = Double.parseDouble(request.getParameter("quantidade"));
        String unidade = request.getParameter("unidade");

        if (nome.isEmpty() || unidade.isEmpty()) {
            request.setAttribute("mensagem", "Campos obrigatórios não preenchidos.");
            request.setAttribute("tipoMensagem", "erro");
            listarIngredientes(request, response);
        } else {
            IngredienteDTO novoIngrediente = new IngredienteDTO(nome, quantidade, unidade);
            ingredienteDAO.inserirIngrediente(novoIngrediente);
            request.setAttribute("mensagem", "Ingrediente adicionado com sucesso.");
            request.setAttribute("tipoMensagem", "sucesso");
            listarIngredientes(request, response);
        }
    }

    private void atualizarIngrediente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        double quantidade = Double.parseDouble(request.getParameter("quantidade"));
        String unidade = request.getParameter("unidade");
        
     // Verifique se os campos estão sendo recebidos corretamente
        System.out.println("Nome: " + nome + ", Quantidade: " + quantidade + ", Unidade: " + unidade);
        
        if (nome.isEmpty() || unidade.isEmpty()) {
            request.setAttribute("mensagemErro", "Campos obrigatórios não preenchidos.");
            listarIngredientes(request, response);
        } else {
            IngredienteDTO ingredienteAtualizado = new IngredienteDTO(id, nome, quantidade, unidade);
            ingredienteDAO.atualizarIngrediente(ingredienteAtualizado);
            request.setAttribute("mensagemSucesso", "Ingrediente atualizado com sucesso.");
            listarIngredientes(request, response);
        }
    }

    private void excluirIngrediente(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        ingredienteDAO.excluirIngrediente(id);
        request.setAttribute("mensagemSucesso", "Ingrediente excluído com sucesso.");
        listarIngredientes(request, response);
    }

   
}
