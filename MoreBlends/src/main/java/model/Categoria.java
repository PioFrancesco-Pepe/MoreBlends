package model;

import java.io.Serializable;

public class Categoria implements Serializable {

	private static final long serialVersionUID = 544852142707290648L;

	private int idCategoria;
	private String nomeCategoria;
	private String descCategoria;
	
	public Categoria()
	{
		idCategoria=-1;
		nomeCategoria="";
		setDescCategoria("");
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getDescCategoria() {
		return descCategoria;
	}

	public void setDescCategoria(String descCategoria) {
		this.descCategoria = descCategoria;
	}

	@Override
	public String toString() {
		return "Categoria [idCategoria=" + idCategoria + ", nomeCategoria=" + nomeCategoria + ", descCategoria="
				+ descCategoria + "]";
	}
}
