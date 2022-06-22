package pnr.modele.donneeAddsOn;

import javafx.beans.property.SimpleStringProperty;

public class Chouette {

    private SimpleStringProperty numIndividu, espece, sexe;

    public Chouette(String num, String espece, String sexe) {
        this.numIndividu = new SimpleStringProperty(num);
        this.espece = new SimpleStringProperty(espece);
        this.sexe = new SimpleStringProperty(sexe);
    }

    public SimpleStringProperty getNumIndividu() {
        return this.numIndividu;
    }

    public SimpleStringProperty getEspece() {
        return this.espece;
    }

    public SimpleStringProperty getSexe() {
        return this.sexe;
    }
}
