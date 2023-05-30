package model;

import java.util.Objects;

public class Prodotto {

	private int id;
	private String nome,descrizione,descrizioneAmpia,datainserimento;
	private float prezzoVendita,costo;
	
	public Prodotto() {
		super();
		this.id = -1;
		this.nome = "";
		this.descrizione="";
		this.descrizioneAmpia="";
		this.datainserimento="";
		this.prezzoVendita = 0;
		this.costo=0;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public String getDescrizioneAmpia() {
		return descrizioneAmpia;
	}

	public void setDescrizioneAmpia(String descrizioneAmpia) {
		this.descrizioneAmpia = descrizioneAmpia;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDatainserimento() {
		return datainserimento;
	}

	public void setDatainserimento(String datainserimento) {
		this.datainserimento = datainserimento;
	}

	public float getPrezzoVendita() {
		return prezzoVendita;
	}

	public void setPrezzoVendita(float prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
