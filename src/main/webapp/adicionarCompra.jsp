<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Cliente" %>
<%@ page import="models.Produto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>Adicionar Produto a Cliente</title>
</head>
<body>
	<h1>Adicionar Produto a Cliente</h1>
    <form action="associarProduto" method="post">
        <label for="clienteId">Selecione um Cliente:</label>
        <select name="clienteId" id="clienteId" required>
            <%
                ArrayList<Cliente> clientes = (ArrayList<Cliente>) request.getAttribute("clientes");
                for (Cliente cliente : clientes) {
            %>
                <option value="<%= cliente.getId() %>"><%= cliente.getNome() %></option>
            <%
                }
            %>
        </select>
        
        <label for="produtoId">Selecione um Produto:</label>
        <select name="produtoId" id="produtoId" required>
            <%
                ArrayList<Produto> produtos = (ArrayList<Produto>) request.getAttribute("produtos");
                for (Produto produto : produtos) {
            %>
                <option value="<%= produto.getId() %>"><%= produto.getNome() %> - R$<%= produto.getPreco() %></option>
            <%
                }
            %>
        </select>
        
        <input type="submit" value="Adicionar">
    </form>
    
    <br>
    <a href="listarCompras">Voltar para Listar Compras</a>
</body>
</html>