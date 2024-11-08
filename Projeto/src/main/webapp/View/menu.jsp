<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="model.dto.ProdutoDTO" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cardápio</title>
</head>
<body>
    <h1>Bem-vindo ao Cardápio</h1>

    <!-- Botões de navegação para outras funcionalidades -->
    <nav>
        <a href="ProdutoServlet">Gerenciar Produtos</a> |
        <a href="IngredienteServlet">Gerenciar Ingredientes</a> |
        <a href="PedidosServlet">Gerenciar Pedidos</a>
    </nav>
    
    <h2>Cardápio de Produtos</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Categoria</th>
                <th>Descrição</th>
                <th>Preço (R$)</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Recupera a lista de produtos
                List<ProdutoDTO> listaProdutos = (List<ProdutoDTO>) request.getAttribute("listaProdutos");

                if (listaProdutos != null && !listaProdutos.isEmpty()) {
                    for (ProdutoDTO produto : listaProdutos) {
            %>
                <tr>
                    <td><%= produto.getNome() %></td>
                    <td><%= produto.getCategoria() != null ? produto.getCategoria() : "N/A" %></td>
                    <td><%= produto.getDescricao() != null ? produto.getDescricao() : "Sem descrição" %></td>
                    <td><%= produto.getPreco() %></td>
                </tr>
            <% 
                    }
                } else {
            %>
                <tr><td colspan="4">Nenhum produto cadastrado.</td></tr>
            <% 
                }
            %>
        </tbody>
    </table>
</body>
</html>