package pnr.modele.donneeAddsOn;

/**
 * GCI class
 */
public class GCI {
    private int idNid, nbEnvol, protection;
    private String nomPlage, raisonArretObservation, bagueMale, bagueFemelle;

    /**
     * GCI constructor
     * @param id if
     * @param plage beach
     * @param arretObs arretObs
     * @param nb number
     * @param protec protec
     * @param bMale bMale
     * @param bFemelle bFemelle
     */
    public GCI(int id, String plage, String arretObs, int nb, int protec, String bMale, String bFemelle) {
        this.idNid = id;
        this.nomPlage = plage;
        this.raisonArretObservation = arretObs;
        this.nbEnvol = nb;
        this.protection = protec;
        this.bagueMale = bMale;
        this.bagueFemelle = bFemelle;
    }

    /**
     * This function returns the value of the variable idNid
     * 
     * @return The idNid variable is being returned.
     */
    public int getIdNid() {
        return this.idNid;
    }

    /**
     * This function returns the name of the beach
     * 
     * @return The name of the beach.
     */
    public String getNomPlage() {
        return this.nomPlage;
    }

    /**
     * This function returns the value of the variable raisonArretObservation
     * 
     * @return The raisonArretObservation
     */
    public String getRaisonArretObservation() {
        return this.raisonArretObservation;
    }

    /**
     * This function returns the number of flights of the airport
     * 
     * @return The number of flights.
     */
    public int getNbEnvol() {
        return this.nbEnvol;
    }

    /**
     * This function returns the protection of the armor
     * 
     * @return The protection value of the armor.
     */
    public int getProtection() {
        return this.protection;
    }

    /**
     * This function returns the value of the variable bagueMale
     * 
     * @return The value of the variable bagueMale.
     */
    public String getBagueMale() {
        return this.bagueMale;
    }

    /**
     * This function returns the bagueFemelle of the object
     * 
     * @return The value of the bagueFemelle variable.
     */
    public String getBagueFemelle() {
        return this.bagueFemelle;
    }
}