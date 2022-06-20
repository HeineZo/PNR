package pnr.modele.donneeAddsOn;

public class ZoneHumide {

    private int zh_id, zh_temporaire;
    private double zh_profondeur, zh_surface;
    private String zh_typeMare, zh_pente, zh_ouverture;

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

    public int getId() {
        return this.zh_id;
    }

    public int getTemporaire() {
        return this.zh_temporaire;
    }

    public double getProfondeur() {
        return this.zh_profondeur;
    }

    public double getSurface() {
        return this.zh_surface;
    }

    public String getTypeMare() {
        return this.zh_typeMare;
    }

    public String getPente() {
        return this.zh_pente;
    }

    public String getOuverture() {
        return this.zh_ouverture;
    }
}
