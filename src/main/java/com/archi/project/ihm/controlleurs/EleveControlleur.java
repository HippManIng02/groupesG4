package com.archi.project.ihm.controlleurs;

import com.archi.project.metier.models.Eleve;
import com.archi.project.interfaces.EleveInterface;
import com.archi.project.metier.services.EleveService;

import java.util.List;


public class EleveControlleur {
	private final EleveInterface eleveInterface;

    public EleveControlleur() {
        this.eleveInterface = new EleveService();
    }

    public List<Eleve> getAllEleves() {
        return eleveInterface.eleves();
    }

    public boolean addEleve(String nom, String prenom) {
        return eleveInterface.createEleve(nom, prenom);
    }

    public boolean deleteEleve(int id) {
        return eleveInterface.deleteEleve(id);
    }
}
