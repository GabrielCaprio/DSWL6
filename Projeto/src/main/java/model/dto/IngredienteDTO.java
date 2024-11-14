package model.dto;

public class IngredienteDTO {
    private int id;
    private String nome;
    private double quantidadeEstoque;
    private String unidade;

    // Construtores, Getters e Setters
    public IngredienteDTO() {}

    public IngredienteDTO(int id, String nome, double quantidadeEstoque, String unidade) {
        this.id = id;
        this.nome = nome;
        this.quantidadeEstoque = quantidadeEstoque;
        this.unidade = unidade;
    }

    public IngredienteDTO(String nome2, double quantidade, String unidade2) {
		// TODO Auto-generated constructor stub
    	 
         this.nome = nome2;
         this.quantidadeEstoque = quantidade;
         this.unidade = unidade2;
	}

	

	public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(double quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    public String getUnidade() { return unidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }
}