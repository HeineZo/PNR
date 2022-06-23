package pnr.modele.donneeAddsOn;

/**
 * Batracien class
 */
public class Batracien {
    private int obsB, nombreAdultes, nombreAmplexus, nombrePonte, nombreTetard, concerne_zh,
            concernes_vege;
    private String espece, meteo_ciel, meteo_temp, meteo_vent, meteo_pluie;
    private double temperature;

    /**
     * Batracien constructor
     * @param id id 
     * @param espece species
     * @param nbAd number adult
     * @param nbAm number Amplexus
     * @param nbP spawning number
     * @param nbT Tetard number
     * @param temp temperature
     * @param mc weather sky
     * @param mt weather temperature
     * @param mv weather wind
     * @param mp meteo rain
     * @param zh zh
     * @param vege vegetation
     */
    public Batracien(int id, String espece, int nbAd, int nbAm, int nbP, int nbT, double temp, String mc, String mt,
            String mv, String mp, int zh, int vege) {
        this.obsB = (id);
        this.espece = (espece);
        this.nombreAdultes = (nbAd);
        this.nombreAmplexus = (nbAm);
        this.nombrePonte = (nbP);
        this.nombreTetard = (nbT);
        this.temperature = temp;
        this.meteo_ciel = (mc);
        this.meteo_temp = (mt);
        this.meteo_vent = (mv);
        this.meteo_pluie = (mp);
        this.concerne_zh = (zh);
        this.concernes_vege = (vege);
    }

    /**
     * This function returns the value of the private variable obsB
     * 
     * @return The value of the variable obsB.
     */
    public int getObsB() {
        return this.obsB;
    }

    /**
     * This function returns the value of the attribute espece
     * 
     * @return The value of the variable espece.
     */
    public String getEspece() {
        return this.espece;
    }

    /**
     * This function returns the number of adults in the reservation
     * 
     * @return The number of adults in the room.
     */
    public int getNombreAdultes() {
        return this.nombreAdultes;
    }

    /**
     * This function returns the number of amplexus
     * 
     * @return The number of amplexus.
     */
    public int getNombreAmplexus() {
        return this.nombreAmplexus;
    }

    /**
     * This function returns the number of bridges in the graph
     * 
     * @return The number of bridges.
     */
    public int getNombrePonte() {
        return this.nombrePonte;
    }

    /**
     * // Java
     * public int getNombreTetard() {
     *         return this.nombreTetard;
     *     }
     * 
     * @return The number of tadpoles in the pond.
     */
    public int getNombreTetard() {
        return this.nombreTetard;
    }

    /**
     * // Java
     * public double getTemperature() {
     *         return this.temperature;
     *     }
     * 
     * @return The temperature of the object.
     */
    public double getTemperature() {
        return this.temperature;
    }

    /**
     * This function returns the value of the attribute meteo_ciel
     * 
     * @return The value of the meteo_ciel variable.
     */
    public String getMeteoCiel() {
        return this.meteo_ciel;
    }

    /**
     * This function returns the temperature of the weather
     * 
     * @return The value of the variable meteo_temp.
     */
    public String getMeteoTemp() {
        return this.meteo_temp;
    }

    /**
     * This function returns the wind speed of the weather
     * 
     * @return The value of the variable meteo_vent.
     */
    public String getMeteoVent() {
        return this.meteo_vent;
    }

    /**
     * This function returns the weather forecast for rain
     * 
     * @return The value of the variable meteo_pluie.
     */
    public String getMeteoPluie() {
        return this.meteo_pluie;
    }

    /**
     * This function returns the value of the variable concerne_zh
     * 
     * @return The value of the variable concerne_zh.
     */
    public int getConcerneZh() {
        return this.concerne_zh;
    }

    /**
     * This function returns the value of the attribute concernes_vege
     * 
     * @return The concernes_vege variable is being returned.
     */
    public int getConcernesvege() {
        return this.concernes_vege;
    }
}