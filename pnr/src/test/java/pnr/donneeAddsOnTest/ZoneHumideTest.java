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
public class ZoneHumideTest {

    @Test 
    public void constructeurZoneHumideTest() {
        try {
            ZoneHumide zh = new ZoneHumide(1, 1, 3.5, 10.5, "mare", "raide", "ouverte");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test 
    public void constructeurIllegalZoneHumideTest() {
        try {
            ZoneHumide zh = new ZoneHumide(0, 0, 0, 0, null, null, null);
            fail("Exception was expected for null input");
        } catch (IllegalArgumentException e) {          
        }
    }

    @Test
    public void getIdTest() {
        ZoneHumide zh = new ZoneHumide(1, 1, 3.5, 10.5, "mare", "raide", "ouverte");
        assertEquals(1, zh.getId());
    }

    @Test
    public void getTemporaireTest() {
        ZoneHumide zh = new ZoneHumide(1, 1, 3.5, 10.5, "mare", "raide", "ouverte");
        assertEquals(1, zh.getTemporaire());
    }

    @Test
    public void getProfondeurTest() {
        ZoneHumide zh = new ZoneHumide(1, 1, 3.5, 10.5, "mare", "raide", "ouverte");
        assertEquals(3.5, zh.getProfondeur(),0);
    }

    @Test
    public void getSurfaceTest() {
        ZoneHumide zh = new ZoneHumide(1, 1, 3.5, 10.5, "mare", "raide", "ouverte");
        assertEquals(10.5, zh.getSurface(),0);
    }

    @Test
    public void getTypeMareTest() {
        ZoneHumide zh = new ZoneHumide(1, 1, 3.5, 10.5, "mare", "raide", "ouverte");
        assertEquals("mare", zh.getgetTypeMare());
    }

    @Test
    public void getPenteTest() {
        ZoneHumide zh = new ZoneHumide(1, 1, 3.5, 10.5, "mare", "raide", "ouverte");
        assertEquals("raide", zh.getgetPente());
    }

    @Test
    public void getOuvertureTest() {
        ZoneHumide zh = new ZoneHumide(1, 1, 3.5, 10.5, "mare", "raide", "ouverte");
        assertEquals("ouverte", zh.getgetOuverture());
    }
}