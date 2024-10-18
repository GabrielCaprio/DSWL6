<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.Produto" %>
<%
    int idProduto = Integer.parseInt(request.getParameter("id"));
    Produto produto = (Produto) request.getAttribute("produto"); // Certifique-se de passar o produto para a requisição
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Produto</title>
</head>
<body>
	 <h1>Editar Produto</h1>
    <form action="editarProduto" method="post">
        <input type="hidden" name="id" value="<%= produto.getId() %>">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="<%= produto.getNome() %>" required>
        <label for="preco">Preço:</label>
        <input type="number" id="preco" name="preco" value="<%= produto.getPreco() %>" step="0.01" required>
        <input type="submit" value="Salvar">
    </form>
    
    <br>
    <a href="listarProdutos">Voltar para Listar Produtos</a>
</body>
</html>