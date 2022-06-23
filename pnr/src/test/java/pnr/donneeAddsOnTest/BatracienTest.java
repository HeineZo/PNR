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
public class BatracienTest {

    @Test
    public void constructeurBatracienTest() {
        try {
            Batracien b = new Batracien(1, "pelodyte", 4, 6, 2, 3, 14, "degage", "moyen", "fort", "non", 1, 3);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getObsTest() {
        Batracien b = new Batracien(1, "pelodyte", 4, 6, 2, 3, 14, "degage", "moyen", "fort", "non", 1, 3);
        assertEquals(1, b.getObsB());
    }

    @Test
    public void getEspeceTest() {
        Batracien b = new Batracien(1, "pelodyte", 4, 6, 2, 3, 14, "degage", "moyen", "fort", "non", 1, 3);
        assertEquals("pelodyte", b.getEspece());
    }

    @Test
    public void getNbAdultesTest() {
        Batracien b = new Batracien(1, "pelodyte", 4, 6, 2, 3, 14, "degage", "moyen", "fort", "non", 1, 3);
        assertEquals(4, b.getNombreAdultes());
    }

    @Test
    public void getNbAmplexusTest() {
        Batracien b = new Batracien(1, "pelodyte", 4, 6, 2, 3, 14, "degage", "moyen", "fort", "non", 1, 3);
        assertEquals(6, b.getNombreAmplexus());
    }

    @Test
    public void getNbPonteTest() {
        Batracien b = new Batracien(1, "pelodyte", 4, 6, 2, 3, 14, "degage", "moyen", "fort", "non", 1, 3);
        assertEquals(2, b.getNombrePonte());
    }

    @Test
    public void getNbTetardTest() {
        Batracien b = new Batracien(1, "pelodyte", 4, 6, 2, 3, 14, "degage", "moyen", "fort", "non", 1, 3);
        assertEquals(3, b.getNombreTetard());
    }

    @Test
    public void getMeteoCielTest() {
        Batracien b = new Batracien(1, "pelodyte", 4, 6, 2, 3, 14, "degage", "moyen", "fort", "non", 1, 3);
        assertEquals("degage", b.getMeteoCiel());
    }

    @Test
    public void getMeteoTempTest() {
        Batracien b = new Batracien(1, "pelodyte", 4, 6, 2, 3, 14, "degage", "moyen", "fort", "non", 1, 3);
        assertEquals("moyen", b.getMeteoTemp());
    }

    @Test
    public void getMeteoVentTest() {
        Batracien b = new Batracien(1, "pelodyte", 4, 6, 2, 3, 14, "degage", "moyen", "fort", "non", 1, 3);
        assertEquals("fort", b.getMeteoVent());
    }

    @Test
    public void getMeteoPluieTest() {
        Batracien b = new Batracien(1, "pelodyte", 4, 6, 2, 3, 14, "degage", "moyen", "fort", "non", 1, 3);
        assertEquals("non", b.getMeteoPluie());
    }

    @Test
    public void getZoneHumideTest() {
        Batracien b = new Batracien(1, "pelodyte", 4, 6, 2, 3, 14, "degage", "moyen", "fort", "non", 1, 3);
        assertEquals(1, b.getConcerneZh());
    }

    @Test
    public void getVegetationTest() {
        Batracien b = new Batracien(1, "pelodyte", 4, 6, 2, 3, 14, "degage", "moyen", "fort", "non", 1, 3);
        assertEquals(3, b.getConcernesvege());
    }
}