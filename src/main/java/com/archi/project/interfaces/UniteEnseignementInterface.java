package com.archi.project.interfaces;

import com.archi.project.metier.models.UniteEnseignement;

import java.util.ArrayList;

public interface UniteEnseignementInterface {
    boolean createUE(String code, String designation);
    boolean deleteUE(int id);
    ArrayList<UniteEnseignement> listUEs();
}

