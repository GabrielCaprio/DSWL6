<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="model.dto.IngredienteDTO" %>
<html>
<head>
<meta charset="UTF-8">
<title>
    <%= (request.getAttribute("acao") != null && request.getAttribute("acao").equals("editar")) ? "Editar Ingrediente" : "Novo Ingrediente" %>
</title>
</head>
<body>
 <%@ include file="/css/header.jsp" %>
    <h1>
        <%= (request.getAttribute("acao") != null && request.getAttribute("acao").equals("editar")) ? "Editar Ingrediente" : "Novo Ingrediente" %>
    </h1>
    
    <form action="IngredienteServlet" method="post">
        <input type="hidden" name="action" value="<%= (request.getAttribute("acao") != null && request.getAttribute("acao").equals("editar")) ? "edit" : "create" %>"/>
        
        <% if ("editar".equals(request.getAttribute("acao"))) { %>
            <input type="hidden" name="id" value="<%= ((IngredienteDTO) request.getAttribute("ingrediente")).getId() %>"/>
        <% } %>
        
        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" value="<%= (request.getAttribute("ingrediente") != null ? ((IngredienteDTO) request.getAttribute("ingrediente")).getNome() : "") %>" required/><br>

        <label for="quantidade">Quantidade:</label>
        <input type="number" step="0.01" name="quantidade" id="quantidade" value="<%= (request.getAttribute("ingrediente") != null ? ((IngredienteDTO) request.getAttribute("ingrediente")).getQuantidadeEstoque() : "") %>" required/><br>

        <label for="unidade">Unidade:</label>
        <input type="text" name="unidade" id="unidade" value="<%= (request.getAttribute("ingrediente") != null ? ((IngredienteDTO) request.getAttribute("ingrediente")).getUnidade() : "") %>" required/><br>

        <button type="submit"><%= ("editar".equals(request.getAttribute("acao"))) ? "Atualizar Ingrediente" : "Criar Ingrediente" %></button>
    </form>
			<% 
			    String mensagem = (String) request.getAttribute("mensagem");
			    String tipoMensagem = (String) request.getAttribute("tipoMensagem");
			%>
			<% if (mensagem != null) { %>
			    <div class="<%= "sucesso".equals(tipoMensagem) ? "mensagem-sucesso" : "mensagem-erro" %>">
			        <%= mensagem %>
			    </div>
			<% } %>
</body>
</html>