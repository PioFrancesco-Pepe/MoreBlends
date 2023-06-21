package model;

import java.io.Serializable;

public class SottoCategoria implements Serializable {
	
	private static final long serialVersionUID = -5624880248225004457L;

	private int idSottoCategoria;
	private String nomeSottoCategoria;
	
	public SottoCategoria()
	{
		idSottoCategoria=-1;
		nomeSottoCategoria="";
	}

	public int getIdSottoCategoria() {
		return idSottoCategoria;
	}

	public void setIdSottoCategoria(int idSottoCategoria) {
		this.idSottoCategoria = idSottoCategoria;
	}

	public String getNomeSottoCategoria() {
		return nomeSottoCategoria;
	}

	public void setNomeSottoCategoria(String nomeSottoCategoria) {
		this.nomeSottoCategoria = nomeSottoCategoria;
	}

	@Override
	public String toString() {
		return "SottoCategoria [idSottoCategoria=" + idSottoCategoria + ", nomeSottoCategoria=" + nomeSottoCategoria
				+ "]";
	}
}
