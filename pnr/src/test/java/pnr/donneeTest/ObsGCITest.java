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
public class ObsGCITest {

    @Test
    public void constructeurObsGCITest() {
        try {
            ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        } catch (Exception e) {
            fail(e.getMessage());
        } 
    }

    @Test
    public void constructeurIllegalObsGCITest() {
        try {
            ObsGCI obsGCI = new ObsGCI(0, null, null, null, null, null, 0);
            fail("Exception was expected for illegal input");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void getNatureObsTest() {
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        assertEquals(ContenuNid.OEUF, obsGCI.getNatureObs());
    }

    @Test
    public void setNatureObsTest() {
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        obsGCI.setNatureObs(ContenuNid.NID_SEUL);
        assertEquals(ContenuNid.NID_SEUL, obsGCI.getNatureObs());
    }


    @Test
    public void getNombreTest() {
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        assertEquals(5, obsGCI.getNombre());
    }

    @Test
    public void setTypeObsTest() {
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        obsGCI.setNombre(6);
        assertEquals(6, obsGCI.getNombre());
    }

    @Test
    public void especeObsTest() {
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        assertEquals(EspeceObservee.GCI, obsGCI.especeObs());
    } 
}