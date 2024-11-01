package com.archi.project.ihm.ecouteurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import com.archi.project.ihm.controlleurs.GroupeControlleur;
import com.archi.project.ihm.vues.GestionGroupeApp;


public class EcouteurCreerGroupeAleatoire implements ActionListener {
    private GestionGroupeApp app;
    private GroupeControlleur groupeControlleur;

    public EcouteurCreerGroupeAleatoire(GestionGroupeApp app, GroupeControlleur gpc) {
        this.app = app;
        this.groupeControlleur = gpc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    	String input = JOptionPane.showInputDialog(app, "Entrez le nombre de personnes par groupe :", "Créer des Groupes Aléatoires", JOptionPane.QUESTION_MESSAGE);
        

    	if (input != null && !input.isEmpty()) {
            try {
                int nbrePersonneParGroupe = Integer.parseInt(input);


                groupeControlleur.creerGroupeAleatoire(nbrePersonneParGroupe);


                app.loadEntities();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(app, "Veuillez entrer un nombre valide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
