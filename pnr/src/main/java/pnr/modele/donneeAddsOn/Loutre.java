package pnr.modele.donneeAddsOn;

public class Loutre {

    private int ObsL;
    private String commune, lieuDit, indice;

    public Loutre(int obs, String commune, String lieuDit, String indice) {
        this.ObsL = obs;
        this.commune = commune;
        this.lieuDit = lieuDit;
        this.indice = indice;
    }

    public int getObsL() {
        return this.ObsL;
    }

    public String getCommune() {
        return this.commune;
    }

    public String getLieuDit() {
        return this.lieuDit;
    }

    public String getIndice() {
        return this.indice;
    }
}
