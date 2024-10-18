<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="models.Cliente" %>
<%@ page import="models.Produto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>Listar Compras</title>
</head>
<body>
	<h1>Listar Compras por Cliente</h1>
    <form action="listarCompras" method="get">
        <label for="clienteId">Selecione um Cliente:</label>
        <select name="clienteId" id="clienteId" required>
            <%
                ArrayList<Cliente> clientes = (ArrayList<Cliente>) request.getSession().getAttribute("clientes");
                if (clientes != null && !clientes.isEmpty()) {
                    for (Cliente cliente : clientes) {
            %>
                        <option value="<%= cliente.getId() %>"><%= cliente.getNome() %></option>
            <%
                    }
                } else {
            %>
                    <option value="">Nenhum cliente disponível</option>
            <%
                }
            %>
        </select>
        <input type="submit" value="Listar Compras">
    </form>

    <%
        Cliente cliente = (Cliente) request.getAttribute("cliente");
        if (cliente != null) {
    %>
    <h2>Compras de <%= cliente.getNome() %></h2>
    <table border="1">
        <tr>
            <th>ID do Produto</th>
            <th>Nome do Produto</th>
            <th>Ações</th>
        </tr>
        <%
            ArrayList<Produto> produtosComprados = cliente.getProdutosComprados();
            for (Produto produto : produtosComprados) {
        %>
        <tr>
            <td><%= produto.getId() %></td>
            <td><%= produto.getNome() %></td>
            <td>
                <form action="removerProdutoCompra" method="post" style="display:inline;">
                    <input type="hidden" name="clienteId" value="<%= cliente.getId() %>">
                    <input type="hidden" name="produtoId" value="<%= produto.getId() %>">
                    <input type="submit" value="Remover">
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        } else {
    %>
    <p>Nenhum cliente selecionado.</p>
    <%
        }
    %>
    
    <br>
    <a href="associarProduto">Adicionar Produto a Cliente</a> 
    <nav>
        <a href="listarClientes">Ir para Listar Clientes</a> |
        <a href="listarProdutos">Ir para Listar Produtos</a>
    </nav>
</body>
</html>