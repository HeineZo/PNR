package pnr.modele.traitement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * It's a class that represents a graph
 */
public class Graphe {

    private HashMap<Sommet, ArrayList<Sommet>> sommetsVoisins;

    /**
     * Creating a graph with a list of vertices and a distance.
     * 
     * @param sommets The list of vertices
     * @param dist    The distance between each pair of vertices
     */
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

    /**
     * A constructor for the Graphe class. It takes a HashMap as a parameter and
     * assigns it to the sommetsVoisins field.
     * 
     * @param sommetsVoisins a HashMap that contains the neighbors of each vertex
     */
    public Graphe(HashMap<Sommet, ArrayList<Sommet>> somVoisins) {
        if (somVoisins != null) {
            this.sommetsVoisins = somVoisins;
        } else {
            throw new IllegalArgumentException("Erreur - Graphe(HashMap) : paramètres incorrects");
        }
    }

    /**
     * Creating a copy constructor for the Graphe class.
     * 
     * @param graphe The graph to copy.
     */
    public Graphe(Graphe g) {
        if (g != null) {
            new Graphe(g.getSommetsVoisins());
        }
    }

    /**
     * It returns a HashMap of vertices and their neighbors
     * 
     * @return A HashMap of Sommets and their neighbors.
     */
    public HashMap<Sommet, ArrayList<Sommet>> getSommetsVoisins() {
        return this.sommetsVoisins;
    }

    /**
     * It returns the number of vertices in the graph
     * 
     * @return The number of vertices in the graph.
     */
    public int nbSommets() {
        return this.sommetsVoisins.size();
    }

    /**
     * > We count the number of edges by counting the number of neighbors of each
     * vertex and dividing
     * by 2
     * 
     * @return The number of edges in the graph.
     */
    public int nbAretes() {
        int ret = 0;
        for (Sommet s : this.sommetsVoisins.keySet()) {
            ArrayList<Sommet> voisins = this.sommetsVoisins.get(s);
            ret = ret + voisins.size();
        }
        return ret / 2;
    }

    /**
     * It returns true if the vertex with the given id is in the graph, false
     * otherwise
     * 
     * @param idSom the id of the vertex to check
     * @return A boolean value.
     */
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

    /**
     * It returns the degree of a vertex
     * 
     * @param idSom the id of the vertex
     * @return The degree of the vertex with the given id.
     */
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

    /**
     * It calculates the degree of each vertex in the graph
     * 
     * @return A HashMap of Sommet and Integer.
     */
    public HashMap<Sommet, Integer> calculeDegres() {
        HashMap<Sommet, Integer> calculeDegres = new HashMap<Sommet, Integer>();
        for (Sommet s : this.sommetsVoisins.keySet()) {
            int degre = this.calculeDegre(s.getId());
            calculeDegres.put(s, degre);
        }
        return calculeDegres;
    }

    /**
     * It returns true if the two vertices are neighbors, false otherwise
     * 
     * @param idSom1 the id of the first vertex
     * @param idSom2 the id of the second vertex
     * @return A boolean value.
     */
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

    /**
     * It returns true if there is a path between two vertices, false otherwise
     * 
     * @param idSom1 the id of the first vertex
     * @param idSom2 the id of the second vertex
     */
    public boolean existeChemin(int idSom1, int idSom2) {
        boolean ret = false;
        Stack<Sommet> stack = new Stack<Sommet>();
        Sommet current = (Sommet) this.sommetsVoisins.keySet().toArray()[0];
        stack.push(current);
        while (!stack.isEmpty()) {
            current = stack.pop();
            if (current.getId() == idSom1) {
                ret = true;
                break;
            }
            ArrayList<Sommet> voisins = this.sommetsVoisins.get(current);
            for (Sommet s : voisins) {
                stack.push(s);
            }
        }
        return ret;
    }

    /**
     * It returns the list of neighbors of a given vertex
     * 
     * @param idSom the id of the vertex you want to get the neighbors of
     * @return The neighbors of the vertex with the id idSom.
     */
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

    /**
     * It adds an edge between two vertices if they are both in the graph
     * 
     * @param idSom1 the id of the first vertex
     * @param idSom2 the id of the second vertex
     */
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

    /**
     * It removes an edge between two vertices
     * 
     * @param idSom1 the id of the first vertex
     * @param idSom2 the id of the second vertex
     * @return A boolean
     */
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

    /**
     * It creates a matrix of size `nbSommets()` x `nbSommets() + 1` and fills it
     * with the adjacency
     * information of the graph
     * 
     * @return A matrix of the graph.
     */
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

