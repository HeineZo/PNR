package pnr.modele.donneeAddsOn;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Hippocampe {

    private SimpleIntegerProperty obsH, temperatureEau, gestant;
    private SimpleStringProperty espece, sexe, typePeche;
    private SimpleDoubleProperty taille;

    public Hippocampe(int obs, String espece, String sexe, int tempEau, String peche, double taille, int gestant) {
        this.obsH = new SimpleIntegerProperty(obs);
        this.espece = new SimpleStringProperty(espece);
        this.temperatureEau = new SimpleIntegerProperty(tempEau);
        this.typePeche = new SimpleStringProperty(peche);
        this.taille = new SimpleDoubleProperty(taille);
        this.gestant = new SimpleIntegerProperty(gestant);
    }

    public SimpleIntegerProperty getObsH() {
        return this.obsH;
    }

    public SimpleStringProperty getEspece() {
        return this.espece;
    }

    public SimpleStringProperty getSexe() {
        return this.sexe;
    }

    public SimpleIntegerProperty getTemperatureEau() {
        return this.temperatureEau;
    }

    public SimpleStringProperty getTypePeche() {
        return this.typePeche;
    }

    public SimpleDoubleProperty getTaille() {
        return this.taille;
    }

    public SimpleIntegerProperty getGestant() {
        return this.gestant;
    }
}
