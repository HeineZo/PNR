package pnr.modele.traitement;
import java.sql.Date;
import pnr.modele.donnee.*;

/**
 * Class that represents a sommet
 */
public class Sommet {

    /**
     * Identifier of the observation
     */
    private int id;

    /**
     * Location of the observation
     */
    private Lieu coordLieu;

    /**
     * Date of the observation
     */
    private Date date;

    /**
     * Species studied during the observation
     */
    private EspeceObservee espece;

    /**
     * First constructor the class, it represents a simplificated representation of an observation based on defined parameters
     * @param id identifier of the observation
     * @param coord coordinates of the location of the observation
     * @param date date of the observation
     * @param espece the species studied during the observation
     */
    public Sommet(int id, Lieu coord, Date date, EspeceObservee espece) {
        if ((id > 0)&&(coord != null)&&(date != null)&&(espece != null)) {
            this.id = id;
            this.coordLieu = coord;
            this.date = date;
            this.espece = espece;
        } else {
            System.out.println("Sommet(): Erreur de parametres");
        }
    }

    /**
     * Second constructor of the class, represents a simplificated version of an observation based on an observation
     * @param obs the observation to extract data from
     */
    public Sommet(Observation obs) {
        if (obs != null) {
            this.id = obs.getIdObs();
            this.coordLieu = obs.getLieuObs();
            this.date = obs.getDateObs();
            this.espece = obs.especeObs();
        } else  {
            System.out.println("Sommet(): l'observation ne doit pas etre nulle");
        }
    }

    /**
     * Calculates the distance between the current Sommet object and the Sommet object in parameters
     * @param som the Sommet object to calculate the distance from
     * @return the distance calculated 
     */
    public double calculeDist(Sommet som) {
        double ret = 0.0;
        if (som != null) {
            double curCoordX = this.coordLieu.getXCoord();
            double curCoordY = this.coordLieu.getYCoord();
            double somCoordX =  som.getCoordLieu().getXCoord();
            double somCoordY =  som.getCoordLieu().getYCoord();

            ret = Math.sqrt(Math.pow((curCoordX - somCoordX),2) + Math.pow((curCoordY - somCoordY),2));
        }
        return ret;
    }

    /**
     * Returns the current id of the current Sommet object's observation
     * @return the current id 
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the current coordinates of the current Sommet object's observation
     * @return the current cooredinates 
     */
    public Lieu getCoordLieu() {
        return this.coordLieu;
    }

    /**
     * Returns the current date of the current Sommet object's observation
     * @return the current date 
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Returns the current species studied of the current Sommet object's observation
     * @return the current species studied 
     */
    public EspeceObservee getEspece() {
        return this.espece;
    }

    /**
     * Creates a String that displays the current Sommet object's observation'
     * @return the String chain created
     */
    public String toString() {
        String ret = "\nIdentifiant de l'observation du sommet : "+ this.getId();
        ret = ret + "\nLieu de l'identifiant du sommet : "+ this.getCoordLieu();
        ret = ret + "\nDate de l'observation du sommet : "+ this.getDate();
        ret = ret + "\nEspece de l'observation du sommet : "+ this.getEspece();
        return ret;
    }
}