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
 <title>Editar Cliente</title>
</head>
<body>
	<h1>Editar Cliente</h1>
    <form action="editarCliente" method="post">
        <input type="hidden" name="id" value="<%= cliente.getId() %>">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="<%= cliente.getNome() %>" required>
        <input type="submit" value="Salvar">
    </form>
    
    <br>
    <a href="listarClientes">Voltar para Listar Clientes</a>
</body>
</html>