package com.archi.project.interfaces;

import java.util.List;

import com.archi.project.metier.models.Sujet;

public interface SujetInterface{
	
	void createSujet(String intitule);
	void deleteSujet(int id);
	List<Sujet> listSujets();
	
}