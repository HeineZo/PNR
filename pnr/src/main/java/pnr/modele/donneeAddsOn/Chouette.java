package pnr.modele.donneeAddsOn;

/**
 * Chouette class
 */
public class Chouette {
    private String numIndividu, espece, sexe;

    /**
     * Chouette constructor
     * @param num num
     * @param espece species
     * @param sexe sex
     */
    public Chouette(String num, String espece, String sexe) {
        this.numIndividu = num;
        this.espece = espece;
        this.sexe = sexe;
    }

    /**
     * // Java
     * public String getNumIndividu() {
     *         return this.numIndividu;
     *     }
     * 
     * @return The value of the numIndividu variable.
     */
    public String getNumIndividu() {
        return this.numIndividu;
    }

    /**
     * This function returns the value of the attribute espece
     * 
     * @return The value of the variable espece.
     */
    public String getEspece() {
        return this.espece;
    }

    /**
     * This function returns the sexe of the person
     * 
     * @return The sexe of the person.
     */
    public String getSexe() {
        return this.sexe;
    }
}