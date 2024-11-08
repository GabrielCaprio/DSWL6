
CREATE DATABASE IF NOT EXISTS LanchoneteDB;
USE LanchoneteDB;


CREATE TABLE Produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    descricao TEXT,
    categoria VARCHAR(50)
);


CREATE TABLE Ingredientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    quantidade_estoque DECIMAL(10, 2) NOT NULL,
    unidade VARCHAR(20) NOT NULL
);

CREATE TABLE Pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL DEFAULT 'Em preparo',
    total DECIMAL(10, 2) DEFAULT 0.00
);

CREATE TABLE Itens_Pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pedido_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES Pedidos(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES Produtos(id) ON DELETE CASCADE
);

INSERT INTO Produtos (nome, preco, descricao, categoria) VALUES 
('Hambúrguer', 12.50, 'Hambúrguer clássico com carne bovina', 'Lanche'),
('Coca-Cola', 5.00, 'Refrigerante 350ml', 'Bebida'),
('Batata Frita', 8.00, 'Porção de batata frita', 'Acompanhamento');


INSERT INTO Ingredientes (nome, quantidade_estoque, unidade) VALUES 
('Carne', 1000, 'gramas'),
('Pão', 50, 'unidades'),
('Alface', 500, 'gramas'),
('Tomate', 300, 'gramas'),
('Batata', 1000, 'gramas');



INSERT INTO Pedidos (status, total) VALUES 
('Em preparo', 25.50);

-- Itens do Pedido para o pedido de exemplo
INSERT INTO Itens_Pedido (pedido_id, produto_id, quantidade, preco) VALUES 
(1, 1, 1, 12.50), -- 1 Hambúrguer no pedido
(1, 2, 2, 5.00),  -- 2 Coca-Colas no pedido
(1, 3, 1, 8.00);  -- 1 Porção de Batata Frita no pedido
