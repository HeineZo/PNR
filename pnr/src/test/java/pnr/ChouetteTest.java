package pnr;

import static org.junit.Assert.*;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;

import org.junit.Test;
import pnr.modele.donnee.*;

/**
 * Unit test
 */
public class ChouetteTest {

    @Test 
    public void constructeurChouetteTest() {
        try {
          Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
        } catch (Exception e) {
          fail(e.getMessage());
        }
    }

    @Test 
    public void constructeurIllegalChouetteTest() {
        try {
          Chouette chouette = new Chouette(null, null, null);
          fail("Exception was expected for null input");
        } catch (IllegalArgumentException e) {          
        }
    }

    @Test
    public void getIdChouetteTest() {
      Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
      assertEquals("1", chouette.getIdChouette());
    }

    @Test
    public void setIdChouetteTest() {
      Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
      chouette.setIdChouette("2");
      assertEquals("2", chouette.getIdChouette());
    }

    @Test
    public void getEspeceChouetteTest() {
      Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
      assertEquals(EspeceChouette.CHEVECHE, chouette.getEspece());
    }

    @Test
    public void setEspeceChouetteTest() {
      Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
      chouette.setEspece(EspeceChouette.HULOTTE);
      assertEquals(EspeceChouette.HULOTTE, chouette.getEspece());
    }

    @Test
    public void getSexeChouetteTest() {
      Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
      assertEquals(Sexe.FEMELLE, chouette.getSexe());
    }

    @Test
    public void setSexeChouetteTest() {
      Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
      chouette.setSexe(Sexe.MALE);
      assertEquals(Sexe.MALE, chouette.getSexe());
    }

    @Test
    public void getLesObservationsTest() {
      Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
      ObsChouette obsChouette = new ObsChouette(2, new Date(18/06/22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
      assertEquals(new ArrayList<Observateur>(), chouette.getLesObservations());
    }

    @Test
    public void setLesObservationsTest() {
      Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
      ObsChouette obsChouette = new ObsChouette(2, new Date(18 / 06 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
      ArrayList<ObsChouette> o = new ArrayList<ObsChouette>();
      ObsChouette obsChouette2 = new ObsChouette(1, new Date(18 / 06 / 22), new Time(12, 19, 0), new Lieu(1, 1), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
      o.add(obsChouette2);
      chouette.setLesObservations(o);
      assertEquals(o, chouette.getLesObservations());
    }

    @Test
    public void ajouteUneObsTest() {
      Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
      ObsChouette obsChouette = new ObsChouette(2, new Date(18 / 06 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
      ArrayList<ObsChouette> o = new ArrayList<ObsChouette>();
      chouette.ajouteUneObs(obsChouette);
      o.add(obsChouette);
      assertEquals(o, chouette.getLesObservations());
    }

    @Test
    public void ajoutePlsObsTest() {
      Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
      ObsChouette obsChouette = new ObsChouette(2, new Date(18 / 06 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
      ObsChouette obsChouette2 = new ObsChouette(1, new Date(18 / 06 / 22), new Time(12, 19, 0), new Lieu(1, 1), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
      ArrayList<ObsChouette> o = new ArrayList<ObsChouette>();
      o.add(obsChouette);
      o.add(obsChouette2);
      chouette.ajoutePlsObs(o);
      assertEquals(o, chouette.getLesObservations());
    }

    @Test
    public void videObsTest() {
      Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
      ObsChouette obsChouette = new ObsChouette(2, new Date(18 / 06 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
      ObsChouette obsChouette2 = new ObsChouette(1, new Date(18 / 06 / 22), new Time(12, 19, 0), new Lieu(1, 1), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
      ArrayList<ObsChouette> o = new ArrayList<ObsChouette>();
      o.add(obsChouette);
      o.add(obsChouette2);
      chouette.ajoutePlsObs(o);
      chouette.videObs();
      assertEquals(new ArrayList<ObsChouette>(), chouette.getLesObservations());
    }

    @Test
    public void retireObsTest() {
      Chouette chouette = new Chouette("1", Sexe.FEMELLE, EspeceChouette.CHEVECHE);
      ObsChouette obsChouette = new ObsChouette(2, new Date(18 / 06 / 22), new Time(10, 19, 0), new Lieu(0.1, 1.0), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
      ObsChouette obsChouette2 = new ObsChouette(1, new Date(18 / 06 / 22), new Time(12, 19, 0), new Lieu(1, 1), new ArrayList<Observateur>(),
                TypeObservation.SONORE);
      ArrayList<ObsChouette> o = new ArrayList<ObsChouette>();
      o.add(obsChouette);
      o.add(obsChouette2); 
      chouette.ajoutePlsObs(o);
      assertEquals(true, chouette.retireObs(1));
    }
} 

