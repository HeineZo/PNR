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
public class NidGCITest {

    @Test
    public void constructeurNidGCITest() {
        try {
            NidGCI nidGCI = new NidGCI(1, "Conleau");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void constructeurIllegalNidGCITest() {
        try {
            NidGCI nidGCI = new NidGCI(-1, null);
            fail("Exception was expected for illegal input");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void getNomPlageTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        assertEquals("Conleau", nidGCI.getNomPlage());
    }

    @Test
    public void setNomPlageTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        nidGCI.setNomPlage("Penvins");
        assertEquals("Penvins", nidGCI.getNomPlage());
    }

    @Test
    public void getIdNidTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        assertEquals(1, nidGCI.getIdNid(), 0);
    }

    @Test
    public void setIdNidTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        nidGCI.setIdNid(2);
        assertEquals(2, nidGCI.getIdNid(), 0);
    }

    @Test
    public void getLesObservationsTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        assertEquals(new ArrayList<Observateur>(), nidGCI.getLesObservations());
    }

    @Test
    public void setLesObservationsTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        ArrayList<ObsGCI> o = new ArrayList<ObsGCI>();
        ObsGCI obsGCI2 = new ObsGCI(1, new Date(18 / 06 / 22), new Time(10, 19, 0), new Lieu(1, 1.5),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 6);
        o.add(obsGCI2);
        nidGCI.setLesObservations(o);
        assertEquals(o, nidGCI.getLesObservations());
    }

    @Test
    public void getNbEnvolTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        assertEquals(0, nidGCI.getNbEnvol());
    }

    @Test
    public void setNbEnvolTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        nidGCI.setNbEnvol(6);
        assertEquals(6, nidGCI.getNbEnvol());
    }

    @Test
    public void getDateDebutObsTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        ObsGCI obsGCI2 = new ObsGCI(1, new Date(18 / 06 / 22), new Time(10, 19, 0), new Lieu(1, 1.5),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 6);
        ArrayList<ObsGCI> o = new ArrayList<ObsGCI>();
        o.add(obsGCI);
        o.add(obsGCI2);
        nidGCI.ajoutePlsObs(o);
        assertEquals(new Date(18 / 05 / 22), nidGCI.dateDebutObs());
    }

    @Test
    public void getDateFinObsTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        ObsGCI obsGCI2 = new ObsGCI(1, new Date(18 / 06 / 22), new Time(10, 19, 0), new Lieu(1, 1.5),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 6);
        ArrayList<ObsGCI> o = new ArrayList<ObsGCI>();
        o.add(obsGCI);
        o.add(obsGCI2);
        nidGCI.ajoutePlsObs(o);
        assertEquals(new Date(18 / 06 / 22), nidGCI.dateFinObs());
    }

    @Test
    public void ajouteUneObsTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        ArrayList<ObsGCI> o = new ArrayList<ObsGCI>();
        nidGCI.ajouteUneObs(obsGCI);
        o.add(obsGCI);
        assertEquals(o, nidGCI.getLesObservations());
    }

    @Test
    public void ajoutePlsObsTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        ObsGCI obsGCI2 = new ObsGCI(1, new Date(18 / 06 / 22), new Time(10, 19, 0), new Lieu(1, 1.5),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 6);
        ArrayList<ObsGCI> o = new ArrayList<ObsGCI>();
        o.add(obsGCI);
        o.add(obsGCI2);
        nidGCI.ajoutePlsObs(o);
        assertEquals(o, nidGCI.getLesObservations());
    }

    @Test
    public void videObsTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        ObsGCI obsGCI2 = new ObsGCI(1, new Date(18 / 06 / 22), new Time(10, 19, 0), new Lieu(1, 1.5),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 6);
        ArrayList<ObsGCI> o = new ArrayList<ObsGCI>();
        o.add(obsGCI);
        o.add(obsGCI2);
        nidGCI.ajoutePlsObs(o);
        nidGCI.videObs();
        assertEquals(new ArrayList<ObsGCI>(), nidGCI.getLesObservations());
    }

    @Test
    public void retireObsTest() {
        NidGCI nidGCI = new NidGCI(1, "Conleau");
        ObsGCI obsGCI = new ObsGCI(1, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        ObsGCI obsGCI2 = new ObsGCI(1, new Date(18 / 06 / 22), new Time(10, 19, 0), new Lieu(1, 1.5),
                new ArrayList<Observateur>(), ContenuNid.OEUF, 6);
        ArrayList<ObsGCI> o = new ArrayList<ObsGCI>();
        o.add(obsGCI);
        o.add(obsGCI2);
        nidGCI.ajoutePlsObs(o);
        assertEquals(true, nidGCI.retireObs(1));
    }
}
