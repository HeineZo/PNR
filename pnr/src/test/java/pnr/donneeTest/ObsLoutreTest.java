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
public class ObsLoutreTest {

    @Test
    public void constructeurObsLoutreTest() {
        try {
            ObsLoutre obsLoutre = new ObsLoutre(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                IndiceLoutre.POSITIF);
        } catch (Exception e) {
            fail(e.getMessage());
        } 
    }

    @Test
    public void constructeurIllegalObsLoutreTest() {
        try {
            ObsLoutre obsLoutre = new ObsLoutre(0, null, null, null, null, null);
            fail("Exception was expected for illegal input");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void getIndiceTest() {
        ObsLoutre obsLoutre = new ObsLoutre(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                IndiceLoutre.POSITIF);
        assertEquals(IndiceLoutre.POSITIF, obsLoutre.getIndice());
    }

    @Test
    public void setIndiceTest() {
        ObsLoutre obsLoutre = new ObsLoutre(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                IndiceLoutre.POSITIF);
        obsLoutre.setIndice(IndiceLoutre.NEGATIF);
        assertEquals(IndiceLoutre.NEGATIF, obsLoutre.getIndice());
    }

    @Test
    public void getEspeceObs() {
        ObsLoutre obsLoutre = new ObsLoutre(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                IndiceLoutre.POSITIF);
        assertEquals(EspeceObservee.LOUTRE, obsLoutre.especeObs());
    }
}