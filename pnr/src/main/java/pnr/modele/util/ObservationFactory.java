package pnr.modele.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ObservationFactory {
        private final int id ;
        private final Date date ;
        private final String nom ;
        private final String prenom ;
        private final String pseudonyme ;
    
        public ObservationFactory(int id, Date date) {
            this.id = id ;
            this.date = date ;
            this.nom = "";
            this.prenom = "";
            this.pseudonyme = "";
        }

        public ObservationFactory(String nom, String prenom, String pseudonyme) {
            this.id = -1;
            this.date = null ;
            this.nom = nom ;
            this.prenom = prenom;
            this.pseudonyme = pseudonyme;
        }
    
        public String getDate() {
            return new SimpleDateFormat("dd/MM/yyyy").format(date);
        }
    
        public String getId() {
            return String.valueOf(id) ;
        }

        public String getPseudonyme() {
            return pseudonyme;
        }

        public String toString() {
            String ret;
            if (this.nom.equals("")){
                if (this.date != null){
                    ret = "Observation du "+this.getDate();
                } else {
                    ret =  "Date indisponible";
                }
            } else {
                ret = this.nom+" "+this.prenom;}
            return ret;
        }
}

