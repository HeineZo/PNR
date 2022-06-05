package pnr.modele.traitement;

import java.util.ArrayList;
import java.util.HashMap;

public class Graphe {

    private HashMap<Sommet, ArrayList<Sommet>> sommetsVoisins;

    public Graphe(ArrayList<Sommet> sommets, double dist) {
        this.sommetsVoisins = new HashMap<Sommet, ArrayList<Sommet>>();
        if (sommets != null && dist > 0) {
            for (Sommet s : sommets) {
                ArrayList<Sommet> som = new ArrayList<Sommet>();
                for (Sommet sTarget : sommets) {
                    if (s.calculeDist(sTarget) >= dist) {
                        som.add(sTarget);
                    }
                }
                this.sommetsVoisins.put(s, som);
            }
        } else {
            throw new IllegalArgumentException("Erreur - paramètre sommet invalide");
        }
    }

    public Graphe(HashMap<Sommet, ArrayList<Sommet>> somVoisins){
        if(somVoisins != null){
            this.sommetsVoisins = somVoisins;
        } else {
            throw new IllegalArgumentException("Erreur - Graphe(HashMap) : paramètres incorrects");
        }
    }

    public Graphe(Graphe g) {
        if (g != null) {
            new Graphe(g.getSommetsVoisins());
        }
    }

    public HashMap<Sommet, ArrayList<Sommet>> getSommetsVoisins() {
        return this.sommetsVoisins;
    }

    public int nbSommets(){
        return this.sommetsVoisins.size();
    }

    public int nbAretes(){
        int ret = 0;
        for (Sommet s : this.sommetsVoisins.keySet()) {
            ArrayList <Sommet> voisins = this.sommetsVoisins.get(s);
            ret = ret + voisins.size();
        }
        return ret/2;
    }

    public boolean estDansGraphe(int idSom) {
        boolean ret = false;

        for (Sommet s : this.sommetsVoisins.keySet()) {
                if (s.getId() == idSom) {
                    ret = true;
                    break;
                }
        } 
        return ret;
    }

    public int calculeDegre(int idSom){
        int ret = -1;
        for (Sommet s : this.sommetsVoisins.keySet()) {
            if (s.getId() == idSom) {
                ArrayList <Sommet> voisins = this.sommetsVoisins.get(s);
                ret = voisins.size();
                break;
            }
        }
        return ret;
    }

    public HashMap<Sommet,Integer> calculeDegres(){
        HashMap <Sommet,Integer> calculeDegres = new HashMap<Sommet,Integer>();
        for (Sommet s : this.sommetsVoisins.keySet()) {
            int degre = this.calculeDegre(s.getId());
            calculeDegres.put(s, degre);
        }
        return calculeDegres;
    }

    public boolean sontVoisins(int idSom1, int idSom2){
        boolean ret = false;
        for (Sommet s : this.sommetsVoisins.keySet()) {
            if (s.getId() == idSom1) {
                ArrayList <Sommet> voisins = this.sommetsVoisins.get(s);
                int i = 0;
                while(i < voisins.size() && ret == false) {
                    Sommet s2 = voisins.get(i);
                    if (s2.getId() == idSom2){
                        ret = true;
                    }
                }
                break;
            }
        }
        return ret;
    }

    public boolean existeChemin(int idSom1, int idSom2){
        boolean ret = false;
        return ret;
    }

    public ArrayList<Sommet> voisins(int idSom){
        ArrayList <Sommet> voisins = new ArrayList<Sommet>();;
        for (Sommet s : this.sommetsVoisins.keySet()) {
            if (s.getId() == idSom) {
                voisins = this.sommetsVoisins.get(s);
                break;
            }
        } 
        return voisins;  
    }

    
}

