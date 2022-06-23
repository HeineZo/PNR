package pnr.modele.donneeAddsOn;

/**
 * JointureGCI class
 */
public class JointureGCI {
    private int idNid, nbEnvol, protection, obsG, nombre, presentMainsNonObs, leNid;
    private String nomPlage, raisonArretObservation, bagueMale, bagueFemelle, nature;

    /**
     * JointureGCI constructor
     * @param id id
     * @param plage beach
     * @param arretObs arretObs
     * @param nb number
     * @param protec protec
     * @param bMale bMale
     * @param bFemelle bFemelle
     * @param obs obs
     * @param nature nature
     * @param nombre number
     * @param present present
     */
    public JointureGCI(int id, String plage, String arretObs, int nb, int protec, String bMale, String bFemelle,
            int obs, String nature, int nombre, int present) {
        this.idNid = id;
        this.nomPlage = plage;
        this.raisonArretObservation = arretObs;
        this.nbEnvol = nb;
        this.protection = protec;
        this.bagueMale = bMale;
        this.bagueFemelle = bFemelle;
        this.obsG = obs;
        this.nature = nature;
        this.nombre = nombre;
        this.presentMainsNonObs = present;
    }

    /**
     * This function returns the value of the variable obsG
     * 
     * @return The value of the variable obsG.
     */
    public int getObsG() {
        return this.obsG;
    }

    /**
     * This function returns the nature of the Pokemon
     * 
     * @return The nature of the animal.
     */
    public String getNature() {
        return this.nature;
    }

    /**
     * This function returns the value of the variable nombre
     * 
     * @return The value of the variable nombre.
     */
    public int getNombre() {
        return this.nombre;
    }

    /**
     * This function returns the number of present mains non obs
     * 
     * @return The value of the variable presentMainsNonObs.
     */
    public int getPresentmainsNonObs() {
        return this.presentMainsNonObs;
    }

    /**
     * This function returns the value of the variable leNid
     * 
     * @return The value of the variable leNid.
     */
    public int getLeNid() {
        return this.leNid;
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
     * This function returns the bagueFemelle of the current object
     * 
     * @return The value of the bagueFemelle variable.
     */
    public String getBagueFemelle() {
        return this.bagueFemelle;
    }
}