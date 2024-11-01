package com.archi.project.ihm.ecouteurs;

import com.archi.project.ihm.controlleurs.GroupeControlleur;
import com.archi.project.ihm.vues.GestionGroupeApp;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurSupprimerGroupe implements ActionListener {

    private GestionGroupeApp gestionGroupeApp;
    private GroupeControlleur groupeControlleur;

    public EcouteurSupprimerGroupe(GestionGroupeApp gestionGroupeApp,GroupeControlleur gpc) {
        this.gestionGroupeApp = gestionGroupeApp;
        this.groupeControlleur = gpc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = gestionGroupeApp.getEntityTable().getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(gestionGroupeApp.getFrame(),
                    "Veuillez sélectionner un groupe à supprimer.",
                    "Erreur de suppression",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(gestionGroupeApp.getFrame(),
                "Êtes-vous sûr de vouloir supprimer ce groupe ?",
                "Confirmation de suppression",
                JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            
            String groupeId = (String) gestionGroupeApp.getEntityTableModel().getValueAt(selectedRow, 0);
          
            groupeControlleur.deleteGroupe(groupeId);

            gestionGroupeApp.loadEntities();

            JOptionPane.showMessageDialog(gestionGroupeApp.getFrame(),
                    "Groupe supprimé avec succès.",
                    "Suppression réussie",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
