package pnr.modele.donneeAddsOn;
import java.util.ArrayList;

public class TableUtilisateur {

    private String pseudo;
    private String nom;
    private String prenom;
    private String mdp;
    private int perm;
    private ArrayList<TableUtilisateur> array;

    public TableUtilisateur(String pseudo, String nom, String prenom, String mdp, int perm) {
        if ((pseudo != null) && (nom != null) && (prenom != null) && (mdp != null) && (perm > -1)) {
            this.pseudo = pseudo;
            this.nom = nom;
            this.prenom = prenom;
            this.mdp = mdp;
            this.perm = perm;
            array = new ArrayList<TableUtilisateur>();
        }
    }

    public ArrayList<TableUtilisateur> getArray() {
        return this.array;
    }

    public String getPseudoName() {
        return this.pseudo;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getMdp() {
        return this.mdp;
    }

    public int getPerm() {
        return this.perm;
    }

}