package pnr.traitementTest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import org.junit.Test;

import pnr.modele.traitement.*;
import pnr.modele.donnee.*;


/**
 * Unit test
 */
public class SommetTest {


    @Test
    public void testConstructeursSommet() {
        try {
            Sommet sommet1 = new Sommet(1, new Lieu(1,2),new Date(21/06/22),EspeceObservee.LOUTRE);
            ObsLoutre obs = new ObsLoutre(1, new Date(21/06/22), new Time(21,12,05), new Lieu(4,6), new ArrayList<Observateur>(), IndiceLoutre.NEGATIF);
            Sommet sommet2 = new Sommet(obs);
          } catch (Exception e) {
            fail(e.getMessage());
          }
    }

    @Test
    public void calculeDistanceTest()  {
        Sommet sommet1 = new Sommet(1, new Lieu(1,2),new Date(21/06/22),EspeceObservee.LOUTRE);
        Sommet sommet2 = new Sommet(2, new Lieu(4,6),new Date(22/06/22),EspeceObservee.GCI);
        assertEquals(5, sommet1.calculeDist(sommet2), 0);
     
    }
}