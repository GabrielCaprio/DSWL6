package models;

import java.util.ArrayList;

public class Cliente {
    private static int contador = 0; 
    private int id;
    private String nome;
    private ArrayList<Produto> produtosComprados;

    public Cliente(String nome) {
        this.id = ++contador; 
        this.nome = nome;
        this.produtosComprados = new ArrayList<>();
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

    public ArrayList<Produto> getProdutosComprados() {
        return produtosComprados;
    }

    public void setProdutosComprados(ArrayList<Produto> produtosComprados) {
        this.produtosComprados = produtosComprados;
    }
}
