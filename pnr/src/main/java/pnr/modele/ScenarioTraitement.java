package pnr.modele;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import pnr.modele.donnee.*;
import pnr.modele.traitement.*;

public class ScenarioTraitement {

    public static void main(String[] args) {
        System.out.println("\n**Classe Sommet");

        HashMap<Sommet,ArrayList<Sommet>> hashMap = new HashMap<Sommet,ArrayList<Sommet>>();

        Date date = new Date(25/05/22);
        EspeceObservee especeObservee = EspeceObservee.LOUTRE;
        Lieu lieu = new Lieu(10,5);
        Sommet sommet1 = new Sommet(1,lieu,date,especeObservee);
        Sommet sommet2 = new Sommet(2,lieu,date,especeObservee);
        Sommet sommet3 = new Sommet(3,lieu,date,especeObservee);
        Sommet sommet4 = new Sommet(4,lieu,date,especeObservee);
        Sommet sommet6 = new Sommet(5,lieu,date,especeObservee);
        Sommet sommet7 = new Sommet(6,lieu,date,especeObservee);

        ArrayList<Sommet> sommets1Voisins = new ArrayList<Sommet>();
        ArrayList<Sommet> sommets2Voisins = new ArrayList<Sommet>();
        ArrayList<Sommet> sommets3Voisins = new ArrayList<Sommet>();
        ArrayList<Sommet> sommets4Voisins = new ArrayList<Sommet>();
        ArrayList<Sommet> sommets5Voisins = new ArrayList<Sommet>();
        ArrayList<Sommet> sommets6Voisins = new ArrayList<Sommet>();

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

        System.out.print("\nLa distance entre les sommets 1 et 2 est 1 :"+sommet1.calculeDist(sommet2)+" : ");
        if (sommet1.calculeDist(sommet2) == 1) {
            System.out.print("\nOK");
        } else {
            System.out.print("\nERREUR");
        }

        System.out.println("La distance entre les sommets 2 et 4 est:"+sommet2.calculeDist(sommet4));
        System.out.println("La distance entre les sommets 1 et 4 est:"+sommet1.calculeDist(sommet4));
        System.out.println("La distance entre les sommets 3 et 4 est:"+sommet3.calculeDist(sommet4));
        System.out.println("La distance entre les sommets 6 et 4 est:"+sommet6.calculeDist(sommet4));

        System.out.println("\n**Classe Graphe");

        Graphe graphe = new Graphe(hashMap);

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

        //System.out.print("\nDegrés des sommets du graphe:"+graphe.calculeDegres());

        System.out.print("\nLes sommets 3 et 4 sont voisins: vrai : ");
        if (graphe.sontVoisins(3,4)) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        System.out.print("\nLes sommets 2 et 6 sont voisins: false : ");
        if (graphe.sontVoisins(2,6) == false) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        // System.out.print("\nOn peut aller du sommet 2 a 6 : vrai :");
        // if (graphe.existeChemin(2,6)) {
        //     System.out.print("OK\n");
        // } else {
        //     System.out.print("ERREUR\n");
        // }

        // System.out.print("\nOn peut aller du sommet 2 a 5 : faux :");
        // if (graphe.existeChemin(2,5) == false) {
        //     System.out.print("OK\n");
        // } else {
        //     System.out.print("ERREUR\n");
        // }

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

        System.out.print("\nOn peut supprimer une arrete entre le sommet 1 et 2: vrai : ");
        if (graphe.retireArete(1,2)) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        System.out.print("\nOn peut supprimer une arrete entre le sommet 1 et 7: faux : ");
        if (graphe.retireArete(1,7) == false) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        int [][] matrice = graphe.matriceAdjacence();
        System.out.println("\nOn crée la matrice d'adjacence du graphe : ");
        for (int[] tab: matrice) {
            for (int s: tab) {
                System.out.print(s + "\t");
            }
            System.out.println("\n");
        }

        // System.out.print("Le graphe est connexe: faux : ");
        // if (graphe.estConnexe() == false) {
        //     System.out.print("OK\n");
        // } else {
        //     System.out.print("ERREUR\n");
        // }

        System.out.print("\nLa distance du sommet 2 à 6 est 2 : ");
        if (graphe.distAretes(2,6) == 2) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        //System.out.print("Il y a 2 composantes connexes dans le graphe :"+graphe.composanteConnexe());

        System.out.print("\nL'excentricité du sommet 6 est -1 : ");
        if (graphe.excentricite(6) == -1) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        System.out.print("\nLe rayon du graphe est -1 : ");
        if (graphe.rayon() == -1) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }

        System.out.print("\nLe diametre du graphe est -1 : ");
        if (graphe.diametre() == -1) {
            System.out.print("OK\n");
        } else {
            System.out.print("ERREUR\n");
        }
    }  
}