package model;

import java.io.Serializable;
import java.util.Objects;

public class Indirizzo implements Serializable {

	private static final long serialVersionUID = 3138441055744591077L;
	
	private int idIndirizzo;
	private String via;
	private String civico;
	private String cap;
	private String localita;
	private String siglaProvincia;
	private int idCliente;
	
	public Indirizzo() {
		this.idIndirizzo = -1;
		this.via ="";
		this.civico ="";
		this.cap="";
		this.localita = "";
		this.siglaProvincia = "";
		this.idCliente = -1;
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
		return cap;
	}

	public void setCAP(String cAP) {
		cap = cAP;
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
		return "Indirizzo [idIndirizzo=" + idIndirizzo + ", via=" + via + ", civico=" + civico + ", CAP=" + cap
				+ ", localita=" + localita + ", siglaProvincia=" + siglaProvincia + ", idCliente=" + idCliente + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cap, civico, idCliente, idIndirizzo, localita, siglaProvincia, via);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Indirizzo other = (Indirizzo) obj;
		return Objects.equals(cap, other.cap) && Objects.equals(civico, other.civico) && idCliente == other.idCliente
				&& idIndirizzo == other.idIndirizzo && Objects.equals(localita, other.localita)
				&& Objects.equals(siglaProvincia, other.siglaProvincia) && Objects.equals(via, other.via);
	}
	
	
}
