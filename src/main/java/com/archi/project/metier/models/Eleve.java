package com.archi.project.metier.models;


public class Eleve extends Personne{
	
	public Eleve() {
		super();
	}
 
	public Eleve(int id, String nom, String prenom) {
		super(id, nom, prenom);
	}
	
	@Override
	public int getId() {
		return super.getId();
	}
	
	@Override
	public String getNom() {
		return super.getNom();
	}
	
	@Override
	public void setNom(String nom) {
		super.setNom(nom);
	}
	
	@Override
	public String getPrenom() {
		return super.getPrenom();
	}
	
	@Override
	public void setPrenom(String prenom) {
		super.setPrenom(prenom);
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}