package pnr.modele.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Creates and manages new observations
 */
public class ObservationFactory {
    private final int id ;
    private final Date date ;
    private final String nom ;
    private final String prenom ;
    private final String pseudonyme ;
    
    /**
     * ObservationFactory consctructor with parameters id, date
     * @param id id
     * @param date date
     */
    public ObservationFactory(int id, Date date) {
        this.id = id ;
        this.date = date ;
        this.nom = "";
        this.prenom = "";
        this.pseudonyme = "";
    }

    /**
     * ObservationFactory consctructor with parameters nom, prenom, pseudo
     * @param nom name
     * @param prenom firtname
     * @param pseudonyme pseudo
     */
    public ObservationFactory(String nom, String prenom, String pseudonyme) {
        this.id = -1;
        this.date = null ;
        this.nom = nom ;
        this.prenom = prenom;
        this.pseudonyme = pseudonyme;
    }
    
    /**
     * It returns a string representation of the date in the format dd/MM/yyyy
     * 
     * @return The date in the format dd/MM/yyyy
     */
    public String getDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
    
    /**
     * This function returns the id of the object as a string.
     * 
     * @return The id of the object.
     */
    public String getId() {
        return String.valueOf(id) ;
    }

    /**
     * This function returns the pseudonyme of the user
     * 
     * @return The pseudonyme of the user.
     */
    public String getPseudonyme() {
        return pseudonyme;
    }

    /**
     * This function returns a string that is either the name of the patient, or the date of the
     * observation if the name is not available
     * 
     * @return The name and surname of the patient.
     */
    public String toString() {
        String ret;
        if (this.nom.equals("")){
            if (this.date != null){
                ret = "Observation du "+this.getDate();
            } else {
                ret =  "Date indisponible";
            }
        } else {
            ret = this.nom+" "+this.prenom;}
        return ret;
    }
}

