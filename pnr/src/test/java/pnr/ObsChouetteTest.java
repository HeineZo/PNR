package pnr;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import org.junit.Test;
import pnr.modele.donnee.*;

/**
 * Unit test
 */
public class ObsChouetteTest {

    @Test
    public void constructeurObsChouetteTest() {
        try {
            ObsChouette obsChouette = new ObsChouette(2, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
        } catch (Exception e) {
            fail(e.getMessage());
        } 
    }

    @Test
    public void constructeurIllegalObsChouetteTest() {
        try {
            ObsChouette obsChouette = new ObsChouette(0, null, null, null, null,null);
            fail("Exception was expected for illegal input");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void getTypeObsTest() {
        ObsChouette obsChouette = new ObsChouette(2, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
        assertEquals(TypeObservation.SONORE, obsChouette.getTypeObs());
    }

    @Test
    public void setTypeObsTest() {
        ObsChouette obsChouette = new ObsChouette(2, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
        obsChouette.setTypeObs(TypeObservation.VISUELLE);
        assertEquals(TypeObservation.VISUELLE, obsChouette.getTypeObs());
    }

    @Test
    public void especeObsTest() {
        ObsChouette obsChouette = new ObsChouette(2, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
        assertEquals(EspeceObservee.CHOUETTE, obsChouette.especeObs());
    }
}