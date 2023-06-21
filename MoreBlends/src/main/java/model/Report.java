package model;

import java.io.Serializable;

public class Report implements Serializable {

	private static final long serialVersionUID = 5108022663529302850L;
	
	private int prodottiVenduti;
	private float costo;
	private float guadagno;
	private float ricavo;
	
	public Report()
	{
		prodottiVenduti=0;
		costo=0;
		guadagno=0;
		ricavo=0;
	}

	public int getProdottiVenduti() {
		return prodottiVenduti;
	}

	public void setProdottiVenduti(int prodottiVenduti) {
		this.prodottiVenduti = prodottiVenduti;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public float getGuadagno() {
		return guadagno;
	}

	public void setGuadagno(float guadagno) {
		this.guadagno = guadagno;
	}

	public float getRicavo() {
		return ricavo;
	}

	public void setRicavo(float ricavo) {
		this.ricavo = ricavo;
	}
}
