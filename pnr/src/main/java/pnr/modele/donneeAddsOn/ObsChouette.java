package pnr.modele.donneeAddsOn;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ObsChouette {

    private SimpleIntegerProperty protocole, numObs;
    private SimpleStringProperty typeObs, lenumIndividu;

    public ObsChouette(int proto, String type, String leNum, int num) {
        this.protocole = new SimpleIntegerProperty(proto);
        this.typeObs = new SimpleStringProperty(type);
        this.lenumIndividu = new SimpleStringProperty(leNum);
        this.numObs = new SimpleIntegerProperty(num);
    }

    public SimpleIntegerProperty getProtocole() {
        return this.protocole;
    }

    public SimpleStringProperty getTypeObs() {
        return this.typeObs;
    }

    public SimpleStringProperty getLenumIndividu() {
        return this.lenumIndividu;
    }

    public SimpleIntegerProperty getNumObs() {
        return this.numObs;
    }
}
