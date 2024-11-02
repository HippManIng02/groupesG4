package com.archi.project.ihm.vues;

import com.archi.project.metier.LogiqueMetier;
import com.archi.project.metier.models.Sujet;
import com.archi.project.ihm.controlleurs.SujetControlleur;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestionSujetApp extends GestionEntityApp<Sujet> {

	private static final long serialVersionUID = 1L;
    private final SujetControlleur sujetControlleur;
    private JFrame mainFrame;

    public GestionSujetApp(JFrame mainFrame, LogiqueMetier logiqueMetier) {
        super(mainFrame, "Gestion des Sujets", new String[]{"ID", "Intitulé"}, "Intitulé", "");
        this.mainFrame = mainFrame;
        
        sujetControlleur = new SujetControlleur(logiqueMetier);
        loadEntities();

        field2.setEnabled(false);
        field2.setVisible(false);

        createMenu();
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu backMenu = new JMenu("Menu");

        JMenuItem backMenuItem = new JMenuItem("Retour");
        backMenuItem.addActionListener(e -> {
            this.dispose();
            mainFrame.setVisible(true);
        });

        backMenu.add(backMenuItem);
        menuBar.add(backMenu);
        setJMenuBar(menuBar);
    }

    @Override
    protected void loadEntities() {
        entityTableModel.setRowCount(0);
        List<Sujet> sujets = sujetControlleur.getAllSujets();
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

        if (sujetControlleur.addSujet(intitule)) {
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
            if (sujetControlleur.deleteSujet(id)) {
                loadEntities();
                showMessage("Sujet '" + intitule + "' supprimé avec succès.", Color.RED);
            } else {
                showMessage("Erreur lors de la suppression du sujet.", Color.RED);
            }
        }
    }

//    public static void main(String[] args) {
//    	JFrame mainFrame = new JFrame();
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame.setVisible(true);
//    	SwingUtilities.invokeLater(() -> {
//            GestionSujetApp gestionSujetApp = new GestionSujetApp(mainFrame);
//            gestionSujetApp.setVisible(true);
//        });
//    }
}
