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
public class ObsBatracienTest {

    @Test
    public void constructeurObsBatracienTest() {
        try {
            int[] res = new int[4];
            res[0] = 12;
            res[1] = 6;
            res[2] = 21;
            res[3] = 3;
            ObsBatracien obsBatracien = new ObsBatracien(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), res,
                EspeceBatracien.CALAMITE);
        } catch (Exception e) {
            fail(e.getMessage());
        } 
    }

    @Test
    public void constructeurIllegalObsBatracienTest() {
        try {
            ObsBatracien obsBatracien = new ObsBatracien(0, null, null, null, null, null, null);
            fail("Exception was expected for illegal input");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void getNombreAdulteTest() {
        int[] res = new int[4];
        res[0] = 12;
        res[1] = 6;
        res[2] = 21;
        res[3] = 3;
        ObsBatracien obsBatracien = new ObsBatracien(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), res,
            EspeceBatracien.CALAMITE);
        assertEquals(12, obsBatracien.getNombreAdultes());
    }

    @Test
    public void getNombreAmplexusTest() {
        int[] res = new int[4];
        res[0] = 12;
        res[1] = 6;
        res[2] = 21;
        res[3] = 3;
        ObsBatracien obsBatracien = new ObsBatracien(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), res,
            EspeceBatracien.CALAMITE);
        assertEquals(6, obsBatracien.getNombreAmplexus());
    }

    @Test
    public void getNombreTetardTest() {
        int[] res = new int[4];
        res[0] = 12;
        res[1] = 6;
        res[2] = 21;
        res[3] = 3;
        ObsBatracien obsBatracien = new ObsBatracien(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), res,
            EspeceBatracien.CALAMITE);
        assertEquals(21, obsBatracien.getNombreTetard());
    }

    @Test
    public void getNombrePonteTest() {
        int[] res = new int[4];
        res[0] = 12;
        res[1] = 6;
        res[2] = 21;
        res[3] = 3;
        ObsBatracien obsBatracien = new ObsBatracien(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), res,
            EspeceBatracien.CALAMITE);
        assertEquals(3, obsBatracien.getNombrePonte());
    }

    @Test
    public void especeObserveeTest() {
        int[] res = new int[4];
        res[0] = 12;
        res[1] = 6;
        res[2] = 21;
        res[3] = 3;
        ObsBatracien obsBatracien = new ObsBatracien(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), res,
            EspeceBatracien.CALAMITE);
        assertEquals(EspeceObservee.BATRACIEN, obsBatracien.especeObs());
    }

}