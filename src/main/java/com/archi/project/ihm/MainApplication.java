package com.archi.project.ihm;

import javax.swing.*;

import com.archi.project.ihm.vues.GestionEleveApp;
import com.archi.project.ihm.vues.GestionSujetApp;
import com.archi.project.ihm.vues.GestionUEApp;
import com.archi.project.ihm.vues.Login;

import java.awt.*;
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
                    login.close();
                    openMainApplication();
                } else {
                    login.setMessage("Nom d'utilisateur ou mot de passe incorrect.");
                }
            }
        };

        login = new Login(loginActionListener);
    }

    private void openMainApplication() {
        JFrame mainFrame = new JFrame("Application Principale");
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setLocationRelativeTo(null); 


        JMenuBar menuBar = new JMenuBar();


        
        JButton gestionSujetsButton = new JButton("Gestion Sujets");
        gestionSujetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GestionSujetApp gestionSujet=new GestionSujetApp();
               gestionSujet.setVisible(true);;
            }
        });

        JButton gestionUEButton = new JButton("Gestion UE");
        gestionUEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GestionUEApp gestionUe=new GestionUEApp();
            	gestionUe.setVisible(true);
            	
            }
        });

        JButton gestionEleveButton = new JButton("Gestion Élèves");
        gestionEleveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GestionEleveApp gestionEleve=new GestionEleveApp();
            	gestionEleve.setVisible(true);
            
            }
        });


        menuBar.add(gestionSujetsButton);
        menuBar.add(gestionUEButton);
        menuBar.add(gestionEleveButton);

        mainFrame.setJMenuBar(menuBar);
        mainFrame.setVisible(true); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApplication::new);
    }
}
