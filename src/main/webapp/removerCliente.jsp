<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Cliente" %>
<%
    int idCliente = Integer.parseInt(request.getParameter("id"));
    Cliente cliente = (Cliente) request.getAttribute("cliente"); // Certifique-se de passar o cliente para a requisição
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Remover Cliente</title>
</head>
<body>
	 <h1>Remover Cliente</h1>
    <p>Tem certeza que deseja remover o cliente <strong><%= cliente.getNome() %></strong>?</p>
    <form action="removerCliente" method="post">
        <input type="hidden" name="id" value="<%= cliente.getId() %>">
        <input type="submit" value="Confirmar">
    </form>
    <br>
    <a href="listarClientes">Cancelar</a>
</body>
</html>