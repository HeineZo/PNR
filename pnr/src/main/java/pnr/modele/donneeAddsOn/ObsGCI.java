package pnr.modele.donneeAddsOn;

/**
 * ObsGCI class
 */
public class ObsGCI {
    private int obsG, nombre, presentMainsNonObs, leNid;
    private String nature;

    /**
     * ObsGCI constructor
     * @param obs obs
     * @param nature nature
     * @param nb number
     * @param present present
     * @param nid nid
     */
    public ObsGCI(int obs, String nature, int nb, int present, int nid) {
        this.obsG = obs;
        this.nature = nature;
        this.nombre = nb;
        this.presentMainsNonObs = present;
        this.leNid = nid;
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
}