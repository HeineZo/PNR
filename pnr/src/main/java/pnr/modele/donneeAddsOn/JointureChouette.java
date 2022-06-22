package pnr.modele.donneeAddsOn;

public class JointureChouette {

    private int protocole, numObs;
    private String typeObs, lenumIndividu, numIndividu, espece, sexe;;

    public JointureChouette(String leNum, String espece, String sexe, int proto, String type, int num) {
        this.lenumIndividu =  leNum;
        this.protocole = proto;
        this.typeObs = type;
        this.numObs = num;
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

    public int getProtocole() {
        return this.protocole; 
    }

    public String getTypeObs() {
        return this.typeObs;
    }

    public String getLeNumIndividu() {
        return this.lenumIndividu;
    }

    public int getNumObs() {
        return this.numObs;
    }
}