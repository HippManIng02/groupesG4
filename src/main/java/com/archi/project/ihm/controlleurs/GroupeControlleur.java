package com.archi.project.ihm.controlleurs;

import com.archi.project.metier.models.Eleve;
import com.archi.project.metier.models.Groupe;
import com.archi.project.metier.models.Sujet;
import com.archi.project.metier.models.UniteEnseignement;
import com.archi.project.interfaces.*;
import com.archi.project.metier.services.*;

import java.util.ArrayList;
import java.util.Collections;

public class GroupeControlleur {
	private final GroupeInterface groupeService;
    private final UniteEnseignementInterface ueService;
    private final EleveInterface eleveService;
    private final SujetInterface sujetService;

    public GroupeControlleur() {
        this.groupeService = new GroupeService();
        this.ueService = new UniteEnseignementService();
        this.eleveService = new EleveService();
        this.sujetService = new SujetService();
    }

    public ArrayList<Groupe> getAllGroupes() {
        return groupeService.listGroupes();
    }

    public ArrayList<UniteEnseignement> getAllUEs() {
        return ueService.listUEs();
    }

    public ArrayList<Eleve> getAllEleves() {
        return eleveService.eleves();
    }

    public ArrayList<Sujet> getAllSujets() {
        return sujetService.listSujets();
    }

    public boolean addGroupe(String identifiant, UniteEnseignement ue, ArrayList<Eleve> eleves, Sujet sujet) {
        return groupeService.createGroupe(identifiant, ue, eleves, sujet);
    }
    
    public boolean changeGroupe(Eleve eleve, String identifiant, Sujet sujet, UniteEnseignement ue) {
    	return groupeService.changerGroupeEleve(eleve, identifiant, sujet, ue);
    }
    
    public void creerGroupeAleatoire(int nbr, int nbg) {
    	
    	int nbEleves= nbr * nbg;
    	
    	ArrayList<Eleve> eleves= this.getAllEleves();
    	
    	Collections.shuffle(eleves);
    	ArrayList<Eleve> sublist= new ArrayList<Eleve>();
    	
    	for(int i=0; i<nbEleves;i++) {
    		sublist.add(eleves.get(i));
    	}
    	
    	
    	groupeService.createGroupesAleatoires(this.getAllUEs(), sublist, this.getAllSujets(), nbr);
    }

    public boolean deleteGroupe(String  identifiant) {
        return groupeService.deleteGroupe(identifiant);
    }
}
