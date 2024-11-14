<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="model.dto.IngredienteDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Ingredientes</title>
</head>
<body>
 <%@ include file="/css/header.jsp" %>
    <h1>Ingredientes</h1>
    
    
    <a href="IngredienteServlet?action=create">Novo Ingrediente</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Quantidade</th>
                <th>Unidade</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
          <%
                List<IngredienteDTO> listaIngredientes = (List<IngredienteDTO>) request.getAttribute("listaIngredientes");
                if (listaIngredientes != null && !listaIngredientes.isEmpty()) {
                    for (IngredienteDTO ingrediente : listaIngredientes) {
            %>
                <tr>
                    <td><%= ingrediente.getId() %></td>
                    <td><%= ingrediente.getNome() %></td>
                    <td><%= ingrediente.getQuantidadeEstoque() %></td>
                    <td><%= ingrediente.getUnidade() %></td>
                    <td>
                        <a href="IngredienteServlet?action=edit&id=<%= ingrediente.getId() %>">Editar</a> |
                        <a href="IngredienteServlet?action=delete&id=<%= ingrediente.getId() %>" onclick="return confirm('Confirma a exclusão?')">Excluir</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="5">Nenhum ingrediente encontrado.</td></tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>