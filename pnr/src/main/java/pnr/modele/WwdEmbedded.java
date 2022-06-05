package pnr.modele;
import java.sql.*;

public class WwdEmbedded {

    public static void main(String[] args) throws SQLException {
        String nomBdd = "bd_pnr";

        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_pnr", "admin_pnr", "admin");

        ResultSet res = null;
        String query = "SELECT idObservateur FROM Observateur WHERE nom='LANGLAIS'";

        Statement stmt = c.createStatement();
        res = stmt.executeQuery(query);

        while (res.next()) {
            String nom=res.getString("idObservateur");
            System.out.println(nom);
            }
        res.close();

    }
}