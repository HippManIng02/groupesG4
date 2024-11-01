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

    	String inputPersonnes = JOptionPane.showInputDialog(app, "Entrez le nombre de personnes par groupe :", "Créer des Groupes Aléatoires", JOptionPane.QUESTION_MESSAGE);

    	if (inputPersonnes != null && !inputPersonnes.isEmpty()) {
    	    try {
    	        int nbrePersonneParGroupe = Integer.parseInt(inputPersonnes);
    	        
    	        String inputGroupes = JOptionPane.showInputDialog(app, "Entrez le nombre de groupes à créer :", "Créer des Groupes Aléatoires", JOptionPane.QUESTION_MESSAGE);
    	        
    	        if (inputGroupes != null && !inputGroupes.isEmpty()) {
    	            int nombreGroupe = Integer.parseInt(inputGroupes);
    	            
    	            groupeControlleur.creerGroupeAleatoire(nbrePersonneParGroupe, nombreGroupe);
    	            
    	            app.loadEntities();
    	        } else {
    	            JOptionPane.showMessageDialog(app, "Le nombre de groupes ne peut pas être vide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
    	        }
    	        
    	    } catch (NumberFormatException ex) {
    	        JOptionPane.showMessageDialog(app, "Veuillez entrer un nombre valide.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
    	    }
    	
        }
    }
}
