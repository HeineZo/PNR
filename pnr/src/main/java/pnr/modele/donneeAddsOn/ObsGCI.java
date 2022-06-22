package pnr.modele.donneeAddsOn;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ObsGCI {

    private SimpleIntegerProperty obsG, nombre, presentMainsNonObs, leNid;
    private SimpleStringProperty nature;

    public ObsGCI(int obs, String nature, int nb, int present, int nid) {
        this.obsG = new SimpleIntegerProperty(obs);
        this.nature = new SimpleStringProperty(nature);
        this.nombre = new SimpleIntegerProperty(nb);
        this.presentMainsNonObs = new SimpleIntegerProperty(present);
        this.leNid = new SimpleIntegerProperty(nid);
    }

    public SimpleIntegerProperty getObsG() {
        return this.obsG;
    }

    public SimpleStringProperty getNature() {
        return this.nature;
    }

    public SimpleIntegerProperty getNombre() {
        return this.nombre;
    }

    public SimpleIntegerProperty getPresentmainsNonObs() {
        return this.presentMainsNonObs;
    }

    public SimpleIntegerProperty getLeNid() {
        return this.leNid;
    }
}
