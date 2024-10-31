package com.archi.project.ihm.ecouteurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import com.archi.project.ihm.vues.GestionGroupeApp;

public class EcouteurSupprimerGroupe implements ActionListener {
    private GestionGroupeApp app;

    public EcouteurSupprimerGroupe(GestionGroupeApp app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = app.getEntityTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(app, "Veuillez sélectionner un groupe à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String identifiant = (String) app.getEntityTableModel().getValueAt(selectedRow, 0);
        boolean success = app.getGroupeInterface().deleteGroupe(identifiant);

        if (success) {
            JOptionPane.showMessageDialog(app, "Groupe supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            app.loadEntities();
        } else {
            JOptionPane.showMessageDialog(app, "Échec de la suppression du groupe.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
