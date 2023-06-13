package model;

import java.io.Serializable;

public class Spedizione implements Serializable {

	private static final long serialVersionUID = 2507928480269955335L;

	private int idSpedizione;
	private String dataSpedizione;
	private int idMetodoSpedizione;
	private int idOrdine;
	
	public Spedizione()
	{
		idSpedizione=-1;
		dataSpedizione="1970-01-01";
		idMetodoSpedizione=-1;
		idOrdine=-1;
	}

	public int getIdSpedizione() {
		return idSpedizione;
	}

	public void setIdSpedizione(int idSpedizione) {
		this.idSpedizione = idSpedizione;
	}

	public String getDataSpedizione() {
		return dataSpedizione;
	}

	public void setDataSpedizione(String dataSpedizione) {
		this.dataSpedizione = dataSpedizione;
	}

	public int getIdMetodoSpedizione() {
		return idMetodoSpedizione;
	}

	public void setIdMetodoSpedizione(int idMetodoSpedizione) {
		this.idMetodoSpedizione = idMetodoSpedizione;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
}
