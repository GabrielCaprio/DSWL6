<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Produto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar Produtos</title>
</head>
<body>
	 <<h1>Listar Produtos</h1>
	 <%
        ArrayList<Produto> produtos = (ArrayList<Produto>) request.getAttribute("produtos");
        if (produtos == null || produtos.isEmpty()) {
    %>
    <p>Nenhum produto cadastrado.</p>
    <%
        } else {
    %>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Preço</th>
            <th>Ações</th>
        </tr>
        <%
            for (Produto produto : produtos) {
        %>
        <tr>
            <td><%= produto.getId() %></td>
            <td><%= produto.getNome() %></td>
            <td>R$ <%= produto.getPreco() %></td>
            <td>
                <form action="editarProduto" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= produto.getId() %>">
                    <input type="text" name="nome" placeholder="Novo Nome" required>
                    <input type="number" name="preco" placeholder="Novo Preço" step="0.01" required>
                    <input type="submit" value="Editar">
                </form>
                <form action="removerProduto" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= produto.getId() %>">
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
    <a href="adicionarProduto">Adicionar Novo Produto</a>
    <nav>
        <a href="listarClientes">Ir para Listar Clientes</a> |
        <a href="listarCompras">Ir para Listar Compras</a>
    </nav>
</body>
</html>