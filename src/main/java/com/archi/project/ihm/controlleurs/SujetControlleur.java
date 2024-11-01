package com.archi.project.ihm.controlleurs;

import com.archi.project.metier.models.Sujet;
import com.archi.project.interfaces.SujetInterface;
import com.archi.project.metier.services.SujetService;

import java.util.List;

public class SujetControlleur {
	
	  private final SujetInterface sujetInterface;

	    public SujetControlleur() {
	        this.sujetInterface = new SujetService();
	    }

	    public List<Sujet> getAllSujets() {
	        return sujetInterface.listSujets();
	    }

	    public boolean addSujet(String intitule) {
	        return sujetInterface.createSujet(intitule);
	    }

	    public boolean deleteSujet(int id) {
	        return sujetInterface.deleteSujet(id);
	    }
}
