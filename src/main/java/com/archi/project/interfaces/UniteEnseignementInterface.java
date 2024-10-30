package com.archi.project.interfaces;

import com.archi.project.metier.models.UniteEnseignement;
import java.util.List;

public interface UniteEnseignementInterface {
    void createUE(String code, String designation);
    void deleteUE(int id);
    List<UniteEnseignement> listUEs();
}

