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
public class ObsHippocampeTest {

    @Test
    public void constructeurObsHippocampeTest() {
        try {
            ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), 3.2,
                Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        } catch (Exception e) {
            fail(e.getMessage());
        } 
    }

    @Test
    public void constructeurIllegalObsHippocampeTest() {
        try {
            ObsHippocampe obsHippocampe = new ObsHippocampe(0, null, null, null, null, 0, null, null, null);
            fail("Exception was expected for illegal input");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void getTypePecheTest() {
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), 3.2,
            Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        assertEquals(Peche.CASIER_CREVETTES, obsHippocampe.getTypePeche());
    }

    @Test
    public void setTypePecheTest() {
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), 3.2,
            Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        obsHippocampe.setTypePeche(Peche.CASIER_MORGATES);
        assertEquals(Peche.CASIER_MORGATES, obsHippocampe.getTypePeche());
    }

    @Test
    public void getEspeceTest() {
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), 3.2,
            Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        assertEquals(EspeceHippocampe.SYNGNATHUS_ACUS, obsHippocampe.getEspece());
    }

    @Test
    public void setEspeceTest() {
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), 3.2,
            Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        obsHippocampe.setEspece(EspeceHippocampe.HIPPOCAMPUS_GUTTULATUS);
        assertEquals(EspeceHippocampe.HIPPOCAMPUS_GUTTULATUS, obsHippocampe.getEspece());
    }

    @Test
    public void getSexeTest() {
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), 3.2,
            Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        assertEquals(Sexe.INCONNU, obsHippocampe.getSexe());
    }

    @Test
    public void setSexeTest() {
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), 3.2,
            Peche.CASIER_CREVETTES, EspeceHippocampe.HIPPOCAMPUS_GUTTULATUS, Sexe.MALE);
        obsHippocampe.setSexe(Sexe.INCONNU);
        assertEquals(Sexe.INCONNU, obsHippocampe.getSexe());
    }

    @Test
    public void getTailleTest() {
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), 3.2,
            Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        assertEquals(3.2, obsHippocampe.getTaille(),0);
    }
 
    @Test
    public void setTailleTest() {
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), 3.2,
            Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        obsHippocampe.setTaille(4);
        assertEquals(4, obsHippocampe.getTaille(),0);
    }

    @Test
    public void getEstGestantTest() {
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), 3.2,
            Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        assertEquals(false, obsHippocampe.getEstGestant());
    }

    @Test
    public void setEstGestantTest() {
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), 3.2,
            Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.MALE);
            obsHippocampe.setEstGestant(true);
        assertEquals(true, obsHippocampe.getEstGestant());
    }

    @Test
    public void getEspeceObs() {
        ObsHippocampe obsHippocampe = new ObsHippocampe(3, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(), 3.2,
            Peche.CASIER_CREVETTES, EspeceHippocampe.SYNGNATHUS_ACUS, Sexe.INCONNU);
        assertEquals(EspeceObservee.HIPPOCAMPE, obsHippocampe.especeObs());
    }
}