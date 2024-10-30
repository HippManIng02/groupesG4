package com.archi.project.metier.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.archi.project.interfaces.UniteEnseignementInterface;
import com.archi.project.metier.DatabaseConnection;
import com.archi.project.metier.models.UniteEnseignement;


public class UniteEnseignementService implements  UniteEnseignementInterface{

    @Override
    public void createUE(String code, String designation) {
        String sql = "INSERT INTO unite_enseignement (code, designation) VALUES (?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.setString(1, designation);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUE(int id) {
        String sql = "DELETE FROM unite_enseignement WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UniteEnseignement> listUEs() {
         List<UniteEnseignement> ueList = new ArrayList<>();
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