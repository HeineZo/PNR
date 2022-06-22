package pnr.modele.donneeAddsOn;

public class Vegetation {

    private int idVege, decrit_LieuVege;
    private String natureVege, vegetation;

    public Vegetation(int id, String nature, String vege, int lieu) {
        if (nature != null && vege != null) {
            this.idVege = id;
            this.natureVege = nature;
            this.vegetation = vege;
            this.decrit_LieuVege = lieu;
        } else {
            throw new IllegalArgumentException("Erreur - Vegetation(Constructeur)");
        }
    }

    public int getId() {
        return this.idVege;
    }

    public String getNatureVege() {
        return this.natureVege;
    }

    public String getVegetation() {
        return this.vegetation;
    }

    public int getDecritLieuVege() {
        return this.decrit_LieuVege;
    }
}
