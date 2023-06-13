package model;

public class Composizione {

	private int idOrdine;
	private int idProdotto;
	private int quantita;
	
	public Composizione()
	{
		idOrdine=-1;
		setIdProdotto(-1);
		quantita=-1;
	}
	
	public int getIdOrdine() {
		return idOrdine;
	}
	
	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}
	
	public int getIdProdotto() {
		return idProdotto;
	}

	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}

	public int getQuantita() {
		return quantita;
	}
	
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	
}
