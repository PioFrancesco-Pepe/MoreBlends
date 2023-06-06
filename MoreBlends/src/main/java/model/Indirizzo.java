package model;

public class Indirizzo {

	private int idIndirizzo;
	private String via;
	private String civico;
	private String CAP;
	private String localita;
	private String siglaProvincia;
	private int idCliente;
	
	public Indirizzo() {
		this.idIndirizzo =-1;
		this.via ="";
		this.civico ="";
		this.CAP="";
		this.localita ="";
		this.siglaProvincia ="";
		this.idCliente=-1;
	}

	public int getIdIndirizzo() {
		return idIndirizzo;
	}

	public void setIdIndirizzo(int idIndirizzo) {
		this.idIndirizzo = idIndirizzo;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getCAP() {
		return CAP;
	}

	public void setCAP(String cAP) {
		CAP = cAP;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Indirizzo [idIndirizzo=" + idIndirizzo + ", via=" + via + ", civico=" + civico + ", CAP=" + CAP
				+ ", localita=" + localita + ", siglaProvincia=" + siglaProvincia + ", idCliente=" + idCliente + "]";
	}
}
