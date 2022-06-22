package pnr.modele.donneeAddsOn;

public class Chouette {

    private String numIndividu, espece, sexe;

    public Chouette(String num, String espece, String sexe) {
        this.numIndividu = num;
        this.espece = espece;
        this.sexe = sexe;
    }

    public String getNumIndividu() {
        return this.numIndividu;
    }

    public String getEspece() {
        return this.espece;
    }

    public String getSexe() {
        return this.sexe;
    }
}
