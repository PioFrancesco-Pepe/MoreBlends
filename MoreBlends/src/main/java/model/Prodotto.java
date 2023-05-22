package model;

public class Prodotto {

	private int id;
	private String nome;
	private float prezzo;
	
	public Prodotto() {
		super();
		this.id = -1;
		this.nome = "";
		this.prezzo = 0;
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

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	
}
