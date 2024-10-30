package com.archi.project.metier;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static String url;
    private static String user;
    private static String password;

    private static Connection connection;

    // Chargement des propriétés avec une block static (l'idée est d'exécuté une seule fois ce block lors d'une premier chargement par le JVM)
    static {
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Désolé, le fichier database.properties est introuvable.");
            }
            //Chargement des clé-valeurs depuis le fichier database.properties dans l'objet prop
            prop.load(input);

            // Lecture des propriétés de connection
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
