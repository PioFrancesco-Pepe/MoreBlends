package model;

import java.io.Serializable;

public class Locazione implements Serializable {

	private static final long serialVersionUID = -6591495934661143749L;

	private int idProdotto;
	private int idMagazzino;
	private int quantita;
	
	public Locazione(){
		setIdProdotto(-1);
		setIdMagazzino(-1);
		setQuantita(0);
	}

	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getIdMagazzino() {
		return idMagazzino;
	}

	public void setIdMagazzino(int idMagazzino) {
		this.idMagazzino = idMagazzino;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
}
