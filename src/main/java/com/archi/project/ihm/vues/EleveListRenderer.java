package com.archi.project.ihm.vues;


import com.archi.project.metier.models.Eleve;

import javax.swing.*;
import java.awt.*;

public class EleveListRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = 1L;

	@Override

    public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                                                  boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Eleve) {
            Eleve eleve = (Eleve) value;
            label.setText("ID: " + eleve.getId() + " - " + eleve.getNom() + " " + eleve.getPrenom());
        }
        return label;
    }
}
