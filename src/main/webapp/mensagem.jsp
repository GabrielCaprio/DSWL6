<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastros</title>
</head>
<body>
	<h1>Cadastros</h1>
    <p><%= request.getAttribute("message") %></p>
    <br>
    <a href="adicionarCliente.jsp">Cadastrar Cliente</a> | 
    <a href="adicionarProduto.jsp">Cadastrar Produto</a> | 
    <a href="associarProduto">Adicionar Produto a Cliente</a>
</body>
</html>