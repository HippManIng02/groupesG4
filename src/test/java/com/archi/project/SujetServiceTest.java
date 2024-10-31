package com.archi.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.archi.project.metier.services.SujetService;
import com.archi.project.metier.models.Sujet;
import com.archi.project.utils.DatabaseInitializer;


public class SujetServiceTest {

    private SujetService sujetService;

    @BeforeEach
    public void setUp() {
        // Initialize the database before each test
        DatabaseInitializer.initializeDatabase();
        sujetService = new SujetService();
    }

    @Test
    public void testCreateSujet() {
        boolean result = sujetService.createSujet("Introduction à la programmation");
        assertTrue(result);

        // Verify that the subject has been added
        var sujets = sujetService.listSujets();
        assertTrue(sujets.stream().anyMatch(s -> s.getIntitule().equals("Introduction à la programmation")));
    }

    @Test
    public void testDeleteSujet() {
        // Create a subject for testing
        sujetService.createSujet("Sujet Test");
        var sujets = sujetService.listSujets();
        assertFalse(sujets.isEmpty());

        // Delete the subject
        Sujet sujet = sujets.get(0);
        boolean result = sujetService.deleteSujet(sujet.getId());
        assertTrue(result);

        // Verify that the subject has been deleted
        var updatedSujets = sujetService.listSujets();
        assertFalse(updatedSujets.stream().anyMatch(s -> s.getId() == sujet.getId()));
    }

    @Test
    public void testListSujets() {
        var sujets = sujetService.listSujets();
        assertNotNull(sujets);
        assertTrue(sujets.size() >= 0); // Should be zero or more
    }
}
