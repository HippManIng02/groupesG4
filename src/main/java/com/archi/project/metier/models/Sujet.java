package com.archi.project.metier.models;


public class Sujet{
	private int id;
	private String intitule;
	
	public Sujet(int id, String intitule) {
		this.setId(id);
		this.setIntitule(intitule);
	}
	
	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	public String getIntitule() {
		return intitule;
	}
	
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Sujet { Id = " + this.getId() + " intitule = " + this.getIntitule() + "}";
	}
	
}