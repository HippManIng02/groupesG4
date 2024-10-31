package com.archi.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.archi.project.metier.services.GroupeService;
import com.archi.project.metier.models.UniteEnseignement;
import com.archi.project.metier.models.Sujet;
import com.archi.project.metier.models.Eleve;
import com.archi.project.utils.DatabaseInitializer;

import java.util.ArrayList;

public class GroupeServiceTest {

    private GroupeService groupeService;
    private UniteEnseignement ue;
    private Sujet sujet;

    @BeforeEach
    public void setUp() {
        // Initialize the database before each test
        DatabaseInitializer.initializeDatabase();
        groupeService = new GroupeService();
        ue = new UniteEnseignement(1, "MATHS300", "Maths de base"); // Example UE
        sujet = new Sujet(1, "Algorithmes"); // Example subject
    }

    @Test
    public void testCreateGroupe() {
        ArrayList<Eleve> eleves = new ArrayList<>();
        eleves.add(new Eleve(1, "Jean", "Dupont")); // Adjust these IDs
        eleves.add(new Eleve(2, "Marie", "Curie")); // Same here

        boolean result = groupeService.createGroupe("Groupe-1", ue, eleves, sujet);
        assertTrue(result);

        // Verify if the group has been created by checking existing groups
        var groupes = groupeService.listGroupes();
        assertTrue(groupes.stream().anyMatch(g -> g.getIdentifiant().equals("Groupe-1")));
    }

    @Test
    public void testDeleteGroupe() {
        // Create a group for testing
        ArrayList<Eleve> eleves = new ArrayList<>();
        eleves.add(new Eleve(1, "Jean", "Dupont"));
        boolean result = groupeService.createGroupe("Groupe-2", ue, eleves, sujet);
        assertTrue(result);

        // Delete the group
        result = groupeService.deleteGroupe("Groupe-2");
        assertTrue(result);

        // Verify that the group has been deleted
        var groupes = groupeService.listGroupes();
        assertFalse(groupes.stream().anyMatch(g -> g.getIdentifiant().equals("Groupe-2")));
    }

    @Test
    public void testCreateGroupesAleatoires() {
        ArrayList<Eleve> eleves = new ArrayList<>();
        eleves.add(new Eleve(1, "Jean", "Dupont"));
        eleves.add(new Eleve(2, "Marie", "Curie"));
        eleves.add(new Eleve(3, "Albert", "Einstein"));

        ArrayList<Sujet> sujets = new ArrayList<>();
        sujets.add(new Sujet(1, "Algorithmes"));
        sujets.add(new Sujet(2, "Structures de données"));
        
        ArrayList<UniteEnseignement> ues = new ArrayList<>();
        ues.add(new UniteEnseignement(1,"MATHS222", "Algorithmes"));
        ues.add(new UniteEnseignement(2, "SP106", "Structures de données"));

        groupeService.createGroupesAleatoires(ues, eleves, sujets, 3);

        var groupes = groupeService.listGroupes();
        assertTrue(groupes.size() > 0); // Ensure groups were created
    }

    @Test
    public void testChangerGroupeEleve() {
        ArrayList<Eleve> eleves = new ArrayList<>();
        eleves.add(new Eleve(1, "Jean", "Dupont"));

        groupeService.createGroupe("Groupe-3", ue, eleves, sujet);

        boolean result = groupeService.changerGroupeEleve(eleves.get(0), "Groupe-Nouveau", sujet, ue);
        assertTrue(result);

        // Verify that the student has changed groups
        var groupes = groupeService.listGroupes();
        assertTrue(groupes.stream().anyMatch(g -> g.getIdentifiant().equals("Groupe-Nouveau")));
    }
}
