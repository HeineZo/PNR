package pnr.modele.donneeAddsOn;

/**
 * ZoneHumide class
 */
public class ZoneHumide {
    private int zh_id, zh_temporaire;
    private double zh_profondeur, zh_surface;
    private String zh_typeMare, zh_pente, zh_ouverture;

    /**
     * ZoneHumide constructor
     * @param id id
     * @param temp temperature
     * @param prof depth
     * @param surf surface
     * @param tMare pond temperature
     * @param pente slope
     * @param ouvert opening
     */
    public ZoneHumide(int id, int temp, double prof, double surf, String tMare, String pente, String ouvert) {
        if (tMare != null && pente != null && ouvert != null) {
            this.zh_id = id;
            this.zh_temporaire = temp;
            this.zh_profondeur = prof;
            this.zh_typeMare = tMare;
            this.zh_surface = surf;
            this.zh_pente = pente;
            this.zh_ouverture = ouvert;
        } else {
            throw new IllegalArgumentException("Erreur - ZoneHumide(Constructeur)");
        }
    }

    /**
     * This function returns the id of the object
     * 
     * @return The id of the object.
     */
    public int getId() {
        return this.zh_id;
    }

    /**
     * It returns the value of the variable zh_temporaire.
     * 
     * @return The value of the zh_temporaire variable.
     */
    public int getTemporaire() {
        return this.zh_temporaire;
    }

    /**
     * This function returns the depth of the object
     * 
     * @return The value of the variable zh_profondeur.
     */
    public double getProfondeur() {
        return this.zh_profondeur;
    }

    /**
     * This function returns the surface of the house
     * 
     * @return The surface of the house.
     */
    public double getSurface() {
        return this.zh_surface;
    }

    /**
     * This function returns the type of mare
     * 
     * @return The type of mare.
     */
    public String getgetTypeMare() {
        return this.zh_typeMare;
    }

    /**
     * This function returns the value of the variable zh_pente
     * 
     * @return The value of the variable zh_pente.
     */
    public String getgetPente() {
        return this.zh_pente;
    }

    /**
     * This function returns the opening hours of the restaurant
     * 
     * @return The value of the variable zh_ouverture.
     */
    public String getgetOuverture() {
        return this.zh_ouverture;
    }
}
