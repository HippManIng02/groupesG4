package com.archi.project.ihm.controlleurs;

import com.archi.project.metier.LogiqueMetier;
import com.archi.project.metier.models.UniteEnseignement;

import java.util.List;

public class UeControlleur {
	 private final LogiqueMetier logiqueMetier;

	    public UeControlleur(LogiqueMetier logiqueMetier) {
	        this.logiqueMetier = logiqueMetier;
	    }

	    public List<UniteEnseignement> getAllUEs() {
	        return this.logiqueMetier.getUeService().listUEs();
	    }

	    public boolean addUE(String code, String designation) {
	        return this.logiqueMetier.getUeService().createUE(code, designation);
	    }

	    public boolean deleteUE(int id) {
	        return this.logiqueMetier.getUeService().deleteUE(id);
	    }
}
