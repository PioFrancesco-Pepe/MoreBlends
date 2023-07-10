package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente implements Serializable{

	private static final long serialVersionUID = -9088569323984415589L;
	
	private int id;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private int isAdmin;
	private List<Telefono> numeriTelefono;
	private List<Indirizzo> indirizzi;
	
	public Cliente() {
		super();
		this.id = -1;
		this.nome = "";
		this.cognome = "";
		this.email = "";
		this.password = "";
		this.isAdmin = 0;
		this.numeriTelefono= new ArrayList<>();
		this.indirizzi=new ArrayList<>();
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int isAdmin() {
		return isAdmin;
	}

	public void setAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<Indirizzo> getIndirizzi() {
		return indirizzi;
	}

	public void setIndirizzi(List<Indirizzo> indirizzi) {
		this.indirizzi = indirizzi;
	}

	public List<Telefono> getNumeriTelefono() {
		return numeriTelefono;
	}
	
	public void setNumeriTelefono(List<Telefono> numeriTelefono) {
		this.numeriTelefono = numeriTelefono;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", password="
				+ password + ", isAdmin=" + isAdmin + ", numeriTelefono=" + numeriTelefono + ", indirizzi=" + indirizzi
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, email, id, indirizzi, isAdmin, nome, numeriTelefono, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(indirizzi, other.indirizzi) && isAdmin == other.isAdmin
				&& Objects.equals(nome, other.nome) && Objects.equals(numeriTelefono, other.numeriTelefono)
				&& Objects.equals(password, other.password);
	}
	
}
