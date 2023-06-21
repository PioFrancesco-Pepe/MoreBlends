package model;

public class Ordine {
	
	private int idOrdine;
	private String dataInserimento;
	private int idCliente;
	private int idStatusOrdine;
	private String statusOrdine;
	
	public Ordine()
	{
		idOrdine=-1;
		dataInserimento="";
		setIdCliente(-1);
		idStatusOrdine=1;
	}
	
	public int getIdOrdine() {
		return idOrdine;
	}
	
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	
	public String getDataInserimento() {
		return dataInserimento;
	}
	
	public void setDataInserimento(String dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdStatusOrdine() {
		return idStatusOrdine;
	}
	
	public void setIdStatusOrdine(int idStatusOrdine) {
		this.idStatusOrdine = idStatusOrdine;
	}

	public String getStatusOrdine() {
		return statusOrdine;
	}

	public void setStatusOrdine(String statusOrdine) {
		this.statusOrdine = statusOrdine;
	}
}
