package pnr;

import static org.junit.Assert.*;

import org.junit.Test;
import pnr.modele.donnee.*;

/**
 * Unit test
 */
public class LieuTest {

    @Test 
    public void constructeurLieuTest() {
        try {
            Lieu lieu = new Lieu(0.1, 1.0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test 
    public void constructeurIllegalLieuTest() {
        try {
            Lieu lieu = new Lieu(-5, -6);
            fail("Exception was expected for illegal input");
        } catch (IllegalArgumentException e) {          
        }
    }

    @Test 
    public void getXCoordTest() {
        Lieu lieu = new Lieu(1, 2);
        assertEquals(1, lieu.getXCoord(), 0);
    }

    @Test 
    public void setXCoordTest() {
        Lieu lieu = new Lieu(1, 2);
        lieu.setXCoord(3);
        assertEquals(3, lieu.getXCoord(), 0);
    }

    @Test 
    public void getYCoordTest() {
        Lieu lieu = new Lieu(1, 2);
        assertEquals(2, lieu.getYCoord(), 0);
    }

    @Test 
    public void setYCoordTest() {
        Lieu lieu = new Lieu(1, 2);
        lieu.setYCoord(1);
        assertEquals(1, lieu.getYCoord(), 0);
    }

}