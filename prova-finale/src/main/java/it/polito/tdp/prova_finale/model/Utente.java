package it.polito.tdp.prova_finale.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utente {
	
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private double budget;
	private List<Corso> corsi;
	
	

	public Utente(String nome, String cognome, String username, String password, double budget) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.budget = budget;
		this.corsi = new ArrayList<Corso>();
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public double getBudget() {
		return budget;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	public List<Corso> getCorsi() {
		return corsi;
	}

	public void setCorsi(List<Corso> corsi) {
		this.corsi = corsi;
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", password=" + password
				+ ", budget=" + budget + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(budget, cognome, nome, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return Double.doubleToLongBits(budget) == Double.doubleToLongBits(other.budget)
				&& Objects.equals(cognome, other.cognome) && Objects.equals(nome, other.nome)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	
	
	
	
	
}
