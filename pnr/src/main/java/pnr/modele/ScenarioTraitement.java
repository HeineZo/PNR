package pnr.modele;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import pnr.modele.donnee.*;
import pnr.modele.traitement.*;

public class ScenarioTraitement {

    public static void main(String[] args) {
        System.out.println("\n**Classe Graphe");
        
        Date date = new Date(25/05/22);
        EspeceObservee especeObservee = EspeceObservee.LOUTRE;
        Sommet sommet1 = new Sommet(1, new Lieu(10, 10), date, especeObservee); //sommet avec constructeur 1

        ObsGCI obsGCI = new ObsGCI(2, new Date(18 / 05 / 22), new Time(10, 19, 0), new Lieu(11, 10), new ArrayList<Observateur>(), ContenuNid.OEUF, 5);
        Sommet sommet2 = new Sommet(obsGCI); //sommet avec constructeur 2

        Sommet sommet3 = new Sommet(3, new Lieu(7, 6), date, especeObservee);
        Sommet sommet4 = new Sommet(4, new Lieu(7, 10), date, especeObservee);
        Sommet sommet5 = new Sommet(5, new Lieu(7, 100), date, especeObservee);
        Sommet sommet6 = new Sommet(6, new Lieu(5, 10), date, especeObservee);
        Sommet sommet7 = new Sommet(7, new Lieu(10, 10), date, especeObservee);

        System.out.print("\nLa distance entre les sommets 1 et 2 est 1 :"+sommet1.calculeDist(sommet2)+" : ");
        if (sommet1.calculeDist(sommet2) == 1) {
            System.out.print("OK");
        } else {
            System.out.print("ERREUR");
        }

        System.out.println("La distance entre les sommets 2 et 4 est:"+sommet2.calculeDist(sommet4));
        System.out.println("La distance entre les sommets 1 et 4 est:"+sommet1.calculeDist(sommet4));
        System.out.println("La distance entre les sommets 3 et 4 est:"+sommet3.calculeDist(sommet4));
        System.out.println("La distance entre les sommets 6 et 4 est:"+sommet6.calculeDist(sommet4));


        System.out.println("\n**Classe Graphe");

        ArrayList<Sommet> sommets = new ArrayList<Sommet>();
        sommets.add(sommet1);
        sommets.add(sommet2);
        sommets.add(sommet3);
        sommets.add(sommet4);
        sommets.add(sommet5);
        sommets.add(sommet6);

        Graphe graphe = new Graphe(sommets, 4);
        //Graphe graphe = new Graphe(g);

        System.out.print("\n\nLe graphe possède 6 sommets : ");
        if (graphe.nbSommets() == 6) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        System.out.print("\nLe graphe possède 5 aretes : ");
        if (graphe.nbAretes() == 5) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        boolean ret1 = graphe.estDansGraphe(7);
        System.out.print("\nLe sommet 7 est dans le graphe: faux : ");
        if (ret1 == false) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        boolean ret2 = graphe.estDansGraphe(5);
        System.out.print("\nLe sommet 5 est dans le graphe: vrai : ");
        if (ret2) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        System.out.print("\nDegré du sommet 3:"+graphe.calculeDegre(sommet3.getId())+" : ");
        if (graphe.calculeDegre(sommet3.getId()) == 1) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        System.out.print("\nDegré du sommet 4:"+graphe.calculeDegre(4)+" : ");
        if (graphe.calculeDegre(4) == 4) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        System.out.print("\nDegrés des sommets du graphe:"+graphe.calculeDegres());

        // System.out.print("\nLes sommets 3 et 4 sont voisins: vrai : ");
        // if (graphe.sontVoisins(3,4)) {
        //     System.out.print("OK\n");
        // } else {
        //     System.out.print("ERREUR\n");
        // }

        // System.out.print("\nLes sommets 2 et 6 sont voisins: false : ");
        // if (graphe.sontVoisins(2,6)) {
        //     System.out.print("OK\n");
        // } else {
        //     System.out.print("ERREUR\n");
        // }

        System.out.print("\nOn peut aller du sommet 2 a 6 : vrai :");
        if (graphe.existeChemin(2,6)) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        System.out.print("\nOn peut aller du sommet 2 a 5 : faux :");
        if (graphe.existeChemin(2,5) == false) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        System.out.print("\nLes voisins du sommet 3 sont:"+graphe.voisins(3));

        System.out.print("\nOn peut ajouter une arrete entre le sommet 1 et 2: vrai : ");
        if (graphe.ajouteArete(1,2)) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        System.out.print("\nOn peut ajouter une arrete entre le sommet 1 et 7: faux : ");
        if (graphe.ajouteArete(1,7) == false) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        // System.out.println("On peut supprimer une arrete entre le sommet 1 et 2: vrai : ");
        // if (graphe.retireArete(1,2)) {
        //     System.out.print("OK\n");
        // } else {
        //     System.out.print("ERREUR\n");
        // }

        // System.out.println("On peut supprimer une arrete entre le sommet 1 et 7: faux : ");
        // if (graphe.retireArete(1,7) == false) {
        //     System.out.print("OK\n");
        // } else {
        //     System.out.print("ERREUR\n");
        // }

        //System.out.println("On crée la matrice d'adjacence du graphe : "+graphe.matriceAdjacence());

        // System.out.print("Le graphe est connexe: faux : ");
        // if (graphe.estConnexe() == false) {
        //     System.out.print("OK\n");
        // } else {
        //     System.out.print("ERREUR\n");
        // }

        System.out.print("La distance du sommet 2 à 6 est 2 : ");
        if (graphe.distAretes(2,6) == 2) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        //System.out.print("Il y a 2 composantes connexes dans le graphe :"+graphe.composanteConnexe());

        // System.out.print("L'excentricité du sommet 6 est -1 : ");
        // if (graphe.excentricite(6) == -1) {
        //     System.out.print("OK\n");
        // } else {
        //     System.out.print("ERREUR\n");
        // }

        // System.out.print("Le rayon du graphe est -1 : ");
        // if (graphe.rayon() == -1) {
        //     System.out.print("OK\n");
        // } else {
        //     System.out.print("ERREUR\n");
        // }

        // System.out.print("Le rayon du graphe est -1 : ");
        // if (graphe.diametre() == -1) {
        //     System.out.print("OK\n");
        // } else {
        //     System.out.print("ERREUR\n");
        // }

        
    }  
}