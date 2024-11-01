package com.archi.project.ihm.vues;

import com.archi.project.metier.models.UniteEnseignement;
import com.archi.project.metier.models.Sujet;

import javax.swing.*;
import java.awt.*;

public class CustomComboBoxRenderer extends DefaultListCellRenderer {



    private static final long serialVersionUID = 1L;

	@Override

    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                   boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof UniteEnseignement) {
            UniteEnseignement ue = (UniteEnseignement) value;
            label.setText(ue.getCode() + " - " + ue.getDesignation()); 
        } else if (value instanceof Sujet) {
            Sujet sujet = (Sujet) value;
            label.setText(sujet.getIntitule()); 
        }
        return label;
    }
}
