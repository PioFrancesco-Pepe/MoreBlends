package model;

import java.io.Serializable;

public class StoricoPrezzi implements Serializable {

	private static final long serialVersionUID = 1L;

	private int IdStoricoPrezzi;
	private float Costo;
	private float pv;
	private String DataInizio;
	private String DataFine;
	private int IdProdotto;
	
	public StoricoPrezzi() {
		IdStoricoPrezzi=-1;
		Costo=0;
		pv=0;
		DataInizio="";
		DataFine=null;
		IdProdotto=-1;
	}

	public int getIdStoricoPrezzi() {
		return IdStoricoPrezzi;
	}

	public void setIdStoricoPrezzi(int idStoricoPrezzi) {
		IdStoricoPrezzi = idStoricoPrezzi;
	}

	public float getCosto() {
		return Costo;
	}

	public void setCosto(float costo) {
		Costo = costo;
	}

	public float getPv() {
		return pv;
	}

	public void setPv(float pv) {
		this.pv = pv;
	}

	public String getDataInizio() {
		return DataInizio;
	}

	public void setDataInizio(String dataInizio) {
		DataInizio = dataInizio;
	}

	public String getDataFine() {
		return DataFine;
	}

	public void setDataFine(String dataFine) {
		DataFine = dataFine;
	}

	public int getIdProdotto() {
		return IdProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		IdProdotto = idProdotto;
	}
}
