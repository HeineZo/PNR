package pnr.modele.donneeAddsOn;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ZoneHumide {

    private SimpleIntegerProperty zh_id, zh_temporaire;
    private SimpleDoubleProperty zh_profondeur, zh_surface;
    private SimpleStringProperty zh_typeMare, zh_pente, zh_ouverture;

    public ZoneHumide(int id, int temp, double prof, double surf, String tMare, String pente, String ouvert) {
        if (tMare != null && pente != null && ouvert != null) {
            this.zh_id = new SimpleIntegerProperty(id);
            this.zh_temporaire = new SimpleIntegerProperty(temp);
            this.zh_profondeur = new SimpleDoubleProperty(prof);
            this.zh_typeMare = new SimpleStringProperty(tMare);
            this.zh_surface = new SimpleDoubleProperty(surf);
            this.zh_pente = new SimpleStringProperty(pente);
            this.zh_ouverture = new SimpleStringProperty(ouvert);
        } else {
            throw new IllegalArgumentException("Erreur - ZoneHumide(Constructeur)");
        }
    }

    public SimpleIntegerProperty getId() {
        return this.zh_id;
    }

    public SimpleIntegerProperty getTemporaire() {
        return this.zh_temporaire;
    }

    public SimpleDoubleProperty getProfondeur() {
        return this.zh_profondeur;
    }

    public SimpleDoubleProperty getSurface() {
        return this.zh_surface;
    }

    public SimpleStringProperty getgetTypeMare() {
        return this.zh_typeMare;
    }

    public SimpleStringProperty getgetPente() {
        return this.zh_pente;
    }

    public SimpleStringProperty getgetOuverture() {
        return this.zh_ouverture;
    }
}
