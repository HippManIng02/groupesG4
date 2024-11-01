package com.archi.project.ihm;

import javax.swing.*;
import com.archi.project.ihm.vues.GestionGroupeApp;

import com.archi.project.ihm.vues.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplication {

    private Login login;

    public MainApplication() {
        showLoginWindow();
    }

    private void showLoginWindow() {
        ActionListener loginActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = login.getUsername();
                String password = login.getPassword();

                if (username.equals("admin") && password.equals("admin")) {
                    login.setMessage("Connexion réussie !");
                    
                    // Ouvrir directement GestionGroupeApp
                    GestionGroupeApp gestionGroupe = new GestionGroupeApp(); 
                    gestionGroupe.setVisible(true);
                    login.close();
                } else {
                    login.setMessage("Nom d'utilisateur ou mot de passe incorrect.");
                }
            }
        };

        login = new Login(loginActionListener);
    }

}
