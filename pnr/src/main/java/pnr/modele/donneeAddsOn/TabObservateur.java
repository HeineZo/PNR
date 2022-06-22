package pnr.modele.donneeAddsOn;
import java.util.ArrayList;

public class TabObservateur {

    private int id;
    private String nom;
    private String prenom;
    private ArrayList<TabObservateur> array;

    public TabObservateur(int id, String nom, String prenom) {
        if ((id >= 0) && (nom != null) && (prenom != null)) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;;
            array = new ArrayList<TabObservateur>();
        }
    }

    public int getId() {
        return this.id;
    }

    public ArrayList<TabObservateur> getArray() {
        return this.array;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }
}