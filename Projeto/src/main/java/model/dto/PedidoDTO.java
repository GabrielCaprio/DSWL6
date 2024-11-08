package model.dto;

import java.util.Date;
import java.util.List;

public class PedidoDTO {
    private int id;
    private Date data;
    private String status;
    private double total;
    private List<ItemPedidoDTO> itens;

    // Construtores, Getters e Setters
    public PedidoDTO() {}

    public PedidoDTO(int id, Date data, String status, double total, List<ItemPedidoDTO> itens) {
        this.id = id;
        this.data = data;
        this.status = status;
        this.total = total;
        this.itens = itens;
    }

    public PedidoDTO(double total2, String status2) {
		// TODO Auto-generated constructor stub
    	this.status = status2;
        this.total = total2;
	}

	public PedidoDTO(int idatt, double totalatt, String statusatt) {
		// TODO Auto-generated constructor stub
		this.id = idatt;
		this.status = statusatt;
        this.total = totalatt;
	}

	public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public List<ItemPedidoDTO> getItens() { return itens; }
    public void setItens(List<ItemPedidoDTO> itens) { this.itens = itens; }
}