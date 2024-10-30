package com.archi.project.interfaces;

import java.util.ArrayList;

import com.archi.project.metier.models.Sujet;

public interface SujetInterface{
	
	boolean createSujet(String intitule);
	boolean deleteSujet(int id);
	ArrayList<Sujet> listSujets();
	
}