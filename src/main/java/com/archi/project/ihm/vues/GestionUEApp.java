package com.archi.project.ihm.vues;

import com.archi.project.metier.models.UniteEnseignement;
import com.archi.project.ihm.controlleurs.UeControlleur;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestionUEApp extends GestionEntityApp<UniteEnseignement> {

	 private static final long serialVersionUID = 1L;
	    private final UeControlleur ueControlleur;

	    /**
	     * Constructeur de la fenêtre de gestion des Unités d'Enseignement (UE).
	     * Initialise le contrôleur et charge les UEs dans le tableau.
	     * @param mainFrame La fenêtre principale de l'application.
	     */
	    public GestionUEApp(JFrame mainFrame) {
	        super(mainFrame, "Gestion des Unités d'Enseignement", new String[]{"ID", "Code", "Désignation"}, "Code", "Désignation");

	        // Initialise le contrôleur pour gérer les opérations sur les UEs
	        ueControlleur = new UeControlleur();

	        // Charge les UEs dans le tableau au démarrage
	        loadEntities();
	    }

	    /**
	     * Charge toutes les UEs depuis le contrôleur et les affiche dans le tableau.
	     * Cette méthode est appelée pour rafraîchir la liste après une modification.
	     */
	    @Override
	    protected void loadEntities() {
	        entityTableModel.setRowCount(0); // Réinitialise le tableau
	        List<UniteEnseignement> ueListData = ueControlleur.getAllUEs(); // Récupère toutes les UEs
	        for (UniteEnseignement ue : ueListData) {
	            // Ajoute chaque UE en tant que ligne dans le tableau
	            entityTableModel.addRow(new Object[]{ue.getId(), ue.getCode(), ue.getDesignation()});
	        }
	    }

	    /**
	     * Ajoute une nouvelle UE en utilisant les valeurs saisies par l'utilisateur.
	     * Si l'ajout réussit, le tableau est actualisé et un message de confirmation est affiché.
	     */
	    @Override
	    protected void addEntity() {
	        String code = field1.getText().trim(); // Récupère le code de l'UE
	        String designation = field2.getText().trim(); // Récupère la désignation de l'UE
	        
	        // Vérifie que tous les champs sont remplis
	        if (code.isEmpty() || designation.isEmpty()) {
	            showMessage("Veuillez remplir tous les champs.", Color.RED);
	            return;
	        }

	        // Tente d'ajouter l'UE via le contrôleur
	        if (ueControlleur.addUE(code, designation)) {
	            loadEntities(); // Actualise la liste des UEs
	            field1.setText(""); // Réinitialise les champs de saisie
	            field2.setText("");
	            showMessage("L'UE '" + designation + "' avec le code '" + code + "' a été ajoutée avec succès.", new Color(0, 128, 0));
	        } else {
	            showMessage("Erreur lors de l'ajout de l'UE.", Color.RED);
	        }
	    }

	    /**
	     * Supprime l'UE sélectionnée dans le tableau après confirmation de l'utilisateur.
	     * Si la suppression réussit, le tableau est actualisé et un message de confirmation est affiché.
	     */
	    @Override
	    protected void deleteEntity() {
	        int selectedRow = entityTable.getSelectedRow(); // Récupère la ligne sélectionnée
	        // Vérifie qu'une ligne est bien sélectionnée
	        if (selectedRow == -1) {
	            showMessage("Veuillez sélectionner une UE à supprimer.", Color.RED);
	            return;
	        }

	        int id = (int) entityTable.getValueAt(selectedRow, 0); // ID de l'UE à supprimer
	        String code = (String) entityTable.getValueAt(selectedRow, 1); // Code de l'UE
	        String designation = (String) entityTable.getValueAt(selectedRow, 2); // Désignation de l'UE
	        
	        // Demande confirmation avant suppression
	        int confirmation = JOptionPane.showConfirmDialog(this, 
	            "Êtes-vous sûr de vouloir supprimer l'UE avec ID: " + id + " ?", 
	            "Confirmation de suppression", 
	            JOptionPane.YES_NO_OPTION);

	        // Si l'utilisateur confirme, supprime l'UE via le contrôleur
	        if (confirmation == JOptionPane.YES_OPTION) {
	            if (ueControlleur.deleteUE(id)) {
	                loadEntities(); // Actualise la liste des UEs
	                showMessage("L'UE '" + designation + "' avec le code '" + code + "' a été supprimée avec succès.", Color.RED);
	            } else {
	                showMessage("Erreur lors de la suppression de l'UE.", Color.RED);
	            }
	        }
	    }

	    /**
	     * Point d'entrée principal pour exécuter l'application de gestion des UEs.
	     * Crée une instance de la fenêtre de gestion et la rend visible.
	     */
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            // Crée et affiche la fenêtre de gestion des UEs
	            JFrame frame = new JFrame();
	            GestionUEApp gestionUE = new GestionUEApp(frame);
	            gestionUE.setVisible(true);
	        });
	    }

}
