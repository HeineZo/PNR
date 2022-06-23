package pnr.donneeAddsOnTest;

import static org.junit.Assert.*;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;

import org.junit.Test;
import pnr.modele.donneeAddsOn.*;

/**
 * Unit test
 */
public class HippocampeTest {

    @Test
    public void constructeurHippocampeTest() {
        try {
            Hippocampe h = new Hippocampe(1, "Hippocampus guttulatus", "Male", 12, "CasierCrevettes", 4, 0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getObsTest() {
        Hippocampe h = new Hippocampe(1, "Hippocampus guttulatus", "Male", 12, "CasierCrevettes", 4, 0);
        assertEquals(1, h.getObsH());
    }

    @Test
    public void getEspeceTest() {
        Hippocampe h = new Hippocampe(1, "Hippocampus guttulatus", "Male", 12, "CasierCrevettes", 4, 0);
        assertEquals("Hippocampus guttulatus", h.getEspece());
    }

    @Test
    public void getSexeTest() {
        Hippocampe h = new Hippocampe(1, "Hippocampus guttulatus", "Male", 12, "CasierCrevettes", 4, 0);
        assertEquals("Male", h.getSexe());
    }

    @Test
    public void getPecheTest() {
        Hippocampe h = new Hippocampe(1, "Hippocampus guttulatus", "Male", 12, "CasierCrevettes", 4, 0);
        assertEquals("CasierCrevettes", h.getTypePeche());
    }

    @Test
    public void getTempTest() {
        Hippocampe h = new Hippocampe(1, "Hippocampus guttulatus", "Male", 12, "CasierCrevettes", 4, 0);
        assertEquals(12, h.getTemperatureEau());
    }

    @Test
    public void getTailleTest() {
        Hippocampe h = new Hippocampe(1, "Hippocampus guttulatus", "Male", 12, "CasierCrevettes", 4, 0);
        assertEquals(4, h.getTaille(),0);
    }

    @Test
    public void getGestantTest() {
        Hippocampe h = new Hippocampe(1, "Hippocampus guttulatus", "Male", 12, "CasierCrevettes", 4, 0);
        assertEquals(0, h.getGestant());
    }
}