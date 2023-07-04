package model;

import java.io.Serializable;

public class StoricoPrezzi implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idStoricoPrezzi;
	private float costo;
	private float pv;
	private String dataInizio;
	private String dataFine;
	private int idProdotto;
	
	public StoricoPrezzi() {
		idStoricoPrezzi=-1;
		costo=0;
		pv=0;
		dataInizio="";
		dataFine=null;
		idProdotto=-1;
	}

	public int getIdStoricoPrezzi() {
		return idStoricoPrezzi;
	}

	public void setIdStoricoPrezzi(int idStoricoPrezzi) {
		this.idStoricoPrezzi = idStoricoPrezzi;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public float getPv() {
		return pv;
	}

	public void setPv(float pv) {
		this.pv = pv;
	}

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDataFine() {
		return dataFine;
	}

	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
}
