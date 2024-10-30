package com.archi.project.metier.models;

public class UniteEnseignement {
	private int id;
	private String code;
	private String designation;
	
	public UniteEnseignement() {
	}

	public UniteEnseignement(int id, String code, String designation) {
		this.setId(id);
		this.setCode(code);
		this.setDesignation(designation);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	private void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Ue{ Id = " + this.getId() + "; code = " + this.getCode() + "; Designation = " + this.getDesignation() + "}";
	}

}