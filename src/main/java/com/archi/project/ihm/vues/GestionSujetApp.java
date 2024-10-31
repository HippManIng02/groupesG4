package com.archi.project.ihm.vues;

import com.archi.project.metier.models.Sujet;
import com.archi.project.interfaces.SujetInterface;
import com.archi.project.metier.services.SujetService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GestionSujetApp extends GestionEntityApp<Sujet> {

    private SujetInterface sujetInterface;

    public GestionSujetApp() {
        super("Gestion des Sujets", new String[]{"ID", "Intitulé"}, "Intitulé", "");
        sujetInterface = new SujetService();
        loadEntities();

        field2.setEnabled(false);
        field2.setVisible(false);
    }

    @Override
    protected void loadEntities() {
        entityTableModel.setRowCount(0);
        ArrayList<Sujet> sujets = sujetInterface.listSujets();
        for (Sujet sujet : sujets) {
            entityTableModel.addRow(new Object[]{sujet.getId(), sujet.getIntitule()});
        }
    }

    @Override
    protected void addEntity() {
        String intitule = field1.getText().trim();

        if (intitule.isEmpty()) {
            showMessage("Veuillez remplir tous les champs.", Color.RED);
            return;
        }

        if (sujetInterface.createSujet(intitule)) {
            loadEntities();
            field1.setText("");
            showMessage("Sujet '" + intitule + "' ajouté avec succès.", new Color(0, 128, 0));
        } else {
            showMessage("Erreur lors de l'ajout du sujet.", Color.RED);
        }
    }

    @Override
    protected void deleteEntity() {
        int selectedRow = entityTable.getSelectedRow();
        if (selectedRow == -1) {
            showMessage("Veuillez sélectionner un sujet à supprimer.", Color.RED);
            return;
        }

        int id = (int) entityTable.getValueAt(selectedRow, 0);
        String intitule = (String) entityTable.getValueAt(selectedRow, 1);

        int confirmation = JOptionPane.showConfirmDialog(this,
                "Êtes-vous sûr de vouloir supprimer le sujet : " + intitule + " ?",
                "Confirmation de suppression",
                JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            if (sujetInterface.deleteSujet(id)) {
                loadEntities();
                showMessage("Sujet '" + intitule + "' supprimé avec succès.", Color.RED);
            } else {
                showMessage("Erreur lors de la suppression du sujet.", Color.RED);
            }
        }
    }

    @Override
    protected void customizeButton(JButton button, Color backgroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestionSujetApp gestionSujetApp = new GestionSujetApp();
            gestionSujetApp.setVisible(true);
        });
    }
}
