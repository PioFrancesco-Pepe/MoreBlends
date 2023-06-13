package model;

import java.io.Serializable;

public class Pagamento implements Serializable {

	private static final long serialVersionUID = 6657848812293766096L;
	
	private int idPagamento;
	private String dataPagamento;
	private float totaleOrdine;
	private int idMetodoPagamento;
	private int idOrdine;
	
	public Pagamento()
	{
		idPagamento=-1;
		dataPagamento="1970-01-01";
		totaleOrdine=0;
		idMetodoPagamento=-1;
		idOrdine=-1;
	}

	public int getIdPagamento() {
		return idPagamento;
	}

	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public float getTotaleOrdine() {
		return totaleOrdine;
	}

	public void setTotaleOrdine(float totaleOrdine) {
		this.totaleOrdine = totaleOrdine;
	}

	public int getIdMetodoPagamento() {
		return idMetodoPagamento;
	}

	public void setIdMetodoPagamento(int idMetodoPagamento) {
		this.idMetodoPagamento = idMetodoPagamento;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
}
