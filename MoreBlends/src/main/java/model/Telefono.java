package model;

import java.io.Serializable;

public class Telefono implements Serializable {

	private static final long serialVersionUID = 7630526836958429054L;
	
	private int idTelefono;
	private String numTelefono;
	private int idCliente;
	
	public Telefono()
	{
		idCliente=-1;
		numTelefono="";
		idCliente=-1;
	}

	public int getIdTelefono() {
		return idTelefono;
	}

	public void setIdTelefono(int idTelefono) {
		this.idTelefono = idTelefono;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Telefono [idTelefono=" + idTelefono + ", numTelefono=" + numTelefono + ", idCliente=" + idCliente + "]";
	}
	
}
