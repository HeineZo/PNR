package pnr.modele.donneeAddsOn;

public class Hippocampe {

    private int obsH, temperatureEau, gestant;
    private String espece, sexe, typePeche;
    private double taille;

    public Hippocampe(int obs, String espece, String sexe, int tempEau, String peche, double taille, int gestant) {
        this.obsH = obs;
        this.espece = espece;
        this.sexe = sexe;
        this.temperatureEau = tempEau;
        this.typePeche = peche;
        this.taille = taille;
        this.gestant = gestant;
    }

    public int getObsH() {
        return this.obsH;
    }

    public String getEspece() {
        return this.espece;
    }

    public String getSexe() {
        return this.sexe;
    }

    public int getTemperatureEau() {
        return this.temperatureEau;
    }

    public String getTypePeche() {
        return this.typePeche;
    }

    public double getTaille() {
        return this.taille;
    }

    public int getGestant() {
        return this.gestant;
    }
}
