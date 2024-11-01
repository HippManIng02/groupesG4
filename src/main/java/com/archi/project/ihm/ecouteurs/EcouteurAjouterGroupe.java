package com.archi.project.ihm.ecouteurs;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import com.archi.project.ihm.controlleurs.GroupeControlleur;
import com.archi.project.ihm.vues.GestionGroupeApp;
import com.archi.project.metier.models.Sujet;
import com.archi.project.metier.models.UniteEnseignement;

public class EcouteurAjouterGroupe implements ActionListener {
    private GestionGroupeApp app;
    private GroupeControlleur groupeControlleur;

    public EcouteurAjouterGroupe(GestionGroupeApp app, GroupeControlleur gpc) {
        this.app = app;
        this.groupeControlleur = gpc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String identifiant = app.getIdentifiantField().getText().trim();
        UniteEnseignement ue = (UniteEnseignement) app.getUeComboBox().getSelectedItem();
        Sujet sujet = (Sujet) app.getSujetComboBox().getSelectedItem();

        if (identifiant.isEmpty() || ue == null || app.getSelectedEleves().isEmpty() || sujet == null) {
            JOptionPane.showMessageDialog(app, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = groupeControlleur.addGroupe(identifiant, ue, app.getSelectedEleves(), sujet);

        if (success) {
            JOptionPane.showMessageDialog(app, "Groupe ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            app.loadEntities();
        } else {
            JOptionPane.showMessageDialog(app, "Échec de l'ajout du groupe.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        app.getSelectedEleves().clear();
        app.getRecapArea().setText("");
    }
}
