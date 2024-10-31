package com.archi.project.utils;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.archi.project.metier.DatabaseConnection;

public class DatabaseInitializer {

    public static void initializeDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
             
            stmt.execute("CREATE TABLE IF NOT EXISTS personne (id INT AUTO_INCREMENT PRIMARY KEY, nom VARCHAR(100), prenom VARCHAR(100), type VARCHAR(20))");
            stmt.execute("CREATE TABLE IF NOT EXISTS groupe (id INT AUTO_INCREMENT PRIMARY KEY, identifiant VARCHAR(100), id_sujet INT, id_ue INT, id_eleve INT)");
            stmt.execute("CREATE TABLE IF NOT EXISTS sujet (id INT AUTO_INCREMENT PRIMARY KEY, intitule VARCHAR(255))");
            stmt.execute("CREATE TABLE IF NOT EXISTS unite_enseignement (id INT AUTO_INCREMENT PRIMARY KEY, code VARCHAR(10), designation VARCHAR(255))");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

