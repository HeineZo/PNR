package pnr.modele.donneeAddsOn;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GCI {

    private SimpleIntegerProperty idNid, nbEnvol, protection;
    private SimpleStringProperty nomPlage, raisonArretObservation, bagueMale, bagueFemelle;

    public GCI(int id, String plage, String arretObs, int nb, int protec, String bMale, String bFemelle) {
        this.idNid = new SimpleIntegerProperty(id);
        this.nomPlage = new SimpleStringProperty(plage);
        this.raisonArretObservation = new SimpleStringProperty(arretObs);
        this.nbEnvol = new SimpleIntegerProperty(nb);
        this.protection = new SimpleIntegerProperty(protec);
        this.bagueMale = new SimpleStringProperty(bMale);
        this.bagueFemelle = new SimpleStringProperty(bFemelle);
    }

    public SimpleIntegerProperty getIdNid() {
        return this.idNid;
    }

    public SimpleStringProperty getNomPlage() {
        return this.nomPlage;
    }

    public SimpleStringProperty getRaisonArretObservation() {
        return this.raisonArretObservation;
    }

    public SimpleIntegerProperty getNbEnvol() {
        return this.nbEnvol;
    }

    public SimpleIntegerProperty getProtection() {
        return this.protection;
    }

    public SimpleStringProperty getBagueMale() {
        return this.bagueMale;
    }

    public SimpleStringProperty getBagueFemelle() {
        return this.bagueFemelle;
    }
}
