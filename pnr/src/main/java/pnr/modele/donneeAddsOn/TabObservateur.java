package pnr.modele.donneeAddsOn;
import java.util.ArrayList;

/**
 * TabObservateur class
 */
public class TabObservateur {
    private int id;
    private String nom;
    private String prenom;
    private ArrayList<TabObservateur> array;

    /**
     * TabObservateur constructor
     * @param id id
     * @param nom name
     * @param prenom firtname
     */
    public TabObservateur(int id, String nom, String prenom) {
        if ((id >= 0) && (nom != null) && (prenom != null)) {
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;;
            array = new ArrayList<TabObservateur>();
        }
    }

    /**
     * This function returns the id of the current object
     * 
     * @return The id of the object.
     */
    public int getId() {
        return this.id;
    }

    /**
     * This function returns the array of the class
     * 
     * @return The arraylist of TabObservateur
     */
    public ArrayList<TabObservateur> getArray() {
        return this.array;
    }

    /**
     * This function returns the name of the person
     * 
     * @return The name of the person.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * This function returns the value of the variable prenom
     * 
     * @return The prenom of the person.
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * The toString() method returns the string representation of the object
     * 
     * @return The method returns the first name and last name of the person.
     */
    public String toString() {
        return getPrenom() + " " + getNom();
    }
}