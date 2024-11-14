<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<%@ page import="model.dto.ProdutoDTO" %>
<html>
<head>
<meta charset="UTF-8">
<title><%= (request.getAttribute("acao") != null && request.getAttribute("acao").equals("editar")) ? "Editar Produto" : "Novo Produto" %>
</title>
</head>
<body>
 <%@ include file="/css/header.jsp" %>
    <h1>
        <%= (request.getAttribute("acao") != null && request.getAttribute("acao").equals("editar")) ? "Editar Produto" : "Novo Produto" %>
    </h1>
    
    <form action="ProdutoServlet" method="post">
        <input type="hidden" name="action" value="<%= (request.getAttribute("acao") != null && request.getAttribute("acao").equals("editar")) ? "edit" : "create" %>"/>
        
        <% if ("editar".equals(request.getAttribute("acao"))) { %>
            <input type="hidden" name="id" value="<%= ((ProdutoDTO) request.getAttribute("produto")).getId() %>"/>
        <% } %>
        
        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" value="<%= (request.getAttribute("produto") != null ? ((ProdutoDTO) request.getAttribute("produto")).getNome() : "") %>" required/><br>

        <label for="preco">Preço:</label>
        <input type="number" step="0.01" name="preco" id="preco" value="<%= (request.getAttribute("produto") != null ? ((ProdutoDTO) request.getAttribute("produto")).getPreco() : "") %>" required/><br>

        <label for="descricao">Descrição:</label>
        <input type="text" name="descricao" id="descricao" value="<%= (request.getAttribute("produto") != null ? ((ProdutoDTO) request.getAttribute("produto")).getDescricao() : "") %>" required/><br>

        <label for="categoria">Categoria:</label>
        <input type="text" name="categoria" id="categoria" value="<%= (request.getAttribute("produto") != null ? ((ProdutoDTO) request.getAttribute("produto")).getCategoria() : "") %>" required/><br>

        <button type="submit"><%= ("editar".equals(request.getAttribute("acao"))) ? "Atualizar Produto" : "Criar Produto" %></button>
    </form>
</body>
</html>