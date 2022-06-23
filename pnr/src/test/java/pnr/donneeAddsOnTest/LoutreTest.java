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
public class LoutreTest {

    @Test 
    public void constructeurLoutreTest() {
        try {
            Loutre loutre = new Loutre(1, "Vannes", "poignan", "positif");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getIdTest() {
        Loutre loutre = new Loutre(1, "Vannes", "poignan", "positif");
        assertEquals(1, loutre.getObsL());
    }

    @Test
    public void getCommuneTest() {
        Loutre loutre = new Loutre(1, "Vannes", "poignan", "positif");
        assertEquals("Vannes", loutre.getCommune());
    }

    @Test
    public void getLieuDitTest() {
        Loutre loutre = new Loutre(1, "Vannes", "poignan", "positif");
        assertEquals("poignan", loutre.getLieuDit());
    }

    @Test
    public void getIndice() {
        Loutre loutre = new Loutre(1, "Vannes", "poignan", "positif");
        assertEquals("positif", loutre.getIndice());
    }
}