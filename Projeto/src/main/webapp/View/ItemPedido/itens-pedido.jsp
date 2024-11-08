<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<%@ page import="model.dto.ProdutoDTO" %>
<%@ page import="model.dto.ItemPedidoDTO" %>
<%@ page import="model.dao.ProdutoDAO" %>
<%@ page import="java.util.List" %>
<head>
    <meta charset="UTF-8">
    <title>Itens do Pedido</title>
</head>
<body>
    <h1>Itens do Pedido</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Produto</th>
                <th>Quantidade</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
             <%
                List<ItemPedidoDTO> itensPedido = (List<ItemPedidoDTO>) request.getAttribute("itensPedido");
             if (itensPedido != null && !itensPedido.isEmpty()) {
                 // Itera sobre os itens do pedido
                 for (ItemPedidoDTO item : itensPedido) {
                     ProdutoDTO produto = item.getProduto();  // Produto já foi setado no servlet
         %>
                <tr>
                    <td><%= produto.getNome() %></td>
                    <td><%= item.getQuantidade() %></td>
                    <td><%= item.getPreco() * item.getQuantidade() %></td>
                </tr>
            <% 
                    }
                } else {
            %>
                <tr><td colspan="3">Nenhum item encontrado para este pedido.</td></tr>
            <% 
                }
            %>
        </tbody>
    </table>
    <a href="ItemPedidoServlet?action=edit&pedidoId=<%= request.getParameter("pedidoId") %>">Voltar</a>
</body>
</html>