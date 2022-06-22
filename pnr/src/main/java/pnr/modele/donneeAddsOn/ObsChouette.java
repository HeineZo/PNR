package pnr.modele.donneeAddsOn;

public class ObsChouette {

    private int protocole, numObs;
    private String typeObs, lenumIndividu;

    public ObsChouette(int proto, String type, String leNum, int num) {
        this.protocole = proto;
        this.typeObs = type;
        this.lenumIndividu =  leNum;
        this.numObs = num;
    }

    public int getProtocole() {
        return this.protocole;
    }

    public String getTypeObs() {
        return this.typeObs;
    }

    public String getLenumIndividu() {
        return this.lenumIndividu;
    }

    public int getNumObs() {
        return this.numObs;
    }
}
