<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List" %>
<%@ page import="model.dto.PedidoDTO, model.dto.ItemPedidoDTO, model.dto.ProdutoDTO" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= (request.getAttribute("acao") != null && request.getAttribute("acao").equals("editar")) ? "Editar Pedido" : "Novo Pedido" %></title>
</head>
<body>
    <h1>
        <%= (request.getAttribute("acao") != null && request.getAttribute("acao").equals("editar")) ? "Editar Pedido" : "Novo Pedido" %>
    </h1>

    <form action="PedidoServlet" method="post">
        <!-- Ação: create ou edit -->
        <input type="hidden" name="action" value="<%= (request.getAttribute("acao") != null && request.getAttribute("acao").equals("editar")) ? "edit" : "create" %>"/>

        <% if ("editar".equals(request.getAttribute("acao"))) { %>
            <input type="hidden" name="id" value="<%= ((PedidoDTO) request.getAttribute("pedido")).getId() %>"/>
        <% } %>

        <!-- Campos do pedido -->
        <label for="status">Status:</label>
        <input type="text" name="status" id="status" value="<%= (request.getAttribute("pedido") != null ? ((PedidoDTO) request.getAttribute("pedido")).getStatus() : "") %>" required/><br>

        <label for="total">Total:</label>
        <input type="number" step="0.01" name="total" id="total" value="<%= (request.getAttribute("pedido") != null ? ((PedidoDTO) request.getAttribute("pedido")).getTotal() : "") %>" required/><br>

        <h3>Itens do Pedido:</h3>

        <% 
            // Se a ação for editar, recupere os itens já existentes
            List<ItemPedidoDTO> itensPedido = (List<ItemPedidoDTO>) request.getAttribute("itensPedido");
            List<ProdutoDTO> listaProdutos = (List<ProdutoDTO>) request.getAttribute("listaProdutos");
            
            if ("editar".equals(request.getAttribute("acao"))) {
                if (itensPedido != null && !itensPedido.isEmpty()) {
                    for (ItemPedidoDTO item : itensPedido) {
        %>
            <div>
                <span>Produto: <%= item.getProdutoId() %> </span><br>
                <label for="quantidade_<%= item.getProdutoId() %>">Quantidade:</label>
                <input type="number" id="quantidade_<%= item.getProdutoId() %>" name="quantidades[<%= item.getProdutoId() %>]" value="<%= item.getQuantidade() %>" required/><br>
            </div>
        <% 
                    }
                } else {
        %>
            <p>Este pedido não contém itens.</p>
        <% 
                }
            } else {
                // Caso seja um pedido novo, exibe os produtos disponíveis
                if (listaProdutos != null && !listaProdutos.isEmpty()) {
                    for (ProdutoDTO produto : listaProdutos) {
        %>
            <div>
                <span>Produto: <%= produto.getNome() %> </span><br>
                <label for="quantidade_<%= produto.getId() %>">Quantidade:</label>
                <input type="number" id="quantidade_<%= produto.getId() %>" name="quantidades[<%= produto.getId() %>]" value="0" required/><br>
            </div>
        <% 
                    }
                } else {
        %>
            <p>Nenhum produto disponível para seleção.</p>
        <% 
                }
            }
        %>

        <button type="submit"><%= ("editar".equals(request.getAttribute("acao"))) ? "Atualizar Pedido" : "Criar Pedido" %></button>
    </form>

    <a href="PedidoServlet?action=list">Cancelar</a>
</body>
</html>