    /**
     * > The function returns true if the graph is connected, false otherwise
     * 
     * @return A boolean value.
     */
    public boolean estConnexe() {
        boolean ret = false;
        Sommet[] lesSommets = (Sommet[]) this.sommetsVoisins.keySet().toArray();
        int i = 0;
        boolean voisinsChemin = true;
        while ((i < lesSommets.length) && voisinsChemin != false) {
            voisinsChemin = false;
            int j = 0;
            while ((j < lesSommets.length) && voisinsChemin != false) {
                voisinsChemin = false;
                if (j != i) {
                    if ((this.sontVoisins(lesSommets[i].getId(), lesSommets[j].getId()) == true)
                            || (existeChemin(lesSommets[i].getId(), lesSommets[j].getId()) == true)) {
                        voisinsChemin = true;
                    }
                } else {
                    voisinsChemin = true;
                }
                j++;
            }
            i++;
        }
        if (voisinsChemin == true) {
            ret = true;
        }
        return ret;
    }

    /**
     * It returns the number of edges between two vertices
     * 
     * @param idSom1 the id of the first vertex
     * @param idSom2 the id of the second vertex
     * @return The distance between two nodes.
     */
    public int distAretes(int idSom1, int idSom2) {
        int ret = -1;

        if (!existeChemin(idSom1, idSom2)) {
            System.out.println("Il n'ya pas de chemin reliant les deux sommet");
        } else {

            ArrayList<Sommet> file = new ArrayList<Sommet>();
            ArrayList<Sommet> traiter = new ArrayList<Sommet>();
            ArrayList<Integer> tailleDesVoisin = new ArrayList<Integer>();
            ArrayList<Sommet> lSommets = new ArrayList<Sommet>(sommetsVoisins.keySet());

            boolean trouver = false;
            ret = 0;

            Sommet sommet1 = getSommet(idSom1);
            Sommet sommet2 = getSommet(idSom2);
            file.add(sommet1);

            while (file.size() > 0 && !trouver) {
                if (file.contains(sommet2)) {
                    trouver = true;
                } else {
                    Sommet sommetATraiter = file.remove(0);
                    traiter.add(sommetATraiter);
                    ArrayList<Sommet> lesVoisin = voisins(sommetATraiter.getId());
                    lesVoisin.removeIf(traiter::contains);
                    lesVoisin.removeIf(file::contains);
                    file.addAll(lesVoisin);
                    if (lesVoisin.size() != 0) {
                        ret += 1;
                    }
                }
            }
        }
        return ret;
    }

    /**
     * It returns the vertex with the given id
     * 
     * @param idSom the id of the vertex we want to get
     * @return The method returns the Sommet object with the id idSom.
     */
    public Sommet getSommet(int idSom) {
        Sommet sommet = null;
        for (Sommet s : this.sommetsVoisins.keySet()) {
            if (s.getId() == idSom) {
                sommet = s;
            }
        }
        return sommet;
    }

    /**
     * It returns the maximum distance between two vertices in the graph
     * 
     * @param idSom the id of the vertex
     * @return The distance between the two vertices.
     */
    public int excentricite(int idSom) {
        int ret = -1;
        if (!estConnexe()) {
            System.err.println("Graphe pas connexe");
        } else {
            ArrayList<Sommet> lSommets = new ArrayList<Sommet>(sommetsVoisins.keySet());
            Sommet sommet1 = getSommet(idSom);
            Sommet sommet2;
            for (int i = 0; i < lSommets.size(); i++) {
                sommet2 = lSommets.get(i);
                int distanceArrete = distAretes(sommet1.getId(), sommet2.getId());
                if (distanceArrete != -1 && distanceArrete > ret) {
                    ret = distanceArrete;
                }
            }
        }
        return ret;
    }

    /**
     * It returns the maximum distance between any two vertices in the graph
     * 
     * @return The diameter of the graph.
     */
    public int diametre() {
        int max = 0;
        if (!estConnexe()) {
            max = -1;
        } else {
            for (Sommet sommet : this.sommetsVoisins.keySet()) {
                if (excentricite(sommet.getId()) > max) {
                    max = excentricite(sommet.getId());
                }
            }
        }
        return max;
    }

    /**
     * It returns the minimum eccentricity of all the vertices in the graph
     * 
     * @return The minimum distance between two vertices in the graph.
     */
    public int rayon() {
        int min = diametre();
        if (!estConnexe()) {
            min = -1;
        } else {
            for (Sommet sommet : this.sommetsVoisins.keySet()) {
                if (excentricite(sommet.getId()) < min) {
                    min = excentricite(sommet.getId());
                }
            }
        }
        return min;
    }
}
