package com.archi.project.ihm.controlleurs;

import com.archi.project.metier.LogiqueMetier;
import com.archi.project.metier.models.Eleve;
import com.archi.project.metier.models.Groupe;
import com.archi.project.metier.models.Sujet;
import com.archi.project.metier.models.UniteEnseignement;

import java.util.ArrayList;
import java.util.Collections;

public class GroupeControlleur {

    private final LogiqueMetier logiqueMetier;

    public GroupeControlleur(LogiqueMetier logiqueMetier) {
    	this.logiqueMetier = logiqueMetier;
    }

    public ArrayList<Groupe> getAllGroupes() {
        return this.logiqueMetier.getGroupeService().listGroupes();
    }

    public ArrayList<UniteEnseignement> getAllUEs() {
        return this.logiqueMetier.getUeService().listUEs();
    }

    public ArrayList<Eleve> getAllEleves() {
        return this.logiqueMetier.getEleveService().eleves();
    }

    public ArrayList<Sujet> getAllSujets() {
        return this.logiqueMetier.getSujetService().listSujets();
    }

    public boolean addGroupe(String identifiant, UniteEnseignement ue, ArrayList<Eleve> eleves, Sujet sujet) {
        return this.logiqueMetier.getGroupeService().createGroupe(identifiant, ue, eleves, sujet);
    }
    
    public boolean changeGroupe(Eleve eleve, String identifiant, Sujet sujet, UniteEnseignement ue) {
    	return this.logiqueMetier.getGroupeService().changerGroupeEleve(eleve, identifiant, sujet, ue);
    }
    
    public void creerGroupeAleatoire(int nbr, int nbg) {
    	
    	int nbEleves= nbr * nbg;
    	
    	ArrayList<Eleve> eleves= this.getAllEleves();
    	
    	Collections.shuffle(eleves);
    	ArrayList<Eleve> sublist= new ArrayList<Eleve>();
    	
    	for(int i=0; i<nbEleves;i++) {
    		sublist.add(eleves.get(i));
    	}
    	
    	
    	this.logiqueMetier.getGroupeService().createGroupesAleatoires(this.getAllUEs(), sublist, this.getAllSujets(), nbr);
    }

    public boolean deleteGroupe(String  identifiant) {
        return this.logiqueMetier.getGroupeService().deleteGroupe(identifiant);
    }
}
