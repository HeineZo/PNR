package pnr.modele.donneeAddsOn;

/**
 * Hippocampe class
 */
public class Hippocampe {
    private int obsH, temperatureEau, gestant;
    private String espece, sexe, typePeche;
    private double taille;

    /**
     * Hippocampe constructor
     * @param obs obs
     * @param espece species
     * @param sexe sex
     * @param tempEau water temperature
     * @param peche sin
     * @param taille size
     * @param gestant pregnant
     */
    public Hippocampe(int obs, String espece, String sexe, int tempEau, String peche, double taille, int gestant) {
        this.obsH = obs;
        this.espece = espece;
        this.sexe = sexe;
        this.temperatureEau = tempEau;
        this.typePeche = peche;
        this.taille = taille;
        this.gestant = gestant;
    }

    /**
     * This function returns the height of the obstacle
     * 
     * @return The height of the obstacle.
     */
    public int getObsH() {
        return this.obsH;
    }

    /**
     * This function returns the value of the attribute espece
     * 
     * @return The value of the variable espece.
     */
    public String getEspece() {
        return this.espece;
    }

    /**
     * This function returns the sexe of the person
     * 
     * @return The sexe of the person.
     */
    public String getSexe() {
        return this.sexe;
    }

    /**
     * This function returns the temperature of the water
     * 
     * @return The temperature of the water.
     */
    public int getTemperatureEau() {
        return this.temperatureEau;
    }

    /**
     * This function returns the type of fishing
     * 
     * @return The type of fishing.
     */
    public String getTypePeche() {
        return this.typePeche;
    }

    /**
     * This function returns the value of the attribute taille
     * 
     * @return The value of the variable taille.
     */
    public double getTaille() {
        return this.taille;
    }

    /**
     * This function returns the value of the variable gestant
     * 
     * @return The gestant value.
     */
    public int getGestant() {
        return this.gestant;
    }
}