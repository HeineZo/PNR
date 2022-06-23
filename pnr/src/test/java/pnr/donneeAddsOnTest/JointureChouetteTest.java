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
public class JointureChouetteTest {

    @Test
    public void constructeurJointureChouetteTest() {
        try {
            JointureChouette jChouette = new JointureChouette("1", "Cheveche", "Male", 0, "Sonore", 2);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getLeNumTest() {
        JointureChouette jChouette = new JointureChouette("1", "Cheveche", "Male", 0, "Sonore", 2);
        assertEquals("1", jChouette.getLeNumIndividu());
    }

    @Test
    public void getEspeceTest() {
        JointureChouette jChouette = new JointureChouette("1", "Cheveche", "Male", 0, "Sonore", 2);
        assertEquals("Cheveche", jChouette.getEspece());
    }

    @Test
    public void getSexeTest() {
        JointureChouette jChouette = new JointureChouette("1", "Cheveche", "Male", 0, "Sonore", 2);
        assertEquals("Male", jChouette.getSexe());
    }

    @Test
    public void getProtocoleTest() {
        JointureChouette jChouette = new JointureChouette("1", "Cheveche", "Male", 0, "Sonore", 2);
        assertEquals(0, jChouette.getProtocole());
    }

    @Test
    public void getTypeObsTest() {
        JointureChouette jChouette = new JointureChouette("1", "Cheveche", "Male", 0, "Sonore", 2);
        assertEquals("Sonore", jChouette.getTypeObs());
    }
}