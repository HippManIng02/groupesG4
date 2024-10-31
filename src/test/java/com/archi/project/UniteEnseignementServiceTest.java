package com.archi.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.archi.project.metier.services.UniteEnseignementService;
import com.archi.project.metier.models.UniteEnseignement;
import com.archi.project.utils.DatabaseInitializer;

import java.util.ArrayList;

public class UniteEnseignementServiceTest {

    private UniteEnseignementService uniteEnseignementService;

    @BeforeEach
    public void setUp() {
        // Initialize the database before each test
        DatabaseInitializer.initializeDatabase();
        uniteEnseignementService = new UniteEnseignementService();
    }

    @Test
    public void testCreateUE() {
        boolean result = uniteEnseignementService.createUE("CS101", "Computer Science Basics");
        assertTrue(result);

        // Check if the newly created UE exists in the database
        ArrayList<UniteEnseignement> ues = uniteEnseignementService.listUEs();
        assertTrue(ues.stream().anyMatch(ue -> ue.getCode().equals("CS101") && ue.getDesignation().equals("Computer Science Basics")));
    }

    @Test
    public void testDeleteUE() {
        // Create a new UE to test deletion
        uniteEnseignementService.createUE("CS102", "Advanced Programming");
        ArrayList<UniteEnseignement> ues = uniteEnseignementService.listUEs();
        assertFalse(ues.isEmpty());

        // Get the ID of the UE to delete
        UniteEnseignement ueToDelete = ues.get(0);
        boolean result = uniteEnseignementService.deleteUE(ueToDelete.getId());
        assertTrue(result);

        // Verify that the UE has been deleted
        ArrayList<UniteEnseignement> updatedUes = uniteEnseignementService.listUEs();
        assertFalse(updatedUes.stream().anyMatch(ue -> ue.getId() == ueToDelete.getId()));
    }

    @Test
    public void testListUEs() {
        ArrayList<UniteEnseignement> ues = uniteEnseignementService.listUEs();
        assertNotNull(ues);
        assertTrue(ues.size() >= 0); // Should be zero or more
    }
}
	