package com.archi.project.metier.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.archi.project.interfaces.UniteEnseignementInterface;
import com.archi.project.metier.DatabaseConnection;
import com.archi.project.metier.models.UniteEnseignement;


public class UniteEnseignementService implements  UniteEnseignementInterface{

    @Override
    public boolean createUE(String code, String designation) {
        String sql = "INSERT INTO unite_enseignement (code, designation) VALUES (?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.setString(2, designation);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUE(int id) {
    	 String deleteFromGroupeSQL = "DELETE FROM groupe WHERE id_ue = ?";
    	    String deleteUESQL = "DELETE FROM unite_enseignement WHERE id = ?";

    	    try (Connection connection = DatabaseConnection.getConnection()) {
    	        PreparedStatement stmtGroupe = connection.prepareStatement(deleteFromGroupeSQL);
    	        stmtGroupe.setInt(1, id);
    	        stmtGroupe.executeUpdate();

    	        PreparedStatement stmtUE = connection.prepareStatement(deleteUESQL);
    	        stmtUE.setInt(1, id);
    	        int rowsAffected = stmtUE.executeUpdate();

    	        return rowsAffected > 0;
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        return false;
    	    }
    }

    @Override
    public ArrayList<UniteEnseignement> listUEs() {
        ArrayList<UniteEnseignement> ueList = new ArrayList<>();
        String sql = "SELECT * FROM unite_enseignement";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String designation = rs.getString("designation");
                ueList.add(new UniteEnseignement(id, code, designation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ueList;
    }
    
    
}