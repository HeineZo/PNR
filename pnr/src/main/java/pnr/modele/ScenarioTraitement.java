package pnr.modele;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import pnr.modele.donnee.*;
import pnr.modele.traitement.*;

public class ScenarioTraitement {

    public static void main(String[] args) {
        // Test classe Sommet
        Lieu lieu = new Lieu(10.10, 20.20);
        Lieu lieu2 = new Lieu(20.10, 10.20);
        Date date = new Date(25/05/22);
        EspeceObservee especeObservee = EspeceObservee.LOUTRE;
        Sommet sommet = new Sommet(1, lieu, date, especeObservee);

        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsGCI obsGCI = new ObsGCI(2, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu2, o, ContenuNid.OEUF, 5);
        Sommet sommet2 = new Sommet(obsGCI);

        System.out.println(sommet);
        System.out.println(sommet2);

        System.out.println("\nLa distance entre les deux sommets est:"+sommet.calculeDist(sommet2));

        // Test classe Graphe

        ArrayList<Sommet> sommets = new ArrayList<Sommet>();
        sommets.add(sommet);
        sommets.add(sommet2);

        Graphe graphe = new Graphe(sommets, 30);
        //Graphe graphe = new Graphe(g);
        System.out.println();
        System.out.println(graphe);
        System.out.println("\nLe graphe possède "+graphe.nbSommets()+" sommet(s)");
        System.out.println("\nLe graphe possède "+graphe.nbAretes()+" arete(s)");

        Lieu lieu3 = new Lieu(220.10, 110.20);
        Sommet sommet3 = new Sommet(3, lieu3, date, especeObservee);
        boolean ret = graphe.estDansGraphe(sommet3.getId());
        System.out.println("\nLe sommet 3 est dans le graphe: "+ret);

        System.out.println("\nLes sommets voisins sont "+graphe.getSommetsVoisins());
        System.out.println("\nDegré du sommet 2:"+graphe.calculeDegre(sommet2.getId()));

        HashMap<Sommet, ArrayList<Sommet>> map = new HashMap<Sommet, ArrayList<Sommet>>();
        Graphe graphe2 = new Graphe(map);
        System.out.println();
        System.out.println(graphe2);


    }  
}