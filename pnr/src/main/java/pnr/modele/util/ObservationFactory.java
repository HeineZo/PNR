package pnr.modele.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ObservationFactory {
        private final int id ;
        private final Date date ;
    
        public ObservationFactory(int id, Date date) {
            this.id = id ;
            this.date = date ;
        }
    
        public String getDate() {
            return new SimpleDateFormat("dd/MM/yyyy").format(date);
        }
    
        public int getId() {
            return id ;
        }
}

