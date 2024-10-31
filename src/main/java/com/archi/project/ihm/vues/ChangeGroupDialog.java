package com.archi.project.ihm.vues;

import javax.swing.*;
import com.archi.project.interfaces.GroupeInterface;
import com.archi.project.interfaces.SujetInterface;
import com.archi.project.interfaces.UniteEnseignementInterface;
import com.archi.project.metier.models.Eleve;
import com.archi.project.metier.models.Groupe;
import com.archi.project.metier.models.Sujet;
import com.archi.project.metier.models.UniteEnseignement;

import java.awt.*;

public class ChangeGroupDialog extends JDialog {
    private static final long serialVersionUID = 1L;
	private JComboBox<Sujet> sujetComboBox;
    private JComboBox<UniteEnseignement> ueComboBox;
    private JComboBox<Groupe> groupeComboBox;
    private JButton confirmButton;
    private JButton cancelButton;

    public ChangeGroupDialog(Frame parent, UniteEnseignementInterface ueInterface, SujetInterface sujetInterface, GroupeInterface groupeInterface, Eleve eleve) {
        super(parent, "Changer le groupe", true);
        setLayout(new GridLayout(4, 2));



        sujetComboBox = new JComboBox<>();
        ueComboBox = new JComboBox<>();
        groupeComboBox = new JComboBox<>();

        loadSujets(sujetInterface);
        loadUniteEnseignements(ueInterface);
        loadGroupes(groupeInterface);


        sujetComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Sujet) {

                    value = ((Sujet) value).getIntitule(); 
                    }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        ueComboBox.setRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 1L;

			@Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof UniteEnseignement) {

                    value = ((UniteEnseignement) value).getDesignation(); 

                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        groupeComboBox.setRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = 1L;

			@Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Groupe) {

                    value = ((Groupe) value).getIdentifiant(); 

                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });


        confirmButton = new JButton("Confirmer");
        cancelButton = new JButton("Annuler");

   
        add(new JLabel("Sujet:"));
        add(sujetComboBox);
        add(new JLabel("Unité d'Enseignement:"));
        add(ueComboBox);
        add(new JLabel("Groupe:"));
        add(groupeComboBox);
        add(confirmButton);
        add(cancelButton);


        pack();
        setLocationRelativeTo(parent);

        cancelButton.addActionListener(e -> dispose());
        confirmButton.addActionListener(e -> {
            handleChangeGroup(groupeInterface, eleve);
            dispose();
        });
    }

    private void loadSujets(SujetInterface sujetInterface) {
        for (Sujet sujet : sujetInterface.listSujets()) {
            sujetComboBox.addItem(sujet);
        }
    }

    private void loadUniteEnseignements(UniteEnseignementInterface ueInterface) {
        for (UniteEnseignement ue : ueInterface.listUEs()) {
            ueComboBox.addItem(ue);
        }
    }

    private void loadGroupes(GroupeInterface groupeInterface) {
        for (Groupe groupe : groupeInterface.listGroupes()) {
            groupeComboBox.addItem(groupe);
        }
    }

    private void handleChangeGroup(GroupeInterface groupeInterface, Eleve eleve) {
        Sujet selectedSujet = (Sujet) sujetComboBox.getSelectedItem();
        UniteEnseignement selectedUE = (UniteEnseignement) ueComboBox.getSelectedItem();
        Groupe selectedGroupe = (Groupe) groupeComboBox.getSelectedItem();

        String nouvelIdentifiant = selectedGroupe.getIdentifiant();
        boolean success = groupeInterface.changerGroupeEleve(eleve, nouvelIdentifiant, selectedSujet, selectedUE);

        if (success) {
            JOptionPane.showMessageDialog(this, "L'élève a été déplacé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Échec du déplacement de l'élève.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
