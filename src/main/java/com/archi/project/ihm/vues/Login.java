package com.archi.project.ihm.vues;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class Login {

    private JFrame loginFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public Login(ActionListener loginActionListener) {

        
        loginFrame = new JFrame("Authentification");
        loginFrame.setSize(800, 400);


    
        
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);


        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
       
        loginFrame.add(new JLabel("Nom d'utilisateur:"));
        usernameField = new JTextField();
        loginFrame.add(usernameField);

        
        JLabel titleLabel = new JLabel("Bienvenue", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/login_2.png"));
        imageLabel.setIcon(icon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(imageLabel, BorderLayout.WEST);

        
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridBagLayout());
        fieldsPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        fieldsPanel.add(new JLabel("Nom d'utilisateur : "), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldsPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        fieldsPanel.add(new JLabel("Mot de passe : "), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldsPanel.add(passwordField, gbc);

        
        gbc.gridx = 1;
        gbc.gridy = 2;
        JButton loginButton = new JButton("Se connecter");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.BLACK);
        fieldsPanel.add(loginButton, gbc);

        
        gbc.gridx = 1;
        gbc.gridy = 3;
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);
        messageLabel.setPreferredSize(new Dimension(300, 20));
        fieldsPanel.add(messageLabel, gbc);

        
        mainPanel.add(fieldsPanel, BorderLayout.CENTER);

       
        loginButton.addActionListener(loginActionListener);

        
        loginFrame.add(mainPanel);
        
        loginFrame.setLocationRelativeTo(null); 
        loginFrame.setVisible(true); 
    }

    public void close() {
        loginFrame.dispose();
    }


    public void setMessage(String message) {
        messageLabel.setText(message);
    }


    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}

