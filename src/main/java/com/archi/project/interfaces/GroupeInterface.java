package com.archi.project.interfaces;

import java.util.ArrayList;

import com.archi.project.metier.models.Eleve;
import com.archi.project.metier.models.Sujet;
import com.archi.project.metier.models.UniteEnseignement;

public interface GroupeInterface{
	
	void createGroupe(String identifiant, UniteEnseignement ue, ArrayList<Eleve> eleves, Sujet sujet);
	boolean deleteGroupe(String identifiant);
	void createGroupesAleatoires(UniteEnseignement ue, ArrayList<Eleve> eleves, ArrayList<Sujet> sujets);
	boolean changerGroupeEleve(Eleve eleve, String nouvelIdentifiant, Sujet sujet, UniteEnseignement ue);
}