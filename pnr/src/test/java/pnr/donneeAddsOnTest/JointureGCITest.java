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
public class JointureGCITest {

    @Test
    public void constructeurJointureGCITest() {
        try {
            JointureGCI jGCI = new JointureGCI(1, "conleau", "maree", 4, 0, "bm", "bf", 2, "Nid", 1, 0);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void getIdTest() {
        JointureGCI jGCI = new JointureGCI(1, "conleau", "maree", 4, 0, "bm", "bf", 2, "Nid", 1, 0);
        assertEquals(1, jGCI.getIdNid());
    }

    @Test
    public void getPlageTest() {
        JointureGCI jGCI = new JointureGCI(1, "conleau", "maree", 4, 0, "bm", "bf", 2, "Nid", 1, 0);
        assertEquals("conleau", jGCI.getNomPlage());
    }

    @Test
    public void getArretTest() {
        JointureGCI jGCI = new JointureGCI(1, "conleau", "maree", 4, 0, "bm", "bf", 2, "Nid", 1, 0);
        assertEquals("maree", jGCI.getRaisonArretObservation());
    }

    @Test
    public void getNbEnvolTest() {
        JointureGCI jGCI = new JointureGCI(1, "conleau", "maree", 4, 0, "bm", "bf", 2, "Nid", 1, 0);
        assertEquals(4, jGCI.getNbEnvol());
    }

    @Test
    public void getProtectionTest() {
        JointureGCI jGCI = new JointureGCI(1, "conleau", "maree", 4, 0, "bm", "bf", 2, "Nid", 1, 0);
        assertEquals(0, jGCI.getProtection());
    }

    @Test
    public void getBMaleTest() {
        JointureGCI jGCI = new JointureGCI(1, "conleau", "maree", 4, 0, "bm", "bf", 2, "Nid", 1, 0);
        assertEquals("bm", jGCI.getBagueMale());
    }

    @Test
    public void getBFemelleTest() {
        JointureGCI jGCI = new JointureGCI(1, "conleau", "maree", 4, 0, "bm", "bf", 2, "Nid", 1, 0);
        assertEquals("bf", jGCI.getBagueFemelle());
    }

    @Test
    public void getObsTest() {
        JointureGCI jGCI = new JointureGCI(1, "conleau", "maree", 4, 0, "bm", "bf", 2, "Nid", 1, 0);
        assertEquals(2, jGCI.getObsG());
    }

    @Test
    public void getNatureTest() {
        JointureGCI jGCI = new JointureGCI(1, "conleau", "maree", 4, 0, "bm", "bf", 2, "Nid", 1, 0);
        assertEquals("Nid", jGCI.getNature());
    }

    @Test
    public void getNombreTest() {
        JointureGCI jGCI = new JointureGCI(1, "conleau", "maree", 4, 0, "bm", "bf", 2, "Nid", 1, 0);
        assertEquals(1, jGCI.getNombre());
    }

    @Test
    public void getPresentTest() {
        JointureGCI jGCI = new JointureGCI(1, "conleau", "maree", 4, 0, "bm", "bf", 2, "Nid", 1, 0);
        assertEquals(0, jGCI.getPresentmainsNonObs());
    }
}