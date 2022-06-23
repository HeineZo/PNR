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
public class VegetationTest {

    @Test 
    public void constructeurVegetationTest() {
        try {
            Vegetation vege = new Vegetation(1, "bordure", "vegetation", 1);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test 
    public void constructeurIllegalVegetationTest() {
        try {
            Vegetation vege = new Vegetation(0, null, null, 0);
            fail("Exception was expected for null input");
        } catch (IllegalArgumentException e) {          
        }
    }

    @Test
    public void getIdTest() {
        Vegetation vege = new Vegetation(1, "bordure", "vegetation", 1);
        assertEquals(1, vege.getId());
    }

    @Test
    public void getNatureVegeTest() {
        Vegetation vege = new Vegetation(1, "bordure", "vegetation", 1);
        assertEquals("bordure", vege.getNatureVege());
    }

    @Test
    public void getVegetationTest() {
        Vegetation vege = new Vegetation(1, "bordure", "vegetation", 1);
        assertEquals("vegetation", vege.getVegetation());
    }

    @Test
    public void getDecritLieuTest() {
        Vegetation vege = new Vegetation(1, "bordure", "vegetation", 1);
        assertEquals(1, vege.getDecritLieuVege());
    }
}