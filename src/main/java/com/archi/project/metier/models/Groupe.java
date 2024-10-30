package com.archi.project.metier.models;

import java.util.ArrayList;

public class Groupe{
	private int id;
	private String identifiant;
	private Sujet sujet;
	private UniteEnseignement ue;
	private ArrayList<Eleve> eleves;
	
	public Groupe() {
	}
	
	public Groupe(int id, String identifiant, Sujet sujet, UniteEnseignement ue, ArrayList<Eleve> eleves) {
		this.setId(id);
		this.setIdentifiant(identifiant);
		this.setSujet(sujet);
		this.setUe(ue);
		this.setEleves(eleves);
	}
	
	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	public String getIdentifiant() {
		return identifiant;
	}
	
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	
	public Sujet getSujet() {
		return sujet;
	}
	
	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}
	
	public UniteEnseignement getUe() {
		return ue;
	}
	
	public void setUe(UniteEnseignement ue) {
		this.ue = ue;
	}
	
	public ArrayList<Eleve> getEleves() {
		return eleves;
	}
	
	public void setEleves(ArrayList<Eleve> eleves) {
		this.eleves = eleves;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "Groupe{ Id = " + this.getId() + "; identifiant = " + this.getIdentifiant() + "; sujet = " + this.getSujet().getIntitule() + "; Ue = "+this.getUe().getCode()+"; Eleves = "+this.getEleves()+"}";
	}
}