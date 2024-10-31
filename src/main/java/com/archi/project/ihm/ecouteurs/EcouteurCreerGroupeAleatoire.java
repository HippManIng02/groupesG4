package com.archi.project.ihm.ecouteurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.archi.project.ihm.vues.GestionGroupeApp;
import com.archi.project.metier.models.UniteEnseignement;
import com.archi.project.metier.models.Eleve;
import com.archi.project.metier.models.Sujet;

public class EcouteurCreerGroupeAleatoire implements ActionListener {
    private GestionGroupeApp app;

    public EcouteurCreerGroupeAleatoire(GestionGroupeApp app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    	String input = JOptionPane.showInputDialog(app, "Entrez le nombre de personnes par groupe :", "Créer des Groupes Aléatoires", JOptionPane.QUESTION_MESSAGE);
        

    	if (input != null && !input.isEmpty()) {
            try {
                int nbrePersonneParGroupe = Integer.parseInt(input);

                ArrayList<UniteEnseignement> ueList = app.getUeInterface().listUEs();
                ArrayList<Eleve> eleveList = app.getEleveInterface().eleves();
                ArrayList<Sujet> sujetList = app.getSujetInterface().listSujets();

                app.getGroupeInterface().createGroupesAleatoires(ueList, eleveList, sujetList, nbrePersonneParGroupe);


                app.loadEntities();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(app, "Veuillez entrer un nombre valide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
