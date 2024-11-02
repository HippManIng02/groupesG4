package com.archi.project.ihm.controlleurs;

import com.archi.project.metier.LogiqueMetier;
import com.archi.project.metier.models.Sujet;

import java.util.List;

public class SujetControlleur {
	
	  private final LogiqueMetier logiqueMetier;

	    public SujetControlleur(LogiqueMetier logiqueMetier) {
	        this.logiqueMetier =  logiqueMetier;
	    }

	    public List<Sujet> getAllSujets() {
	        return logiqueMetier.getSujetService().listSujets();
	    }

	    public boolean addSujet(String intitule) {
	        return logiqueMetier.getSujetService().createSujet(intitule);
	    }

	    public boolean deleteSujet(int id) {
	        return logiqueMetier.getSujetService().deleteSujet(id);
	    }
}
