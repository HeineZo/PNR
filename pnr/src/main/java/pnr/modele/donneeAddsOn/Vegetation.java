package pnr.modele.donneeAddsOn;

/**
 * Vegetation class
 */
public class Vegetation {
    private int idVege, decrit_LieuVege;
    private String natureVege, vegetation;

    /**
     * Vegetation constructor
     * @param id id
     * @param nature nature
     * @param vege vegetation
     * @param lieu venue
     */
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

    /**
     * This function returns the id of the vegetable
     * 
     * @return The idVege is being returned.
     */
    public int getId() {
        return this.idVege;
    }

    /**
     * This function returns the natureVege attribute of the object
     * 
     * @return The natureVege variable is being returned.
     */
    public String getNatureVege() {
        return this.natureVege;
    }

    /**
     * This function returns the vegetation of the current object
     * 
     * @return Vegetation
     */
    public String getVegetation() {
        return this.vegetation;
    }

    /**
     * This function returns the value of the attribute decrit_LieuVege
     * 
     * @return The decrit_LieuVege variable is being returned.
     */
    public int getDecritLieuVege() {
        return this.decrit_LieuVege;
    }
}