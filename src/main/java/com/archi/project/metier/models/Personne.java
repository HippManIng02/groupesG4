package com.archi.project.metier.models;

public class Personne {
	protected int id;
	protected String nom;
	protected String prenom;
	
	
	public Personne(int id, String nom, String prenom) {
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
	}
	
	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Personne{ Id = " + this.getId() + " nom = " + this.getNom() + "prenom = " + this.getPrenom() + "}";

	}
	
	

}