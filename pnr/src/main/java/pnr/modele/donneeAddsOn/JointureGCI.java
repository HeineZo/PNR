package pnr.modele.donneeAddsOn;

public class JointureGCI {

    private int idNid, nbEnvol, protection, obsG, nombre, presentMainsNonObs, leNid;
    private String nomPlage, raisonArretObservation, bagueMale, bagueFemelle, nature;

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

    public int getIdNid() {
        return this.idNid;
    }

    public String getNomPlage() {
        return this.nomPlage;
    }

    public String getRaisonArretObservation() {
        return this.raisonArretObservation;
    }

    public int getNbEnvol() {
        return this.nbEnvol;
    }

    public int getProtection() {
        return this.protection;
    }

    public String getBagueMale() {
        return this.bagueMale;
    }

    public String getBagueFemelle() {
        return this.bagueFemelle;
    }
}
