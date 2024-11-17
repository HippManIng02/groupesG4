package com.archi.project.metier;

import java.io.IOException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static String url;
    private static String user;
    private static String password;
    
    private static final String ENV_CONFIG_PATH = "DB_CONFIG_PATH"; // Nom de la variable d'environnement

    private static Connection connection;

    // Chargement des propriétés avec une block static (l'idée est d'exécuté une seule fois ce block lors d'une premier chargement par le JVM)
    static {
    	Properties prop = new Properties();
        String configPath = System.getenv(ENV_CONFIG_PATH); // Récupérer le chemin depuis la variable d'environnement

        if (configPath == null || configPath.isEmpty()) {
            System.err.println("Erreur : La variable d'environnement 'DB_CONFIG_PATH' n'est pas définie.");
        }

        try (FileInputStream fis = new FileInputStream(configPath)) {
            // Charger les propriétés du fichier
            prop.load(fis);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier de configuration : " + e.getMessage());
        }
        
        url = prop.getProperty("db.url");
        user = prop.getProperty("db.user");
        password = prop.getProperty("db.password");
    }
    /*
     * Méthode static permettant d'assurer l'utilisation d'une seule connection dans toute l'application 
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
    
}
