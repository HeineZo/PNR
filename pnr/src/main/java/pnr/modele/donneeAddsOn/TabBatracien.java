package pnr.modele.donneeAddsOn;

public class TabBatracien {

    private int obsB, nombreAdultes, nombreAmplexus, nombrePonte, nombreTetard, concerne_zh,
            concernes_vege;
    private String espece, meteo_ciel, meteo_temp, meteo_vent, meteo_pluie;
    private double temperature;

    public TabBatracien(int id, String espece, int nbAd, int nbAm, int nbP, int nbT, double temp, String mc, String mt,
            String mv, String mp, int zh, int vege) {
        this.obsB = id;
        this.espece = espece;
        this.nombreAdultes = nbAd;
        this.nombreAmplexus = nbAm;
        this.nombrePonte = nbP;
        this.nombreTetard = nbT;
        this.temperature = temp;
        this.meteo_ciel = mc;
        this.meteo_temp = mt;
        this.meteo_vent = mv;
        this.meteo_pluie = mp;
        this.concerne_zh =zh;
        this.concernes_vege = vege;
    }

    public int getObsB() {
        return this.obsB;
    }

    public String getEspece() {
        return this.espece;
    }

    public int getNombreAdultes() {
        return this.nombreAdultes;
    }

    public int getNombreAmplexus() {
        return this.nombreAmplexus;
    }

    public int getNombrePonte() {
        return this.nombrePonte;
    }

    public int getNombreTetard() {
        return this.nombreTetard;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public String getMeteoCiel() {
        return this.meteo_ciel;
    }

    public String getMeteoTemp() {
        return this.meteo_temp;
    }

    public String getMeteoVent() {
        return this.meteo_vent;
    }

    public String getMeteoPluie() {
        return this.meteo_pluie;
    }

    public int getConcerneZh() {
        return this.concerne_zh;
    }

    public int getConcerneVege() {
        return this.concernes_vege;
    }
}