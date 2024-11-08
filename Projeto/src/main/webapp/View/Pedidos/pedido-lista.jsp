<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="model.dto.PedidoDTO" %>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Pedidos</title>
</head>
<body>
    <h1>Pedidos</h1>
    <a href="PedidoServlet?action=create">Novo Pedido</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Status</th>
                <th>Total</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<PedidoDTO> listaPedidos = (List<PedidoDTO>) request.getAttribute("listaPedidos");
                if (listaPedidos != null && !listaPedidos.isEmpty()) {
                    for (PedidoDTO pedido : listaPedidos) {
            %>
                <tr>
                    <td><%= pedido.getId() %></td>
                    <td><%= pedido.getStatus() %></td>
                    <td><%= pedido.getTotal() %></td>
                    <td>
                        <a href="PedidoServlet?action=edit&id=<%= pedido.getId() %>">Editar</a> |
                        <a href="PedidoServlet?action=delete&id=<%= pedido.getId() %>" onclick="return confirm('Confirma a exclusão?')">Excluir</a>
                        
                        <a href="ItemPedidoServlet?action=edit&pedidoId=<%= pedido.getId() %>">Editar Itens</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="4">Nenhum pedido encontrado.</td></tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>