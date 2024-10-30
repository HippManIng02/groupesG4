package com.archi.project.metier.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.archi.project.interfaces.GroupeInterface;
import com.archi.project.metier.DatabaseConnection;
import com.archi.project.metier.models.Eleve;
import com.archi.project.metier.models.Sujet;
import com.archi.project.metier.models.UniteEnseignement;

public class GroupeService implements GroupeInterface{

	@Override
	public void createGroupe(String identifiant, UniteEnseignement ue, ArrayList<Eleve> eleves, Sujet sujet) {
		 String insertGroupeSQL = "INSERT INTO groupe (identifiant, id_sujet, id_ue, id_eleve) VALUES (?, ?, ?, ?)";
	        
	        try (Connection connection = DatabaseConnection.getConnection()) {
	            PreparedStatement stmt = connection.prepareStatement(insertGroupeSQL);

	            for (Eleve eleve : eleves) {
	                stmt.setString(1, identifiant);
	                stmt.setInt(2, sujet.getId());
	                stmt.setInt(3, ue.getId());
	                stmt.setInt(4, eleve.getId());
	                stmt.addBatch();
	            }
	            stmt.executeBatch();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public boolean deleteGroupe(String identifiant) {
		String deleteGroupeSQL = "DELETE FROM groupe WHERE identifiant = ?";
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(deleteGroupeSQL);
            stmt.setString(1, identifiant);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
		
	}

	@Override
	public void createGroupesAleatoires(UniteEnseignement ue, ArrayList<Eleve> eleves,
			ArrayList<Sujet> sujets) {
		
        Random random = new Random();
        
        for (int i = 0; i < eleves.size(); i += 3) { 
            List<Eleve> groupeEleves = eleves.subList(i, Math.min(i + 3, eleves.size()));
            Sujet sujetAleatoire = sujets.get(random.nextInt(sujets.size()));
            createGroupe("Groupe-" + (i / 3 + 1), ue, new ArrayList<Eleve>(groupeEleves), sujetAleatoire);
        }
	}

	@Override
	public boolean changerGroupeEleve(Eleve eleve, String nouvelIdentifiant, Sujet sujet, UniteEnseignement ue) {
		 String updateEleveGroupeSQL = "UPDATE groupe SET identifiant = ?, id_sujet = ?, id_ue = ? WHERE id_eleve = ?";

	        try (Connection connection = DatabaseConnection.getConnection()) {
	            PreparedStatement stmt = connection.prepareStatement(updateEleveGroupeSQL);
	            stmt.setString(1, nouvelIdentifiant);
	            stmt.setInt(2, sujet.getId());
	            stmt.setInt(3, ue.getId());
	            stmt.setInt(4, eleve.getId());
	            int rowsAffected = stmt.executeUpdate();

	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	}
	
}