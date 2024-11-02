package com.archi.project.ihm.controlleurs;

import com.archi.project.metier.LogiqueMetier;
import com.archi.project.metier.models.Eleve;

import java.util.List;


public class EleveControlleur {
	
	private final LogiqueMetier logiqueMetier;

    public EleveControlleur(LogiqueMetier logiqueMetier) {
        this.logiqueMetier = logiqueMetier;
    }

    public List<Eleve> getAllEleves() {
        return logiqueMetier.getEleveService().eleves();
    }

    public boolean addEleve(String nom, String prenom) {
        return logiqueMetier.getEleveService().createEleve(nom, prenom);
    }

    public boolean deleteEleve(int id) {
        return logiqueMetier.getEleveService().deleteEleve(id);
    }
}
