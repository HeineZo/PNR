package pnr.modele.donneeAddsOn;

/**
 * JointureChouette class
 */
public class JointureChouette {
    private int protocole, numObs;
    private String typeObs, lenumIndividu, numIndividu, espece, sexe;;

    /**
     * JointureChouette constructor
     * @param leNum leNum
     * @param espece species
     * @param sexe sex
     * @param proto proto
     * @param type type
     * @param num num
     */
    public JointureChouette(String leNum, String espece, String sexe, int proto, String type, int num) {
        this.lenumIndividu =  leNum;
        this.protocole = proto;
        this.typeObs = type;
        this.numObs = num;
        this.espece = espece;
        this.sexe = sexe;
    }

    /**
     * This function returns the value of the numIndividu attribute
     * 
     * @return The numIndividu variable is being returned.
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

    /**
     * This function returns the value of the protocole attribute
     * 
     * @return The protocole of the current object.
     */
    public int getProtocole() {
        return this.protocole; 
    }

    /**
     * This function returns the type of observation
     * 
     * @return The type of observation.
     */
    public String getTypeObs() {
        return this.typeObs;
    }

    /**
     * This function returns the value of the variable lenumIndividu
     * 
     * @return The lenumIndividu is being returned.
     */
    public String getLeNumIndividu() {
        return this.lenumIndividu;
    }

    /**
     * This function returns the number of observations in the data set
     * 
     * @return The number of observations.
     */
    public int getNumObs() {
        return this.numObs;
    }
}