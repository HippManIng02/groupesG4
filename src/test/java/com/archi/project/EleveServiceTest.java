package com.archi.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.archi.project.metier.models.Eleve;
import com.archi.project.metier.services.EleveService;
import com.archi.project.utils.DatabaseInitializer;


public class EleveServiceTest {

    private EleveService eleveService;

    @BeforeEach
    public void setUp() {
        // Initialize the database before each test
        DatabaseInitializer.initializeDatabase();
        eleveService = new EleveService();
    }

    @Test
    public void testCreateEleve() {
        boolean result = eleveService.createEleve("Jean", "Dupont");
        assertTrue(result);

        // Check that the student has been added
        var eleves = eleveService.eleves();
        assertTrue(eleves.stream().anyMatch(e -> e.getNom().equals("Jean") && e.getPrenom().equals("Dupont")));
    }

    @Test
    public void testDeleteEleve() {
        // Create a student for testing
        eleveService.createEleve("Marie", "Curie");
        var eleves = eleveService.eleves();
        assertFalse(eleves.isEmpty());

        // Delete the student
        Eleve eleve = eleves.get(0);
        boolean result = eleveService.deleteEleve(eleve.getId());
        assertTrue(result);

        // Verify that the student has been deleted
        var updatedEleves = eleveService.eleves();
        assertFalse(updatedEleves.stream().anyMatch(e -> e.getId() == eleve.getId()));
    }

    @Test
    public void testListEleves() {
        var eleves = eleveService.eleves();
        assertNotNull(eleves);
        assertTrue(eleves.size() >= 0); // Should be zero or more
    }
}
