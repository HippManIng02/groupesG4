package com.archi.project.ihm.controlleurs;

import com.archi.project.metier.models.UniteEnseignement;
import com.archi.project.interfaces.UniteEnseignementInterface;
import com.archi.project.metier.services.UniteEnseignementService;

import java.util.List;

public class UeControlleur {
	 private final UniteEnseignementInterface ueInterface;

	    public UeControlleur() {
	        this.ueInterface = new UniteEnseignementService();
	    }

	    public List<UniteEnseignement> getAllUEs() {
	        return ueInterface.listUEs();
	    }

	    public boolean addUE(String code, String designation) {
	        return ueInterface.createUE(code, designation);
	    }

	    public boolean deleteUE(int id) {
	        return ueInterface.deleteUE(id);
	    }
}
