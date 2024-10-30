package com.archi.project.metier.models;


public class Eleve extends Personne{

	public Eleve(int id, String nom, String prenom) {
		super(id, nom, prenom);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}
	
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return super.getNom();
	}
	
	@Override
	public void setNom(String nom) {
		// TODO Auto-generated method stub
		super.setNom(nom);
	}
	
	@Override
	public String getPrenom() {
		// TODO Auto-generated method stub
		return super.getPrenom();
	}
	
	@Override
	public void setPrenom(String prenom) {
		// TODO Auto-generated method stub
		super.setPrenom(prenom);
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}