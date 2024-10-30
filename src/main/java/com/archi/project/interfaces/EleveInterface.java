package com.archi.project.interfaces;

import java.util.ArrayList;

import com.archi.project.metier.models.Eleve;

public interface EleveInterface{
	boolean createEleve(String nom, String prenom);
	boolean deleteEleve(int id);
	ArrayList<Eleve> eleves();
}