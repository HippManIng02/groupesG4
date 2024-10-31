package com.archi.project.ihm.ecouteurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.archi.project.ihm.vues.ChangeGroupDialog;
import com.archi.project.ihm.vues.GestionGroupeApp;
import com.archi.project.metier.models.Eleve;
import com.archi.project.metier.models.Groupe;

public class EcouteurChangerGroupe implements ActionListener {
    private GestionGroupeApp app;

    public EcouteurChangerGroupe(GestionGroupeApp app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Eleve selectedEleve = app.getSelectedEleve();
        if (selectedEleve == null) {
            JOptionPane.showMessageDialog(app, "Veuillez sélectionner un groupe avec des élèves.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ChangeGroupDialog changeGroupDialog = new ChangeGroupDialog(
            app,
            app.getUeInterface(),
            app.getSujetInterface(),
            app.getGroupeInterface(),
            app.getSelectedEleve()
        );
        changeGroupDialog.setVisible(true);
        app.loadEntities();
    }


}


