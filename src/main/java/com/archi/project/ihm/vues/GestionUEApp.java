package com.archi.project.ihm.vues;

import com.archi.project.metier.models.UniteEnseignement;
import com.archi.project.interfaces.UniteEnseignementInterface;
import com.archi.project.metier.services.UniteEnseignementService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GestionUEApp extends GestionEntityApp<UniteEnseignement> {

    private UniteEnseignementInterface ueInterface;

    public GestionUEApp() {
        super("Gestion des Unités d'Enseignement", new String[]{"ID", "Code", "Désignation"}, "Code", "Désignation");
        ueInterface = new UniteEnseignementService();
        loadEntities(); 
    }

    @Override
    protected void loadEntities() {
        entityTableModel.setRowCount(0);
        ArrayList<UniteEnseignement> ueListData = ueInterface.listUEs();
        for (UniteEnseignement ue : ueListData) {
            entityTableModel.addRow(new Object[]{ue.getId(), ue.getCode(), ue.getDesignation()});
        }
    }

    @Override
    protected void addEntity() {
        String code = field1.getText().trim(); 
        String designation = field2.getText().trim(); 
        
        if (code.isEmpty() || designation.isEmpty()) {
            showMessage("Veuillez remplir tous les champs.", Color.RED);
            return;
        }

        if (ueInterface.createUE(code, designation)) {
            loadEntities(); 
            field1.setText(""); 
            field2.setText(""); 
            showMessage("L'UE '" + designation + "' avec le code '" + code + "' a été ajoutée avec succès.", new Color(0, 128, 0));
        } else {
            showMessage("Erreur lors de l'ajout de l'UE.", Color.RED);
        }
    }

    @Override
    protected void deleteEntity() {
        int selectedRow = entityTable.getSelectedRow();
        if (selectedRow == -1) {
            showMessage("Veuillez sélectionner une UE à supprimer.", Color.RED);
            return;
        }

        int id = (int) entityTable.getValueAt(selectedRow, 0);
        String code = (String) entityTable.getValueAt(selectedRow, 1);
        String designation = (String) entityTable.getValueAt(selectedRow, 2);
        int confirmation = JOptionPane.showConfirmDialog(this, 
            "Êtes-vous sûr de vouloir supprimer l'UE avec ID: " + id + " ?", 
            "Confirmation de suppression", 
            JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            if (ueInterface.deleteUE(id)) {
                loadEntities();
                showMessage("L'UE '" + designation + "' avec le code '" + code + "' a été supprimée avec succès.", Color.RED);
            } else {
                showMessage("Erreur lors de la suppression de l'UE.", Color.RED);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestionUEApp gestionUE = new GestionUEApp();
            gestionUE.setVisible(true);
        });
    }
}