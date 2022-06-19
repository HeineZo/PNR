package pnr.modele;
import java.sql.*;

public class WwdEmbedded {

    public static void main(String[] args) throws SQLException {
        ConnectVPS c = new ConnectVPS("jdbc:mysql://141.94.221.193:3306/bd_pnr","user","Mdp@user1");
        ResultSet rs = c.executeQuery("SELECT * FROM Utilisateur");
        String ret = "";
        while(rs.next()) {
            ret = ret + rs.getString(1);
        }
        System.out.println(ret);
    }

    public static Connection getConnection() throws SQLException {
        String db = "bd_pnr";
        String username = "pnr";
        String pwd = "mdp_pnr";
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, pwd);

        return connection;
    }
    
}