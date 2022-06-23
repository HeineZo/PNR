package pnr.modele.donneeAddsOn;

/**
 * Loutre class
 */
public class Loutre {
    private int ObsL;
    private String commune, lieuDit, indice;

    /**
     * Loutre constructor
     * @param obs
     * @param commune
     * @param lieuDit
     * @param indice
     */
    public Loutre(int obs, String commune, String lieuDit, String indice) {
        this.ObsL = obs;
        this.commune = commune;
        this.lieuDit = lieuDit;
        this.indice = indice;
    }

    /**
     * This function returns the value of the variable ObsL
     * 
     * @return The value of the variable ObsL.
     */
    public int getObsL() {
        return this.ObsL;
    }

    /**
     * This function returns the commune of the current object
     * 
     * @return The commune.
     */
    public String getCommune() {
        return this.commune;
    }

    /**
     * This function returns the value of the attribute lieuDit
     * 
     * @return The value of the variable lieuDit.
     */
    public String getLieuDit() {
        return this.lieuDit;
    }

    /**
     * This function returns the value of the variable indice
     * 
     * @return The value of the variable indice.
     */
    public String getIndice() {
        return this.indice;
    }
}