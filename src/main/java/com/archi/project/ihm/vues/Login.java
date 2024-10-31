package com.archi.project.ihm.vues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    private JFrame loginFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public Login(ActionListener loginActionListener) {
        // Créer la fenêtre de connexion
        loginFrame = new JFrame("Authentification");
        loginFrame.setSize(300, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new GridLayout(4, 2));

        // Ajouter des composants
        loginFrame.add(new JLabel("Nom d'utilisateur:"));
        usernameField = new JTextField();
        loginFrame.add(usernameField);

        loginFrame.add(new JLabel("Mot de passe:"));
        passwordField = new JPasswordField();
        loginFrame.add(passwordField);

        JButton loginButton = new JButton("Se connecter");
        loginFrame.add(loginButton);

        messageLabel = new JLabel("");
        loginFrame.add(messageLabel);

        // Action de connexion
        loginButton.addActionListener(loginActionListener);

        // Centrer la fenêtre de connexion
        loginFrame.setLocationRelativeTo(null); // Centre la fenêtre
        loginFrame.setVisible(true); // Afficher la fenêtre de connexion
    }

    // Méthode pour fermer la fenêtre de connexion
    public void close() {
        loginFrame.dispose();
    }

    // Méthode pour afficher un message
    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    // Méthode pour obtenir les informations d'identification
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}
