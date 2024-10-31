package com.archi.project.ihm.vues;

import com.archi.project.metier.models.Eleve;
import com.archi.project.interfaces.EleveInterface;
import com.archi.project.metier.services.EleveService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GestionEleveApp extends GestionEntityApp<Eleve> {

    private static final long serialVersionUID = 1L;
	private EleveInterface eleveInterface;

    public GestionEleveApp() {
        super("Gestion des Élèves", new String[]{"ID", "Nom", "Prénom"}, "Nom", "Prénom");
        eleveInterface = new EleveService();
        loadEntities(); 
    }

    @Override
    protected void loadEntities() {
        entityTableModel.setRowCount(0);
        ArrayList<Eleve> eleveListData = eleveInterface.eleves();
        for (Eleve eleve : eleveListData) {
            entityTableModel.addRow(new Object[]{eleve.getId(), eleve.getNom(), eleve.getPrenom()});
        }
    }

    @Override
    protected void addEntity() {
        String nom = field1.getText().trim(); 
        String prenom = field2.getText().trim(); 
        
        if (nom.isEmpty() || prenom.isEmpty()) {
            showMessage("Veuillez remplir tous les champs.", Color.RED);
            return;
        }

        if (eleveInterface.createEleve(nom, prenom)) {
            loadEntities(); 
            field1.setText("");
            field2.setText(""); 
            showMessage("L'élève '" + nom + " " + prenom + "' a été ajouté avec succès.", new Color(0, 128, 0));
        } else {
            showMessage("Erreur lors de l'ajout de l'élève.", Color.RED);
        }
    }

    @Override
    protected void deleteEntity() {
        int selectedRow = entityTable.getSelectedRow();
        if (selectedRow == -1) {
            showMessage("Veuillez sélectionner un élève à supprimer.", Color.RED);
            return;
        }

        int id = (int) entityTable.getValueAt(selectedRow, 0);
        String nom = (String) entityTable.getValueAt(selectedRow, 1);
        String prenom = (String) entityTable.getValueAt(selectedRow, 2);
        int confirmation = JOptionPane.showConfirmDialog(this, 
            "Êtes-vous sûr de vouloir supprimer l'élève avec ID: " + id + " ?", 
            "Confirmation de suppression", 
            JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            if (eleveInterface.deleteEleve(id)) {
                loadEntities();
                showMessage("L'élève '" + nom + " " + prenom + "' a été supprimé avec succès.", Color.RED);
            } else {
                showMessage("Erreur lors de la suppression de l'élève.", Color.RED);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestionEleveApp gestionEleve = new GestionEleveApp();
            gestionEleve.setVisible(true);
        });
    }
}
