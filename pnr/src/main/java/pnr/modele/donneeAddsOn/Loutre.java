package pnr.modele.donneeAddsOn;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Loutre {

    private SimpleIntegerProperty ObsL;
    private SimpleStringProperty commune, lieuDit, indice;

    public Loutre(int obs, String commune, String lieuDit, String indice) {
        this.ObsL = new SimpleIntegerProperty(obs);
        this.commune = new SimpleStringProperty(commune);
        this.lieuDit = new SimpleStringProperty(lieuDit);
        this.indice = new SimpleStringProperty(indice);
    }

    public SimpleIntegerProperty getObsL() {
        return this.ObsL;
    }

    public SimpleStringProperty getCommune() {
        return this.commune;
    }

    public SimpleStringProperty getLieuDit() {
        return this.lieuDit;
    }

    public SimpleStringProperty getIndice() {
        return this.indice;
    }
}
