package pnr.modele.util;

public class Dates {
    

    public String dateToFormat(String chaine){
        String ret ="";
        String[] split = chaine.split(" ");
        String jour = "";
        String mois = "";
        String annee = "";
        String[] lesMois = {"janv.","févr.", "mars", "avr.", "mai","juin","juil.","août","sept.","oct.","nov.","déc."};
        String[] lesJoursAvec0 = {"1","2","3","4","5","6","7","8","9"};
        String[] lesMoisEnNombre = {"01","02", "03", "04", "05","06","07","08","09","10","11","12"};
        boolean trouve = false;
        int i = 0;
        while(trouve == false){
            if (split[1].equals(lesMois[i])){
                mois = lesMoisEnNombre[i];
                trouve = true;
            }
            i++;
        }
        i = 0;
        trouve = false;
        jour = split[0];
        while(trouve == false && i < 9){
            if (jour.equals(lesJoursAvec0[i])){
                jour = lesMoisEnNombre[i];
                trouve = true;
            }
            i++;
        }
        annee = split[2];
        ret = annee+"-"+mois+"-"+jour;
        return ret;
    }

    public String formatToDate(String chaine){
        String ret ="";
        String[] split = chaine.split("-");
        String jour = "";
        String mois = "";
        String annee = "";
        String[] lesMois = {"janv.","févr.", "mars", "avr.", "mai","juin","juil.","août","sept.","oct.","nov.","déc."};
        String[] lesJoursAvec0 = {"1","2","3","4","5","6","7","8","9"};
        String[] lesMoisEnNombre = {"01","02", "03", "04", "05","06","07","08","09","10","11","12"};
        boolean trouve = false;
        int i = 0;
        while(trouve == false){
            if (split[1].equals(lesMoisEnNombre[i])){
                mois = lesMois[i];
                trouve = true;
            }
            i++;
        }
        i = 0;
        trouve = false;
        jour = split[2];
        while(trouve == false && i < 9){
            if (jour.equals(lesMoisEnNombre[i])){
                jour = lesJoursAvec0[i];
                trouve = true;
            }
            i++;
        }
        annee = split[0];
        ret = jour+" "+mois+" "+annee;
        return ret;
    }
}
