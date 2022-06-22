package pnr.modele.donneeAddsOn;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Batracien {

    private SimpleIntegerProperty obsB, nombreAdultes, nombreAmplexus, nombrePonte, nombreTetard, concerne_zh,
            concernes_vege;
    private SimpleStringProperty espece, meteo_ciel, meteo_temp, meteo_vent, meteo_pluie;
    private SimpleDoubleProperty temperature;

    public Batracien(int id, String espece, int nbAd, int nbAm, int nbP, int nbT, double temp, String mc, String mt,
            String mv, String mp, int zh, int vege) {
        this.obsB = new SimpleIntegerProperty(id);
        this.espece = new SimpleStringProperty(espece);
        this.nombreAdultes = new SimpleIntegerProperty(nbAd);
        this.nombreAmplexus = new SimpleIntegerProperty(nbAm);
        this.nombrePonte = new SimpleIntegerProperty(nbP);
        this.nombreTetard = new SimpleIntegerProperty(nbT);
        this.temperature = new SimpleDoubleProperty(temp);
        this.meteo_ciel = new SimpleStringProperty(mc);
        this.meteo_temp = new SimpleStringProperty(mt);
        this.meteo_vent = new SimpleStringProperty(mv);
        this.meteo_pluie = new SimpleStringProperty(mp);
        this.concerne_zh = new SimpleIntegerProperty(zh);
        this.concernes_vege = new SimpleIntegerProperty(vege);
    }

    public SimpleIntegerProperty getObsB() {
        return this.obsB;
    }

    public SimpleStringProperty getEspece() {
        return this.espece;
    }

    public SimpleIntegerProperty getNombreAdultes() {
        return this.nombreAdultes;
    }

    public SimpleIntegerProperty getNombreAmplexus() {
        return this.nombreAmplexus;
    }

    public SimpleIntegerProperty getnombrePonte() {
        return this.nombrePonte;
    }

    public SimpleIntegerProperty getNombreTetard() {
        return this.nombreTetard;
    }

    public SimpleDoubleProperty getTemperatue() {
        return this.temperature;
    }

    public SimpleStringProperty getMeteoCiel() {
        return this.meteo_ciel;
    }

    public SimpleStringProperty getMeteoTemp() {
        return this.meteo_temp;
    }

    public SimpleStringProperty getMeteoVent() {
        return this.meteo_vent;
    }

    public SimpleStringProperty getMeteoPluie() {
        return this.meteo_pluie;
    }

    public SimpleIntegerProperty getConcerneZh() {
        return this.concerne_zh;
    }

    public SimpleIntegerProperty getConcernesvege() {
        return this.concernes_vege;
    }
}
