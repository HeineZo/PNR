package pnr.modele.donneeAddsOn;

public class ObsGCI {

    private int obsG, nombre, presentMainsNonObs, leNid;
    private String nature;

    public ObsGCI(int obs, String nature, int nb, int present, int nid) {
        this.obsG = obs;
        this.nature = nature;
        this.nombre = nb;
        this.presentMainsNonObs = present;
        this.leNid = nid;
    }

    public int getObsG() {
        return this.obsG;
    }

    public String getNature() {
        return this.nature;
    }

    public int getNombre() {
        return this.nombre;
    }

    public int getPresentmainsNonObs() {
        return this.presentMainsNonObs;
    }

    public int getLeNid() {
        return this.leNid;
    }
}
