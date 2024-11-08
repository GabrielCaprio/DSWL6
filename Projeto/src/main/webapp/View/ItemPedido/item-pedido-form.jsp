<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="model.dto.ProdutoDTO" %>
<%@ page import="model.dto.ItemPedidoDTO" %>
<%@ page import="java.util.List" %>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Itens do Pedido</title>
</head>
<body>
    <h1>Editar Itens do Pedido</h1>
    <form method="post" action="ItemPedidoServlet?action=update">
        <input type="hidden" name="pedidoId" value="<%= request.getParameter("pedidoId") %>">
        
        <table border="1">
            <thead>
                <tr>
                    <th>Produto</th>
                    <th>Quantidade</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<ItemPedidoDTO> itensPedido = (List<ItemPedidoDTO>) request.getAttribute("itensPedido");
                    List<ProdutoDTO> listaProdutos = (List<ProdutoDTO>) request.getAttribute("listaProdutos");
                    
                    for (ItemPedidoDTO item : itensPedido) {
                        ProdutoDTO produto = null;
                        
                        // Busca o produto correspondente ao item
                        for (ProdutoDTO p : listaProdutos) {
                            if (p.getId() == item.getProdutoId()) {
                                produto = p;
                                break;
                            }
                        }
                %>
                    <tr>
                        <td><%= produto.getNome() %></td>
                        <td>
                            <!-- Cria um formulário para cada item, com seu próprio botão de envio -->
                            <input type="number" name="quantidade_<%= item.getId() %>" value="<%= item.getQuantidade() %>" required>
                            <input type="hidden" name="itemId_<%= item.getId() %>" value="<%= item.getId() %>">
                        </td>
                        <td>
                            <!-- Submete o item atual para atualização -->
                            <button type="submit" name="action" value="updateItem_<%= item.getId() %>">Atualizar Quantidade</button>
                        </td>
                <% } %>
            </tbody>
        </table>
    </form>
    <a href="ItemPedidoServlet?action=listt&pedidoId=<%= request.getParameter("pedidoId") %>">Voltar</a>
</body>
</html>