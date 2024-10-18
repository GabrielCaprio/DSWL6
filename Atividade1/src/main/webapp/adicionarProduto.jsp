<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Adicionar Produto</title>
</head>
<body>
	 <h1>Adicionar Produto</h1>
    <form action="adicionarProduto" method="post">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required>
        <label for="preco">Preço:</label>
        <input type="number" id="preco" name="preco" step="0.01" required>
        <input type="submit" value="Adicionar">
    </form>
    
    <br>
    <a href="listarProdutos">Voltar para Listar Produtos</a>
</body>
</html>