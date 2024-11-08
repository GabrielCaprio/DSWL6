<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="model.dto.ProdutoDTO" %>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Produtos</title>
</head>
<body>
    <h1>Produtos</h1>
    <a href="ProdutoServlet?action=create">Novo Produto</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Preço</th>
                <th>Descrição</th>
                <th>Categoria</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
          <%
                List<ProdutoDTO> listaProdutos = (List<ProdutoDTO>) request.getAttribute("listaProdutos");
                if (listaProdutos != null && !listaProdutos.isEmpty()) {
                    for (ProdutoDTO produto : listaProdutos) {
            %>
                <tr>
                    <td><%= produto.getId() %></td>
                    <td><%= produto.getNome() %></td>
                    <td><%= produto.getPreco() %></td>
                    <td><%= produto.getDescricao() %></td>
                    <td><%= produto.getCategoria() %></td>
                    <td>
                        <a href="ProdutoServlet?action=edit&id=<%= produto.getId() %>">Editar</a> |
                        <a href="ProdutoServlet?action=delete&id=<%= produto.getId() %>" onclick="return confirm('Confirma a exclusão?')">Excluir</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="6">Nenhum produto encontrado.</td></tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>