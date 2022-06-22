package pnr.modele.donneeAddsOn;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vegetation {

    private SimpleIntegerProperty idVege, decrit_LieuVege;
    private SimpleStringProperty natureVege, vegetation;

    public Vegetation(int id, String nature, String vege, int lieu) {
        if (nature != null && vege != null) {
            this.idVege = new SimpleIntegerProperty(id);
            this.natureVege = new SimpleStringProperty(nature);
            this.vegetation = new SimpleStringProperty(vege);
            this.decrit_LieuVege = new SimpleIntegerProperty(lieu);
        } else {
            throw new IllegalArgumentException("Erreur - Vegetation(Constructeur)");
        }
    }

    public SimpleIntegerProperty getId() {
        return this.idVege;
    }

    public SimpleStringProperty getNatureVege() {
        return this.natureVege;
    }

    public SimpleStringProperty getVegetation() {
        return this.vegetation;
    }

    public SimpleIntegerProperty getDecritLieuVege() {
        return this.decrit_LieuVege;
    }
}
