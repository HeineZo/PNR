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
        Lieu lieu = new Lieu(1, 2);
        Lieu lieu2 = new Lieu(4, 6);
        Date date = new Date(25/05/22);
        EspeceObservee especeObservee = EspeceObservee.LOUTRE;
        //sommet avec constructeur 1
        Sommet sommet1 = new Sommet(1, lieu, date, especeObservee);

        ArrayList<Observateur> o = new ArrayList<Observateur>();
        ObsGCI obsGCI = new ObsGCI(2, new Date(18 / 05 / 22), new Time(10, 19, 0), lieu2, o, ContenuNid.OEUF, 5);
        //sommet avec constructeur 2
        Sommet sommet2 = new Sommet(obsGCI);

        System.out.println(sommet1);
        System.out.println(sommet2);

        System.out.println("\nLa distance entre les deux sommets est: 5 :");
        if (sommet1.calculeDist(sommet2) == 5) {
            System.out.print("OK");
        } else {
            System.out.print("ERREUR");
        }

        Lieu lieu3 = new Lieu(2, 3);
        Sommet sommet3 = new Sommet(3, lieu3, date, especeObservee);

        System.out.println("\nLa distance entre les sommets 1 et 3 est: :"+sommet1.calculeDist(sommet3));
        System.out.println("\nLa distance entre les sommets 2 et 3 est: :"+sommet2.calculeDist(sommet3));


        // Test classe Graphe

        ArrayList<Sommet> sommets = new ArrayList<Sommet>();
        sommets.add(sommet1);
        sommets.add(sommet2);
        sommets.add(sommet3);

        Graphe graphe = new Graphe(sommets, 4);
        //Graphe graphe = new Graphe(g);

        System.out.println("\n\nLe graphe possède "+graphe.nbSommets()+" sommet(s) : ");
        if (graphe.nbSommets() == 3) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        System.out.println("\nLe graphe possède "+graphe.nbAretes()+" arete(s) : ");
        if (graphe.nbAretes() == 2) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        boolean ret = graphe.estDansGraphe(sommet3.getId());
        System.out.println("\nLe sommet 3 est dans le graphe: "+ret+" : ");
        if (ret == true) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        //System.out.println("\nLes sommets voisins sont "+graphe.getSommetsVoisins());

        System.out.println("\nDegré du sommet 2:"+graphe.calculeDegre(sommet2.getId())+" : ");
        if (graphe.calculeDegre(sommet2.getId()) == 1) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }
    }  
}