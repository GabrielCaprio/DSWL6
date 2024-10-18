<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Adicionar Cliente</h1>
    <form action="adicionarCliente" method="post">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required>
        <input type="submit" value="Adicionar">
    </form>
    
    <br>
    <a href="listarClientes">Voltar para Listar Clientes</a>
</body>
</html>