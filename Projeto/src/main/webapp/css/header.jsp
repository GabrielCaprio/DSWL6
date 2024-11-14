<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lanchonete - Sistema de Gerenciamento</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h1>Lanchonete - Sistema de Gerenciamento</h1>
    </header>
    <nav>
        <a href="MenuServlet">Cardápio</a>
        <a href="ProdutoServlet">Produtos</a>
        <a href="IngredienteServlet">Ingredientes</a>
        <a href="PedidoServlet">Pedidos</a>
    </nav>
</body>
</html>