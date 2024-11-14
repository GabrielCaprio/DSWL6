package model.dto;

public class ItemPedidoDTO {
    private int id;
    private int pedidoId;
    private int produtoId;
    private int quantidade;
    private double preco;
    
    private ProdutoDTO produto; // Produto associado ao ItemPedido

    // Construtores, Getters e Setters
    public ItemPedidoDTO() {}

    public ItemPedidoDTO(int id, int pedidoId, int produtoId, int quantidade, double preco) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    
    public ItemPedidoDTO(int pedidoId, int produtoId, int quantidade, double preco) {
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPedidoId() { return pedidoId; }
    public void setPedidoId(int pedidoId) { this.pedidoId = pedidoId; }

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    
    public ProdutoDTO getProduto() { return produto; } 
    public void setProduto(ProdutoDTO produto) { this.produto = produto; }

    public double getSubtotal() {
        return this.preco * this.quantidade; // Calculando o subtotal do item
    }
}