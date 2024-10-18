<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Cliente" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar Clientes</title>
</head>
<body>
	<h1>Lista de Clientes</h1>
    <%
        ArrayList<Cliente> clientes = (ArrayList<Cliente>) request.getAttribute("clientes");
        if (clientes == null || clientes.isEmpty()) {
    %>
    <p>Nenhum cliente cadastrado.</p>
    <%
        } else {
    %>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Ações</th>
        </tr>
        <%
            for (Cliente cliente : clientes) {
        %>
        <tr>
            <td><%= cliente.getId() %></td>
            <td><%= cliente.getNome() %></td>
            <td>
                <form action="editarCliente" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= cliente.getId() %>">
                    <input type="text" name="nome" placeholder="Novo Nome" required>
                    <input type="submit" value="Editar">
                </form>
                <form action="removerCliente" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= cliente.getId() %>">
                    <input type="submit" value="Remover">
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        }
    %>
    <br>
    <a href="adicionarCliente">Adicionar Novo Cliente</a>
    <nav>
        <a href="listarProdutos">Ir para Listar Produtos</a> |
        <a href="listarCompras">Ir para Listar Compras</a>
    </nav>
    
</body>
</html>