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
<title>Remover Produto</title>
</head>
<body>
	 <h1>Remover Produto</h1>
    <p>Tem certeza que deseja remover o produto <strong><%= produto.getNome() %></strong>?</p>
    <form action="removerProduto" method="post">
        <input type="hidden" name="id" value="<%= produto.getId() %>">
        <input type="submit" value="Confirmar">
    </form>
    <br>
    <a href="listarProdutos">Cancelar</a>
</body>
</html>