package pnr.modele.traitement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

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

    public Graphe(HashMap<Sommet, ArrayList<Sommet>> somVoisins) {
        if (somVoisins != null) {
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

    public int nbSommets() {
        return this.sommetsVoisins.size();
    }

    public int nbAretes() {
        int ret = 0;
        for (Sommet s : this.sommetsVoisins.keySet()) {
            ArrayList<Sommet> voisins = this.sommetsVoisins.get(s);
            ret = ret + voisins.size();
        }
        return ret / 2;
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

    public int calculeDegre(int idSom) {
        int ret = -1;
        for (Sommet s : this.sommetsVoisins.keySet()) {
            if (s.getId() == idSom) {
                ArrayList<Sommet> voisins = this.sommetsVoisins.get(s);
                ret = voisins.size();
                break;
            }
        }
        return ret;
    }

    public HashMap<Sommet, Integer> calculeDegres() {
        HashMap<Sommet, Integer> calculeDegres = new HashMap<Sommet, Integer>();
        for (Sommet s : this.sommetsVoisins.keySet()) {
            int degre = this.calculeDegre(s.getId());
            calculeDegres.put(s, degre);
        }
        return calculeDegres;
    }

    public boolean sontVoisins(int idSom1, int idSom2) {
        boolean ret = false;
        for (Sommet s : this.sommetsVoisins.keySet()) {
            if (s.getId() == idSom1) {
                ArrayList<Sommet> voisins = this.sommetsVoisins.get(s);
                int i = 0;
                while (i < voisins.size() && ret == false) {
                    Sommet s2 = voisins.get(i);
                    if (s2.getId() == idSom2) {
                        ret = true;
                    }
                }
                break;
            }
        }
        return ret;
    }

    public boolean existeChemin(int idSom1, int idSom2) {
        boolean ret = false;
        Stack<Sommet> stack = new Stack<Sommet>();
        Sommet current = (Sommet) this.sommetsVoisins.keySet().toArray()[0];
        stack.push(current);
        while (!stack.isEmpty()) {
            current = stack.pop();
            visit(current.value);

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        return ret;
    }

    public ArrayList<Sommet> voisins(int idSom) {
        ArrayList<Sommet> voisins = new ArrayList<Sommet>();
        ;
        for (Sommet s : this.sommetsVoisins.keySet()) {
            if (s.getId() == idSom) {
                voisins = this.sommetsVoisins.get(s);
                break;
            }
        }
        return voisins;
    }

    public boolean ajouteArete(int idSom1, int idSom2) {
        boolean ret = false;
        if ((this.estDansGraphe(idSom1) == true && this.estDansGraphe(idSom2) == true)) {
            ArrayList<Sommet> voisins1 = this.voisins(idSom1);
            ArrayList<Sommet> voisins2 = this.voisins(idSom2);
            for (Sommet s : this.sommetsVoisins.keySet()) {
                if (s.getId() == idSom1) {
                    voisins2.add(s);
                }
                if (s.getId() == idSom2) {
                    voisins1.add(s);
                }
            }
            ret = true;
        }
        return ret;
    }

    public boolean retireArete(int idSom1, int idSom2) {
        boolean ret = false;
        if ((this.estDansGraphe(idSom1) == true && this.estDansGraphe(idSom2) == true)) {
            if (this.sontVoisins(idSom1, idSom2) == true) {
                ArrayList<Sommet> voisins1 = this.voisins(idSom1);
                ArrayList<Sommet> voisins2 = this.voisins(idSom2);
                for (Sommet s : this.sommetsVoisins.keySet()) {
                    if (s.getId() == idSom1) {
                        voisins2.remove(s);
                    }
                    if (s.getId() == idSom2) {
                        voisins1.remove(s);
                    }
                }
                ret = true;
            }
        }
        return ret;
    }

    public int[][] matriceAdjacence() {
        int[][] ret = new int[this.nbSommets()][this.nbSommets() + 1];
        for (Sommet s : this.sommetsVoisins.keySet()) {
            for (int i = 0; i < this.nbSommets() - 1; i++) {
                ret[i][0] = s.getId();
            }
        }
        for (Sommet s : this.sommetsVoisins.keySet()) {
            int id = s.getId();
            ArrayList<Sommet> voisins = this.voisins(id);
            for (Sommet v : voisins) {
                int idVoisin = v.getId();
                ret[id][idVoisin] = 1;
            }
        }
        return ret;
    }

    public boolean estConnexe(){
        boolean ret = false;
        Sommet[] lesSommets = (Sommet[]) this.sommetsVoisins.keySet().toArray();
        int i = 0;
        boolean voisinsChemin = true;
        while((i < lesSommets.length) && voisinsChemin != false){
            voisinsChemin = false;
            int j = 0;
            while((j < lesSommets.length) && voisinsChemin != false){
                voisinsChemin = false;
                if(j != i){
                    if ((this.sontVoisins(lesSommets[i].getId(),lesSommets[j].getId()) == true) || (existeChemin(lesSommets[i].getId(),lesSommets[j].getId()) == true)){
                        voisinsChemin = true;
                    }
                } else {
                    voisinsChemin = true;
                }
                j++;
            }
            i++;
        }
        if (voisinsChemin == true){
            ret = true;
        }
        return ret;
    }

    public int distArrete(int idSom1, int idSom2) {
        int ret;

        if (estDansGraphe(idSom1) && estDansGraphe(idSom2)) {
            if (existeChemin(idSom1, idSom2)) {

            } else {
                ret = 0;
                System.err.println("Il n'existe pas de chemin entre ces deux arrêtes");
            }
        } else {
            ret = -1;
            System.err.println("Les sommets ne sont pas dans le graphe");
        }
    }

    public int excentricite(int idSom) {
        int ret;

        if (estConnexe()) {
            ret = distArrete(this, idSom);
        } else {
            ret = -1;
            System.err.println("Le graphe n'est pas connexe");
        }

        return ret;
    }

    public int diametre() {
        int ret;

        if (estConnexe()) {
            int maxExc = 0;

            for (Sommet s : this.sommetsVoisins.keySet()) {
                for (Sommet sTarget : this.getSommetsVoisins()) {
                    if (s.excentricite(sTarget) != -1 && s.excentricite(sTarget) > maxExc) {
                        maxExc = s.excentriciteDist(sTarget);
                    }
                }
            }

            ret = minExc;
        } else {
            ret = -1;
            System.err.println("Le graphe n'est pas connexe");
        }

        return ret;
    }

    public int rayon() {
        int ret;

        if (estConnexe()) {
            minExc = 999999;

            for (Key s : this.sommetsVoisins.keySet()) {
                for (Sommet sTarget : this.getSommetsVoisins()) {
                    if (s.excentricite(sTarget) != -1 && s.excentricite(sTarget) < minExc) {
                        minExc = s.excentriciteDist(sTarget);
                    }
                }
            }

            ret = minExc;
        } else {
            ret = -1;
            System.err.println("Le graphe n'est pas connexe");
        }

        return ret;
    }
}
