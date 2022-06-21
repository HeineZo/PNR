package pnr;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import pnr.modele.traitement.*;
import pnr.modele.donnee.*;

/**
 * Unit test
 */
public class GrapheTest {

    HashMap<Sommet,ArrayList<Sommet>> hashMap = new HashMap<Sommet,ArrayList<Sommet>>();
    Date date = new Date(25/05/22);
    EspeceObservee especeObservee = EspeceObservee.LOUTRE;
    Lieu lieu = new Lieu(10,5);
    Sommet sommet1 = new Sommet(1,lieu,date,especeObservee);
    Sommet sommet2 = new Sommet(2,lieu,date,especeObservee);
    Sommet sommet3 = new Sommet(3,lieu,date,especeObservee);
    Sommet sommet4 = new Sommet(4,lieu,date,especeObservee); 
    Sommet sommet5 = new Sommet(5,lieu,date,especeObservee);
    Sommet sommet6 = new Sommet(5,lieu,date,especeObservee);
    Sommet sommet7 = new Sommet(6,lieu,date,especeObservee);
    ArrayList<Sommet> sommets1Voisins = new ArrayList<Sommet>();
    ArrayList<Sommet> sommets2Voisins = new ArrayList<Sommet>();
    ArrayList<Sommet> sommets3Voisins = new ArrayList<Sommet>();
    ArrayList<Sommet> sommets4Voisins = new ArrayList<Sommet>();
    ArrayList<Sommet> sommets5Voisins = new ArrayList<Sommet>();
    ArrayList<Sommet> sommets6Voisins = new ArrayList<Sommet>();

    public void init() {
        sommets1Voisins.add(sommet2);
        sommets1Voisins.add(sommet4);
        sommets2Voisins.add(sommet1);
        sommets2Voisins.add(sommet4);
        sommets4Voisins.add(sommet1);
        sommets4Voisins.add(sommet2);
        sommets4Voisins.add(sommet3);
        sommets4Voisins.add(sommet7);
        sommets3Voisins.add(sommet4);
        sommets6Voisins.add(sommet4);
        hashMap.put(sommet1,sommets1Voisins);
        hashMap.put(sommet2,sommets2Voisins);
        hashMap.put(sommet3,sommets3Voisins);
        hashMap.put(sommet4,sommets4Voisins);
        hashMap.put(sommet6,sommets5Voisins);
        hashMap.put(sommet7,sommets6Voisins);
    }

    @Test
    public void getSommetsVoisinsTest() {
        sommets1Voisins.add(sommet1);
        hashMap.put(sommet1, sommets1Voisins);
        Graphe g = new Graphe(hashMap);
        assertEquals(hashMap, g.getSommetsVoisins());
    }

    @Test
    public void getNbSommetsTest() {
        sommets1Voisins.add(sommet1);
        hashMap.put(sommet1, sommets1Voisins);
        Graphe g = new Graphe(hashMap);
        assertEquals(1, g.nbSommets());
    }

    @Test
    public void nbArreteTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(5, g.nbAretes());
    }

    @Test
    public void estDansGrapheTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(true, g.estDansGraphe(3));
        assertEquals(false, g.estDansGraphe(7));
    }

    @Test
    public void calculeDegreTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(1, g.calculeDegre(3));
        assertEquals(4, g.calculeDegre(4));
    }

    @Test
    public void existeCheminTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(true, g.existeChemin(2,6));
        assertEquals(false, g.existeChemin(2,5));   
    }

    @Test
    public void sontVoisinsTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(false, g.sontVoisins(2,6));
        assertEquals(true, g.existeChemin(3,4));   
    }

    @Test
    public void voisinsTest() {
        init();
        Graphe g = new Graphe(hashMap);
        ArrayList<Sommet> s = new ArrayList<Sommet>();
        s.add(sommet4);
        assertEquals(s, g.voisins(3));  
    }

    @Test
    public void ajouteAreteTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(false, g.ajouteArete(1,7));
        assertEquals(true, g.ajouteArete(1,2));   
    }

    @Test
    public void retireAreteTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(false, g.retireArete(1,7));
        assertEquals(true, g.retireArete(1,2));   
    }

    @Test
    public void estConnexeTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(false, g.estConnexe());   
    }

    @Test
    public void composantesConnexeTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(2, g.composanteConnexe().size());   
    }

    @Test
    public void distanceAretesTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(2, g.distAretes(2,6));   
    }

    @Test
    public void excentriciteTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(-1, g.excentricite(1));   
    }

    @Test
    public void rayonTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(-1, g.rayon());   
    }

    @Test
    public void diametreTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(-1, g.diametre());   
    }

    @Test
    public void getSommetTest() {
        init();
        Graphe g = new Graphe(hashMap);
        assertEquals(sommet1, g.getSommet(1));   
    }
}