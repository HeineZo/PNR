package pnr.modele.donneeAddsOn;

/**
 * ObsChouette class
 */
public class ObsChouette {
    private int protocole, numObs;
    private String typeObs, lenumIndividu;

    /**
     * ObsChouette constructor
     * @param proto proto
     * @param type type
     * @param leNum leNum
     * @param num number
     */
    public ObsChouette(int proto, String type, String leNum, int num) {
        this.protocole = proto;
        this.typeObs = type;
        this.lenumIndividu =  leNum;
        this.numObs = num;
    }

    /**
     * This function returns the protocol of the packet
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
     * This function returns the value of the lenumIndividu field
     * 
     * @return The lenumIndividu field is being returned.
     */
    public String getLenumIndividu() {
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