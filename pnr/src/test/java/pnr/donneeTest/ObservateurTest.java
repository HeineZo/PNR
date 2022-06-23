package pnr.donneeTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import org.junit.Test;
import pnr.modele.donnee.*;

/**
 * Unit test
 */
public class ObservateurTest {

    @Test
    public void constructeurObservateurTest() {
        try {
            Observateur observateur = new Observateur(1, "Test", "prenomTest");
        } catch (Exception e) {
            fail(e.getMessage());
        } 
    }

    @Test
    public void constructeurIllegalObservateurTest() {
        try {
            Observateur observateur = new Observateur(0, null, null);
            fail("Exception was expected for illegal input");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void getIdObsTest() {
        Observateur observateur = new Observateur(1, "Test", "prenomTest");
        assertEquals(1, observateur.getIdObservateur(),0);
    }

    @Test
    public void setIdObsTest() {
        Observateur observateur = new Observateur(1, "Test", "prenomTest");
        observateur.setIdObservateur(2);
        assertEquals(2, observateur.getIdObservateur());
    }

    @Test
    public void getNomObsTest() {
        Observateur observateur = new Observateur(1, "Test", "prenomTest");
        assertEquals("Test", observateur.getNom());
    }

    @Test
    public void setNomObsTest() {
        Observateur observateur = new Observateur(1, "Test", "prenomTest");
        observateur.setNom("nomTest");
        assertEquals("nomTest", observateur.getNom());
    }

    @Test
    public void getPrenomObsTest() {
        Observateur observateur = new Observateur(1, "Test", "prenomTest");
        assertEquals("prenomTest", observateur.getPrenom());
    }

    @Test
    public void setPrenomObsTest() {
        Observateur observateur = new Observateur(1, "Test", "prenomTest");
        observateur.setPrenom("Prenom");
        assertEquals("Prenom", observateur.getPrenom());
    }
}