package com.archi.project.metier.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.archi.project.interfaces.GroupeInterface;
import com.archi.project.metier.DatabaseConnection;
import com.archi.project.metier.models.Eleve;
import com.archi.project.metier.models.Groupe;
import com.archi.project.metier.models.Sujet;
import com.archi.project.metier.models.UniteEnseignement;
import com.archi.project.utils.GenerateurID;

public class GroupeService implements GroupeInterface{

	@Override
	public boolean createGroupe(String identifiant, UniteEnseignement ue, ArrayList<Eleve> eleves, Sujet sujet) {
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
	            return true;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
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
	public void createGroupesAleatoires(ArrayList<UniteEnseignement> ue, ArrayList<Eleve> eleves,ArrayList<Sujet> sujets, int nbrePersonneParGroupe) {
		
        Random random = new Random();
        
        for (int i = 0; i < eleves.size(); i += nbrePersonneParGroupe) { 
            List<Eleve> groupeEleves = eleves.subList(i, Math.min(i + nbrePersonneParGroupe, eleves.size()));
            Sujet sujetAleatoire = sujets.get(random.nextInt(sujets.size()));
            UniteEnseignement ueAleatoire = ue.get(random.nextInt(ue.size()));
            String idGroupe = GenerateurID.generateShortId(); 
            createGroupe(idGroupe, ueAleatoire, new ArrayList<Eleve>(groupeEleves), sujetAleatoire);
        }
	}

	@Override
	public boolean changerGroupeEleve(Eleve eleve, String nouvelIdentifiant, Sujet sujet, UniteEnseignement ue ) {
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
	
	@Override
    public ArrayList<Groupe> listGroupes() {
        String selectGroupeSQL = """
            SELECT g.id, g.identifiant, g.id_sujet, g.id_ue, g.id_eleve, s.intitule AS sujet_nom, ue.code AS ue_code, 
            ue.designation AS eu_designation, p.nom AS eleve_nom, p.prenom AS eleve_prenom FROM groupe g 
            LEFT JOIN sujet s ON g.id_sujet = s.id 
            LEFT JOIN unite_enseignement ue ON g.id_ue = ue.id 
            LEFT JOIN personne p ON g.id_eleve = p.id AND p.type = "eleve";
            """;

        ArrayList<Groupe> listeGroupes = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(selectGroupeSQL);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String identifiant = resultSet.getString("identifiant");

                Sujet sujet = new Sujet(resultSet.getInt("id_sujet"), resultSet.getString("sujet_nom"));
                UniteEnseignement ue = new UniteEnseignement(resultSet.getInt("id_ue"), resultSet.getString("ue_code"), resultSet.getString("eu_designation"));
                Eleve eleve = new Eleve(resultSet.getInt("id_eleve"), resultSet.getString("eleve_nom"), resultSet.getString("eleve_prenom"));

                ArrayList<Eleve> eleves = new ArrayList<>();
                eleves.add(eleve);

                Groupe groupe = new Groupe(id, identifiant, sujet, ue, eleves);
                listeGroupes.add(groupe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listeGroupes;
    }
	
	
}