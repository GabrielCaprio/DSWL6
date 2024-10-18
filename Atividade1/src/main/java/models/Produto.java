package models;

public class Produto {
    private static int contador = 0;
    private int id;
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.id = ++contador;
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}

