package pnr.modele.donneeAddsOn;

public class GCI {

    private int idNid, nbEnvol, protection;
    private String nomPlage, raisonArretObservation, bagueMale, bagueFemelle;

    public GCI(int id, String plage, String arretObs, int nb, int protec, String bMale, String bFemelle) {
        this.idNid = id;
        this.nomPlage = plage;
        this.raisonArretObservation = arretObs;
        this.nbEnvol = nb;
        this.protection = protec;
        this.bagueMale = bMale;
        this.bagueFemelle = bFemelle;
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
